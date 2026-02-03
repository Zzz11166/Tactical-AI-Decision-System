# AI辅助决策系统Apifox测试快速启动指南

本文档指导您如何快速开始使用Apifox测试AI辅助决策系统的功能。

## 1. 环境准备

### 1.1 启动依赖服务

在开始API测试之前，请确保以下服务正在运行：

1. **Nacos注册中心**
   ```bash
   docker run -d --name nacos -e MODE=standalone -p 8848:8848 nacos/nacos-server:latest
   ```

2. **MinIO对象存储**
   ```bash
   docker run -d --name minio -p 9000:9000 -p 9001:9001 -e MINIO_ROOT_USER=minioadmin -e MINIO_ROOT_PASSWORD=minioadmin quay.io/minio/minio:latest server /data --console-address ":9001"
   ```

3. **PostgreSQL数据库**（如果需要）
   ```bash
   docker run -d --name postgres -p 5432:5432 -e POSTGRES_DB=airag_data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 postgres:13
   ```

4. **Redis缓存**（如果需要）
   ```bash
   docker run -d --name redis -p 6379:6379 redis:latest
   ```

### 1.2 启动微服务

按以下顺序启动微服务：

1. **启动认证服务** (airag-auth)
2. **启动数据服务** (airag-data)
3. **启动其他服务** (airag-scene, airag-situation, airag-decision)
4. **启动网关服务** (airag-gateway) - 最后启动

## 2. Apifox配置

### 2.1 导入API集合

1. 打开Apifox
2. 点击左上角"文件" → "导入"
3. 选择项目根目录下的 [apifox_collection.json](file:///D:/code/AIRAGDemo/apifox_collection.json) 文件
4. 点击"导入"完成API集合导入

### 2.2 配置环境变量

1. 点击右上角"环境"按钮
2. 创建新环境，例如命名为"Local Development"
3. 添加以下环境变量：

| 变量名 | 初始值 | 当前值 |
|--------|--------|--------|
| BASE_URL | http://localhost:8888 | http://localhost:8888 |
| JWT_TOKEN | | （留空，后续填入） |
| ADMIN_JWT_TOKEN | | （留空，后续填入） |
| FILENAME | test.txt | test.txt |

### 2.3 配置全局前置脚本（可选）

在项目设置中，可以配置全局前置脚本来自动处理认证：

```javascript
// 示例：自动获取JWT令牌
if (!pm.environment.get("JWT_TOKEN")) {
    // 这里需要根据实际的认证接口获取令牌
    pm.sendRequest({
        url: pm.environment.get("BASE_URL") + "/api/auth/login",
        method: 'POST',
        header: {
            'Content-Type': 'application/json'
        },
        body: {
            mode: 'raw',
            raw: JSON.stringify({
                username: "testuser",
                password: "testpass"
            })
        }
    }, function (err, response) {
        if (!err && response.json().data.token) {
            pm.environment.set("JWT_TOKEN", response.json().data.token);
        }
    });
}
```

## 3. 测试步骤

### 3.1 验证网关服务

1. 展开"Gateway网关服务"目录
2. 选择"网关健康检查"请求
3. 点击"发送"按钮
4. 验证响应状态码为200，响应内容显示网关运行正常

### 3.2 获取JWT令牌

1. 需要先通过认证服务获取JWT令牌（假设已有认证接口）
2. 将获取到的令牌填入环境变量`JWT_TOKEN`中

### 3.3 测试数据管理功能

#### 3.3.1 上传文件测试

1. 展开"Airag-Data数据管理服务"目录
2. 选择"上传文件"请求
3. 在Body选项卡中，点击"Select Files"选择一个测试文件
4. 确保Headers中Authorization字段使用了有效的JWT_TOKEN
5. 点击"发送"
6. 验证响应是否成功，并记录返回的文件名用于后续测试

#### 3.3.2 下载文件测试

1. 在环境变量中设置FILENAME为上一步上传的文件名
2. 选择"下载文件"请求
3. 点击"发送"
4. 验证是否能成功下载文件

#### 3.3.3 删除文件测试

1. 选择"删除文件"请求
2. 确保FILENAME环境变量设置正确
3. 点击"发送"
4. 验证文件是否被成功删除

### 3.4 验证认证机制

#### 3.4.1 无认证访问测试

1. 展开"认证与安全"目录
2. 选择"访问受保护资源-无认证"请求
3. 点击"发送"
4. 验证响应状态码应为401 Unauthorized

#### 3.4.2 有认证访问测试

1. 选择"访问受保护资源-有效认证"请求
2. 确保JWT_TOKEN环境变量已设置
3. 点击"发送"
4. 验证响应状态码应为200 OK

## 4. 测试用例

### 4.1 功能测试用例

1. **文件上传功能**
   - 验证不同类型文件的上传
   - 验证大文件上传（边界测试）
   - 验证重复文件名处理

2. **文件下载功能**
   - 验证文件完整性
   - 验证下载速度
   - 验证不同文件类型的下载

3. **文件删除功能**
   - 验证删除成功后无法再访问
   - 验证删除不存在的文件返回适当错误

4. **认证功能**
   - 验证有效令牌可以访问受保护资源
   - 验证无效/过期令牌返回401
   - 验证无令牌访问返回401

### 4.2 性能测试用例

1. 并发上传多个文件
2. 同时下载多个文件
3. 高频API调用压力测试

### 4.3 安全测试用例

1. 验证无权用户无法访问他人文件
2. 验证恶意文件名处理
3. 验证路径遍历攻击防护

## 5. 故障排除

### 5.1 常见问题

1. **404错误**
   - 检查服务是否正常启动
   - 检查Nacos注册状态
   - 检查网关路由配置

2. **401错误**
   - 检查JWT令牌是否有效
   - 检查令牌是否过期
   - 检查Authorization头格式

3. **连接超时**
   - 检查网络连通性
   - 检查防火墙设置
   - 检查服务端口是否正确

### 5.2 日志查看

1. 查看网关日志：检查路由和认证相关信息
2. 查看数据服务日志：检查文件操作相关信息
3. 查看Nacos日志：检查服务注册发现相关信息

## 6. 扩展测试

随着系统功能的增加，可以添加更多测试用例：

1. 场景构设服务API测试
2. 态势展示服务WebSocket测试
3. AI决策服务推理API测试
4. 系统集成测试

## 7. 注意事项

1. 测试时注意备份重要数据
2. 避免在生产环境中使用测试数据
3. 遵循API使用频率限制
4. 测试完成后清理临时文件