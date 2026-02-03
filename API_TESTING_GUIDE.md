# AI辅助决策系统API测试指南

本文档详细介绍了如何使用Apifox测试AI辅助决策系统的各项功能，包括gateway网关服务、airag-common公共模块和airag-data数据管理服务。

## 服务架构概览

### 端口分配
- Gateway网关服务: 8888
- airag-data服务: 默认8080（通过网关映射）
- Nacos注册中心: 8848

### 服务路由规则
- `/api/auth/**` → airag-auth服务
- `/api/data/**` → airag-data服务  
- `/api/scene/**` → airag-scene服务
- `/api/situation/**` → airag-situation服务
- `/api/decision/**` → airag-decision服务
- `/ws/**` → airag-situation WebSocket服务

## 1. Gateway网关服务测试

### 1.1 网关健康检查
- **端点**: `GET http://localhost:8888/actuator/health`
- **描述**: 检查网关服务是否正常运行
- **认证**: 无需认证
- **预期响应**:
```json
{
  "status": "UP"
}
```

### 1.2 网关路由测试
- **端点**: `GET http://localhost:8888/actuator/gateway/routes`
- **描述**: 检查网关路由配置
- **认证**: 需要管理员权限
- **预期响应**: 返回当前配置的路由列表

## 2. airag-data数据管理服务测试

### 2.1 文件上传功能
- **端点**: `POST http://localhost:8888/api/data/upload`
- **描述**: 上传文件到MinIO对象存储
- **认证**: 需要Bearer Token
- **请求头**:
  - `Authorization: Bearer {your-jwt-token}`
  - `Content-Type: multipart/form-data`
- **请求参数**:
  - `file`: 文件上传字段
- **示例请求**:
```
curl -X POST http://localhost:8888/api/data/upload \
  -H "Authorization: Bearer {your-jwt-token}" \
  -F "file=@/path/to/your/file.txt"
```
- **预期响应**:
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

### 2.2 文件下载功能
- **端点**: `GET http://localhost:8888/api/data/download/{fileName}`
- **描述**: 从MinIO下载文件
- **认证**: 需要Bearer Token
- **路径参数**:
  - `fileName`: 文件名
- **请求头**:
  - `Authorization: Bearer {your-jwt-token}`
- **预期响应**: 文件二进制流，带有`Content-Disposition`头

### 2.3 文件删除功能
- **端点**: `DELETE http://localhost:8888/api/data/delete/{fileName}`
- **描述**: 从MinIO删除文件
- **认证**: 需要Bearer Token
- **路径参数**:
  - `fileName`: 文件名
- **请求头**:
  - `Authorization: Bearer {your-jwt-token}`
- **示例请求**:
```
curl -X DELETE http://localhost:8888/api/data/delete/example.txt \
  -H "Authorization: Bearer {your-jwt-token}"
```
- **预期响应**:
```json
{
  "code": 200,
  "message": "文件删除成功",
  "data": true
}
```

### 2.4 服务健康检查
- **端点**: `GET http://localhost:8888/api/data/health`
- **描述**: 检查数据服务是否正常运行
- **认证**: 无需认证
- **预期响应**:
```json
{
  "code": 200,
  "message": "数据服务运行正常",
  "data": "数据服务运行正常"
}
```

## 3. JWT认证测试

### 3.1 认证中间件验证
以下端点需要有效的JWT令牌才能访问：

#### 需要认证的路径:
- `/api/data/**`
- `/api/scene/**`
- `/api/situation/**`
- `/api/decision/**`

#### 认证失败响应:
- **状态码**: 401 Unauthorized
- **响应体**: 空

### 3.2 无认证可访问的路径:
- `/api/auth/**` (认证相关接口)
- 网关级别的监控端点

## 4. Apifox测试步骤

### 4.1 环境配置
1. 在Apifox中创建新项目
2. 设置环境变量:
   - `BASE_URL`: `http://localhost:8888`
   - `JWT_TOKEN`: 你的JWT令牌

### 4.2 测试计划
1. **网关连通性测试**:
   - 测试网关健康检查端点
   - 验证路由配置

2. **认证流程测试**:
   - 获取JWT令牌
   - 使用令牌访问受保护端点

3. **数据服务功能测试**:
   - 上传文件
   - 下载文件
   - 删除文件
   - 检查服务健康状态

4. **错误处理测试**:
   - 无认证访问受保护资源
   - 无效JWT令牌访问
   - 不存在的文件操作

### 4.3 预期测试结果
- 所有认证通过的请求应返回200状态码
- 未认证访问受保护资源应返回401状态码
- 文件操作应成功完成且返回正确的响应
- 网关应正确路由到相应微服务

## 5. 注意事项

1. **JWT令牌获取**: 需要先通过认证服务获取有效的JWT令牌
2. **服务依赖**: 确保Nacos注册中心、MinIO对象存储等依赖服务正在运行
3. **网络连通性**: 确保所有微服务都已注册到Nacos并能相互通信
4. **CORS配置**: 网关已配置允许所有来源的跨域请求

## 6. 常见问题排查

1. **网关无法路由**: 检查服务是否已在Nacos注册
2. **认证失败**: 检查JWT令牌是否有效且未过期
3. **文件操作失败**: 检查MinIO服务是否正常运行
4. **端口冲突**: 确认各服务使用的端口未被占用