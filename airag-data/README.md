# 数据管理微服务功能说明

## 功能概述

本服务提供了完整的数据管理功能，包括：
1. 五张核心数据表的增删改查操作
2. 文件上传到MinIO并存储数据库信息
3. 大文件分块上传和断点续传功能

## 核心功能

### 1. 数据库表操作

支持以下五张表的操作：
- **files**: 文件信息表
- **exercise_scenes**: 演练场景表  
- **models**: 模型/单位表
- **tactical_cases**: 战术案例表
- **rag_knowledge**: RAG知识库表

### 2. 文件管理功能

#### 普通文件上传
```
POST /api/upload
参数:
- file: 文件内容
- businessType: 业务类型(可选)
- businessId: 业务ID(可选)
```

#### 分块上传功能
1. **初始化上传**
```
POST /api/chunk/init
参数:
- fileName: 文件名
- fileSize: 文件大小
- fileMd5: 文件MD5值
- totalChunks: 总分块数
```

2. **上传分块**
```
POST /api/chunk/upload
参数:
- file: 分块文件
- fileId: 文件ID
- chunkNumber: 分块序号
- chunkMd5: 分块MD5值
```

3. **检查分块状态**
```
GET /api/chunk/check/{fileId}/{chunkNumber}
```

4. **获取已上传分块列表**
```
GET /api/chunk/uploaded/{fileId}
```

5. **合并分块**
```
POST /api/chunk/merge/{fileId}
```

6. **取消上传**
```
DELETE /api/chunk/cancel/{fileId}
```

### 3. 数据查询接口

#### 文件信息查询
```
GET /api/files                    # 查询所有文件
GET /api/files/{id}              # 根据ID查询
GET /api/files/business/{type}   # 根据业务类型查询
```

#### 其他表查询
```
GET /api/scenes                  # 演练场景相关接口
GET /api/models                  # 模型相关接口  
GET /api/cases                   # 战术案例相关接口
GET /api/knowledge               # 知识库相关接口
```

## 技术架构

采用标准的三层架构：
- **Controller层**: 处理HTTP请求
- **Service层**: 业务逻辑处理
- **Mapper层**: 数据访问层(MyBatis)

## 配置要求

### 数据库配置
在`application.yml`中配置PostgreSQL连接：
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database
    username: your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
```

### MinIO配置
```yaml
minio:
  endpoint: http://localhost:9000
  access-key: your_access_key
  secret-key: your_secret_key
  bucket-name: your_bucket_name
```

## 部署说明

1. 执行数据库脚本创建表结构
2. 配置数据库和MinIO连接信息
3. 启动服务
4. 通过API网关访问相关接口

## 注意事项

1. 分块上传适用于大于100MB的文件
2. 文件信息会同时存储在MinIO和数据库中
3. 支持断点续传，可以从中断处继续上传
4. 所有接口都返回标准的JSON格式响应