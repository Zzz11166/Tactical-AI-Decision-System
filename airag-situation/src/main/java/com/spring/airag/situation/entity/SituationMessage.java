package com.spring.airag.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 态势消息实体类
 * 定义WebSocket消息协议格式
 */
@Data
public class SituationMessage {
    /**
     * 消息类型
     * MODEL_UPDATE: 模型状态更新
     * COMMAND: 指令消息
     * DECISION: 决策消息
     * CHAT: 聊天消息
     * PING/PONG: 心跳消息
     * CONNECTED/SUBSCRIBED: 连接状态消息
     */
    private String type;
    
    /**
     * 场景ID
     */
    private String sceneId;
    
    /**
     * 消息内容
     */
    private Object data;
    
    /**
     * 发送者ID
     */
    private String senderId;
    
    /**
     * 接收者ID（可选，如果为空则广播给所有人）
     */
    private String receiverId;
    
    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;
    
    /**
     * 消息序列号
     */
    private Long sequenceNumber;
    
    /**
     * 扩展属性
     */
    private Map<String, Object> attributes;

    public SituationMessage() {
        this.timestamp = new Date();
        this.sequenceNumber = System.currentTimeMillis();
    }

    public SituationMessage(String type, String sceneId, Object data) {
        this();
        this.type = type;
        this.sceneId = sceneId;
        this.data = data;
    }

    public SituationMessage(String type, String sceneId, Object data, String senderId) {
        this(type, sceneId, data);
        this.senderId = senderId;
    }
}