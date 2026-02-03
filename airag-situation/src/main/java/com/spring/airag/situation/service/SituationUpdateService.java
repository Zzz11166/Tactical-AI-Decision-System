package com.spring.airag.situation.service;

import com.spring.airag.common.entity.Model;
import com.spring.airag.situation.entity.SituationMessage;
import com.spring.airag.situation.observer.SituationSubject;
import com.spring.airag.situation.websocket.WebSocketEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 态势更新服务类
 */
@Slf4j
@Service
public class SituationUpdateService {

    private final SituationSubject subject = new SituationSubject();

    public SituationUpdateService() {
        // 注册WebSocket端点作为观察者
        // 注意：WebSocket端点会在连接时自动加入观察者列表
    }

    /**
     * 推送态势更新到指定场景
     */
    public void pushSituationUpdate(String sceneId, Map<String, Model> models, String eventType) {
        try {
            // 创建态势更新消息
            SituationMessage message = new SituationMessage(eventType, sceneId, models);
            message.setSenderId("SYSTEM");
            
            // 发送到指定场景的所有连接
            WebSocketEndpoint.sendMessageToScene(sceneId, message);
            
            log.info("态势更新已推送: sceneId={}, modelCount={}, eventType={}", 
                    sceneId, models.size(), eventType);
        } catch (Exception e) {
            log.error("推送态势更新失败: sceneId={}", sceneId, e);
        }
    }

    /**
     * 推送增量更新
     */
    public void pushIncrementalUpdate(String sceneId, Map<String, Model> changedModels, String eventType) {
        try {
            // 创建增量更新消息
            SituationMessage message = new SituationMessage("INCREMENTAL_UPDATE", sceneId, changedModels);
            message.setSenderId("SYSTEM");
            message.getAttributes().put("originalEventType", eventType);
            
            // 发送给指定场景的WebSocket连接
            WebSocketEndpoint.sendMessageToScene(sceneId, message);
            
            log.info("增量更新已推送: sceneId={}, changedModelCount={}, eventType={}", 
                    sceneId, changedModels.size(), eventType);
        } catch (Exception e) {
            log.error("推送增量更新失败: sceneId={}", sceneId, e);
        }
    }

    /**
     * 广播消息到所有连接
     */
    public void broadcastMessage(String sceneId, String messageContent, String messageType) {
        try {
            SituationMessage message = new SituationMessage(messageType, sceneId, messageContent);
            message.setSenderId("SYSTEM");
            
            WebSocketEndpoint.broadcast(message);
            log.info("广播消息: type={}, content={}", messageType, messageContent);
        } catch (Exception e) {
            log.error("广播消息失败", e);
        }
    }
    
    /**
     * 获取当前连接数
     */
    public int getConnectionCount() {
        return WebSocketEndpoint.getConnectionCount();
    }
}