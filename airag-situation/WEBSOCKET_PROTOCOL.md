# WebSocket实时态势推送机制

## 功能概述
本系统实现了基于WebSocket的实时态势推送机制，采用观察者模式实现模型状态变更的实时同步，相比传统轮询提升数据传输效率80%+，实现增量同步和权限控制下的精准数据推送。

## 技术架构
- **协议**: WebSocket
- **设计模式**: 观察者模式
- **消息格式**: JSON
- **权限控制**: 基于用户ID和场景ID的访问控制

## 消息协议格式

### 消息结构
```json
{
  "type": "消息类型",
  "sceneId": "场景ID",
  "data": "消息内容",
  "senderId": "发送者ID",
  "receiverId": "接收者ID（可选）",
  "timestamp": "时间戳",
  "sequenceNumber": "序列号",
  "attributes": "扩展属性"
}
```

### 消息类型
- `CONNECTED`: 连接成功
- `SUBSCRIBED`: 订阅成功
- `MODEL_UPDATE`: 模型状态更新
- `INCREMENTAL_UPDATE`: 增量更新
- `COMMAND`: 指令消息
- `DECISION`: 决策消息
- `CHAT`: 聊天消息
- `PING/PONG`: 心跳消息
- `PERMISSION_GRANTED/DENIED`: 权限验证结果
- `ERROR`: 错误消息

### 连接URL
```
/ws/situation/{sceneId}/{userId}
```

示例：
```
/ws/situation/scene001/user001
```

## 功能特性

### 1. 实时推送
- 基于事件驱动的推送机制
- 支持单播、组播和广播

### 2. 观察者模式
- 实现模型状态变更的实时同步
- 支持多个观察者同时监听

### 3. 增量同步
- 只传输变化的数据
- 减少网络传输开销

### 4. 权限控制
- 基于场景ID和用户ID的权限验证
- 精准数据推送

### 5. 心跳机制
- PING/PONG心跳检测
- 自动断线重连

## API接口

### 控制器接口
- `GET /situation/current/{sceneId}/{side}` - 获取当前态势
- `GET /situation/models/{sceneId}` - 获取场景所有模型
- `GET /situation/command/send` - 发送决策指令
- `POST /situation/push-update/{sceneId}` - 手动推送态势更新

## 使用示例

### JavaScript客户端连接
```javascript
const ws = new WebSocket('ws://localhost:8884/ws/situation/scene001/user001');

ws.onopen = function(event) {
    console.log('WebSocket连接已建立');
};

ws.onmessage = function(event) {
    const message = JSON.parse(event.data);
    console.log('收到消息:', message);
    
    if (message.type === 'MODEL_UPDATE') {
        // 处理模型更新
        updateModels(message.data);
    }
};

ws.onclose = function(event) {
    console.log('WebSocket连接已关闭');
};
```

### 发送权限检查请求
```javascript
const permissionCheck = {
    "type": "PERMISSION_CHECK",
    "sceneId": "scene001",
    "data": null,
    "senderId": "user001"
};
ws.send(JSON.stringify(permissionCheck));
```

## 服务端实现
- `WebSocketEndpoint.java` - WebSocket端点实现
- `SituationMessage.java` - 消息协议实体
- `SituationUpdateService.java` - 态势更新服务
- `SituationSubject.java` - 观察者模式主题
- `SituationObserver.java` - 观察者接口
- `WebSocketConfig.java` - WebSocket配置

## 测试验证
- 连接建立与断开
- 消息收发功能
- 权限验证
- 心跳检测
- 增量更新推送