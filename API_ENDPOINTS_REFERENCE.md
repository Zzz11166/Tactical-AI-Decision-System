# AI辅助决策系统API端点参考

本文档提供了AI辅助决策系统中所有可用API端点的详细参考信息。

## 1. Gateway网关服务

### 1.1 路由配置

网关服务使用Spring Cloud Gateway作为API网关，配置了以下路由规则：

- **认证服务路由**
  - 路径: `/api/auth/**`
  - 目标: `lb://airag-auth`
  - 前缀剥离: 2级 (`/api/auth` → `/`)

- **数据管理服务路由**
  - 路径: `/api/data/**`
  - 目标: `lb://airag-data`
  - 前缀剥离: 2级 (`/api/data` → `/`)
  - 特性: 带有速率限制

- **场景构设服务路由**
  - 路径: `/api/scene/**`
  - 目标: `lb://airag-scene`
  - 前缀剥离: 2级 (`/api/scene` → `/`)

- **态势展示服务路由**
  - 路径: `/api/situation/**`
  - 目标: `lb://airag-situation`
  - 前缀剥离: 2级 (`/api/situation` → `/`)

- **AI决策服务路由**
  - 路径: `/api/decision/**`
  - 目标: `lb://airag-decision`
  - 前缀剥离: 2级 (`/api/decision` → `/`)

- **WebSocket路由**
  - 路径: `/ws/**`
  - 目标: `lb://airag-situation`

### 1.2 认证过滤器

网关配置了全局认证过滤器，对以下路径进行JWT令牌验证：

- `/api/data/**`
- `/api/scene/**`
- `/api/situation/**`
- `/api/decision/**`

认证要求：
- 请求头必须包含 `Authorization: Bearer {token}`
- JWT令牌必须有效且未过期

### 1.3 网关端点

- **健康检查**: `GET /actuator/health`
- **路由信息**: `GET /actuator/gateway/routes`
- **指标信息**: `GET /actuator/gateway/metrics`

## 2. Airag-Data数据管理服务

### 2.1 DataController

#### 2.1.1 文件上传
- **端点**: `POST /data/upload`
- **功能**: 上传文件到MinIO对象存储
- **参数**: 
  - `file`: Multipart文件参数
- **认证**: 需要JWT令牌
- **响应**:
  ```json
  {
    "code": 200,
    "message": "文件上传成功",
    "data": {
      "id": "unique-file-id",
      "filename": "file.txt",
      "originalName": "original-file.txt",
      "fileSize": 1024,
      "fileType": "text/plain",
      "filePath": "minio-bucket/file.txt",
      "uploadTime": "2026-02-03T10:00:00",
      "uploadedBy": "user-id",
      "tags": [],
      "metadata": {},
      "status": "ACTIVE"
    }
  }
  ```

#### 2.1.2 文件下载
- **端点**: `GET /data/download/{fileName}`
- **功能**: 从MinIO下载文件
- **路径参数**: `fileName` - 文件名
- **认证**: 需要JWT令牌
- **响应**: 文件流，带有适当的内容头

#### 2.1.3 文件删除
- **端点**: `DELETE /data/delete/{fileName}`
- **功能**: 从MinIO删除文件
- **路径参数**: `fileName` - 文件名
- **认证**: 需要JWT令牌
- **响应**:
  ```json
  {
    "code": 200,
    "message": "文件删除成功",
    "data": true
  }
  ```

#### 2.1.4 服务健康检查
- **端点**: `GET /data/health`
- **功能**: 检查数据服务运行状态
- **认证**: 无需认证
- **响应**:
  ```json
  {
    "code": 200,
    "message": "数据服务运行正常",
    "data": "数据服务运行正常"
  }
  ```

### 2.2 SchemaValidationController

- **端点**: `GET /data/schema/validate`
- **功能**: 验证数据库表结构完整性
- **认证**: 需要JWT令牌
- **响应**: 数据库表结构验证结果

## 3. 通用响应格式

所有API端点遵循统一的响应格式：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {} // 具体响应数据
}
```

### 3.1 响应码说明

- `200`: 成功
- `400`: 请求参数错误
- `401`: 未授权（认证失败）
- `403`: 禁止访问（权限不足）
- `404`: 资源不存在
- `500`: 服务器内部错误

### 3.2 Result<T> 类结构

- `code`: 响应状态码
- `message`: 响应消息
- `data`: 响应数据（泛型）
- `timestamp`: 时间戳
- `success`: 是否成功

## 4. 认证与授权

### 4.1 JWT令牌

- **格式**: `Authorization: Bearer {jwt-token}`
- **有效期**: 24小时（可配置）
- **加密算法**: HS256
- **密钥**: 从环境变量或配置文件加载

### 4.2 受保护的端点

以下服务路径需要有效的JWT令牌：

- `/api/data/**`
- `/api/scene/**`
- `/api/situation/**`
- `/api/decision/**`

## 5. 服务注册与发现

所有微服务都注册到Nacos，服务名称如下：

- `airag-gateway`: 网关服务
- `airag-data`: 数据管理服务
- `airag-auth`: 认证服务
- `airag-scene`: 场景构设服务
- `airag-situation`: 态势展示服务
- `airag-decision`: AI决策服务

## 6. 错误处理

### 6.1 通用错误响应

```json
{
  "code": 500,
  "message": "错误消息",
  "data": null
}
```

### 6.2 参数校验错误

```json
{
  "code": 400,
  "message": "参数校验失败",
  "data": {
    "fieldErrors": [
      {
        "field": "fieldName",
        "message": "错误消息"
      }
    ]
  }
}
```

## 7. 监控与日志

### 7.1 Actuator端点

- `/actuator/health`: 健康检查
- `/actuator/info`: 应用信息
- `/actuator/metrics`: 指标信息
- `/actuator/env`: 环境信息

### 7.2 日志级别

- `DEBUG`: 详细调试信息
- `INFO`: 一般信息
- `WARN`: 警告信息
- `ERROR`: 错误信息