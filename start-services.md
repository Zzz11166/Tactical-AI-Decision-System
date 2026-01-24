# 微服务启动指南

## 启动顺序

为了确保系统正常运行，微服务应按以下顺序启动：

### 1. 基础设施服务
- Nacos (服务注册与配置中心)
- PostgreSQL (数据库)
- Redis (缓存)
- Elasticsearch (搜索引擎)
- MinIO (对象存储)

### 2. 微服务启动顺序

#### 第一步：启动网关服务
```bash
cd airag-gateway
mvn spring-boot:run
```

#### 第二步：启动认证服务
```bash
cd airag-auth
mvn spring-boot:run
```

#### 第三步：启动数据服务
```bash
cd airag-data
mvn spring-boot:run
```

#### 第四步：启动场景服务
```bash
cd airag-scene
mvn spring-boot:run
```

#### 第五步：启动态势服务
```bash
cd airag-situation
mvn spring-boot:run
```

#### 第六步：启动决策服务
```bash
cd airag-decision
mvn spring-boot:run
```

## 验证服务状态

启动每个服务后，可通过以下方式验证：

1. **Nacos控制台**: http://localhost:8848/nacos
   - 用户名/密码: nacos/nacos
   - 检查各服务是否成功注册

2. **服务健康检查**:
   - 网关: http://localhost:8080/actuator/health
   - 认证服务: http://localhost:8081/actuator/health
   - 数据服务: http://localhost:8082/actuator/health
   - 场景服务: http://localhost:8084/actuator/health
   - 态势服务: http://localhost:8083/actuator/health
   - 决策服务: http://localhost:8085/actuator/health

## 注意事项

1. **数据库初始化**：确保所有数据库已创建并执行了建表脚本
2. **服务依赖**：某些服务依赖其他服务，确保上游服务已启动后再启动下游服务
3. **配置同步**：服务启动时会从Nacos拉取配置，确保Nacos已启动并包含所需配置
4. **端口检查**：确保各服务端口未被占用

## 常见问题排查

1. **服务注册失败**：检查Nacos是否正常运行，网络连接是否正常
2. **数据库连接失败**：检查数据库服务是否运行，连接参数是否正确
3. **配置获取失败**：检查Nacos中是否存在相应的配置项
4. **服务间调用失败**：检查服务是否已成功注册到Nacos

## 停止服务

按相反顺序停止服务：
1. 决策服务
2. 态势服务
3. 场景服务
4. 数据服务
5. 认证服务
6. 网关服务

基础设施服务可在最后停止。