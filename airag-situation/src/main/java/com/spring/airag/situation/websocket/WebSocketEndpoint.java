package com.spring.airag.situation.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.airag.common.entity.Model;
import com.spring.airag.situation.entity.SituationMessage;
import com.spring.airag.situation.observer.SituationObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket端点类，用于实时态势推送
 */
@Slf4j
@Component
@ServerEndpoint("/ws/situation/{sceneId}/{userId}")
public class WebSocketEndpoint implements SituationObserver {

    private static final CopyOnWriteArraySet<WebSocketEndpoint> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private String sceneId;
    private String userId;

    // 用于存储客户端权限信息
    private static final Map<String, String> userPermissions = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, @PathParam("sceneId") String sceneId, @PathParam("userId") String userId) {
        this.session = session;
        this.sceneId = sceneId;
        this.userId = userId;
        webSocketSet.add(this);
        log.info("客户端连接: sceneId={}, userId={}, 当前连接数={}", sceneId, userId, webSocketSet.size());
        
        // 发送连接成功的确认消息
        SituationMessage connectedMsg = new SituationMessage("CONNECTED", sceneId, "连接成功");
        connectedMsg.setSenderId(userId);
        sendMessage(connectedMsg);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("客户端断开: sceneId={}, userId={}, 当前连接数={}", sceneId, userId, webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.debug("来自客户端的消息: {}", message);
        // 处理客户端发送的消息，可以根据具体需求实现
        try {
            SituationMessage msgObj = objectMapper.readValue(message, SituationMessage.class);
            
            String type = msgObj.getType();
            if ("PING".equals(type)) {
                // 响应心跳
                SituationMessage pongMsg = new SituationMessage("PONG", msgObj.getSceneId(), null);
                pongMsg.setSenderId("SERVER");
                sendMessage(pongMsg);
            } else if ("SUBSCRIBE".equals(type)) {
                // 处理订阅请求
                String subscribeSceneId = msgObj.getSceneId();
                if (subscribeSceneId != null && subscribeSceneId.equals(this.sceneId)) {
                    // 可以在这里实现权限验证
                    SituationMessage subscribedMsg = new SituationMessage("SUBSCRIBED", subscribeSceneId, "订阅成功");
                    subscribedMsg.setSenderId(userId);
                    sendMessage(subscribedMsg);
                }
            } else if ("PERMISSION_CHECK".equals(type)) {
                // 权限检查请求
                String requestedSceneId = msgObj.getSceneId();
                if (hasPermission(userId, requestedSceneId)) {
                    SituationMessage permGrantedMsg = new SituationMessage("PERMISSION_GRANTED", requestedSceneId, "权限验证通过");
                    permGrantedMsg.setSenderId("SERVER");
                    sendMessage(permGrantedMsg);
                } else {
                    SituationMessage permDeniedMsg = new SituationMessage("PERMISSION_DENIED", requestedSceneId, "权限不足");
                    permDeniedMsg.setSenderId("SERVER");
                    sendMessage(permDeniedMsg);
                }
            }
        } catch (Exception e) {
            log.error("处理客户端消息失败", e);
            // 发送错误消息
            SituationMessage errorMsg = new SituationMessage("ERROR", sceneId, "消息格式错误: " + e.getMessage());
            errorMsg.setSenderId("SERVER");
            sendMessage(errorMsg);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket发生错误", error);
    }

    /**
     * 发送消息给当前连接
     */
    public void sendMessage(SituationMessage message) {
        try {
            if (session != null && session.isOpen()) {
                String jsonStr = objectMapper.writeValueAsString(message);
                session.getBasicRemote().sendText(jsonStr);
            }
        } catch (IOException e) {
            log.error("发送消息失败", e);
        }
    }

    /**
     * 发送消息给当前连接
     */
    public void sendMessage(String message) {
        try {
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            log.error("发送消息失败", e);
        }
    }

    /**
     * 发送消息给指定场景的所有连接
     */
    public static void sendMessageToScene(String sceneId, SituationMessage message) {
        for (WebSocketEndpoint item : webSocketSet) {
            if (item.sceneId.equals(sceneId)) {
                item.sendMessage(message);
            }
        }
    }

    /**
     * 发送消息给所有连接
     */
    public static void broadcast(SituationMessage message) {
        for (WebSocketEndpoint item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    /**
     * 获取当前连接数
     */
    public static int getConnectionCount() {
        return webSocketSet.size();
    }

    /**
     * 检查用户权限
     */
    private boolean hasPermission(String userId, String sceneId) {
        // 这里实现具体的权限检查逻辑
        // 简单示例：假设所有用户都有读取权限
        return true;
    }

    /**
     * 实现态势观察者接口，接收态势更新
     */
    @Override
    public void update(String sceneId, Map<String, Model> models, String eventType) {
        if (this.sceneId.equals(sceneId)) {
            try {
                // 构建态势更新消息
                SituationMessage situationMsg = new SituationMessage(eventType, sceneId, models);
                situationMsg.setSenderId("SYSTEM");
                
                sendMessage(situationMsg);
            } catch (Exception e) {
                log.error("发送态势更新失败", e);
            }
        }
    }
}