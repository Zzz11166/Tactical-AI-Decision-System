# Nacos配置指南

## 1. 登录Nacos控制台
- 访问：http://localhost:8848/nacos
- 用户名：nacos
- 密码：nacos

## 2. 配置命名空间
- 进入"命名空间"页面
- 创建命名空间：`airag`

## 3. 添加配置项

### 配置1：application.yml
- Data ID: `application.yml`
- Group: `DEFAULT_GROUP`
- 配置格式: YAML
- 配置内容：
```yaml
# 全局公共配置
server:
  servlet:
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    druid:
      # 连接池配置
      initial-size: 5
      min-idle: 5
      max-active: 20
      max-wait: 60000
      # 间隔多久才进行一次检测，检测需要关闭的空闲连接
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-timeMillis: 300000
      validation-query: SELECT 1 FROM DUAL
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      # 打开PSCache，并且指定每个连接上PSCache的大小
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall,log4j
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
      # 合并多个DruidDataSource的监控数据
      use-global-data-source-stat: true

# Redis配置
  redis:
    jedis:
      pool:
        max-active: 20
        max-idle: 10
        min-idle: 0
        max-wait: -1ms
    timeout: 5000ms

# 日志配置
logging:
  level:
    com.spring.airag: DEBUG
    org.springframework: INFO
    org.springframework.web: DEBUG
    org.springframework.security: DEBUG
```

### 配置2：airag-gateway.yml
- Data ID: `airag-gateway.yml`
- Group: `DEFAULT_GROUP`
- 配置格式: YAML
- 配置内容：
```yaml
spring:
  cloud:
    gateway:
      routes:
        # 认证服务路由
        - id: airag-auth
          uri: lb://airag-auth
          predicates:
            - Path=/auth/**
          filters:
            - StripPrefix=1
        # 数据服务路由
        - id: airag-data
          uri: lb://airag-data
          predicates:
            - Path=/data/**
          filters:
            - StripPrefix=1
        # 场景服务路由
        - id: airag-scene
          uri: lb://airag-scene
          predicates:
            - Path=/scene/**
          filters:
            - StripPrefix=1
        # 态势服务路由
        - id: airag-situation
          uri: lb://airag-situation
          predicates:
            - Path=/situation/**
          filters:
            - StripPrefix=1
        # 决策服务路由
        - id: airag-decision
          uri: lb://airag-decision
          predicates:
            - Path=/decision/**
          filters:
            - StripPrefix=1
      default-filters:
        - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOriginPatterns: "*"
            allowedMethods: "*"
            allowedHeaders: "*"
            allowCredentials: true
            maxAge: 3600
```

### 配置3：datasource.yml
- Data ID: `datasource.yml`
- Group: `DEFAULT_GROUP`
- 配置格式: YAML
- 配置内容：
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/airag_common
    username: postgres
    password: 123456
    driver-class-name: org.postgresql.Driver
```

## 4. 验证配置
- 在Nacos控制台的"配置列表"中确认上述配置项均已创建
- 确认命名空间设置正确

## 5. 微服务启动注意事项
- 确保各微服务的bootstrap.yml文件正确配置了Nacos连接参数
- 确保各微服务的spring.application.name与Nacos中的Data ID对应