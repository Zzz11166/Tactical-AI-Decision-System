package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型实体类
 */
@Data
public class Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sceneId;
    private String name;
    private String side;  // RED, BLUE
    private Type type;
    private Position position;
    private Status status;
    private DetectionStatus detectionStatus;
    private String detectedBy;
    private Date detectionTime;
    private Double health;
    private Double attackPower;
    private Double defensePower;
    private Double attackRange;
    private Double detectionRange;
    private Double moveSpeed;
    private Date lastUpdateTime;

    /**
     * 模型类型
     */
    public enum Type {
        INFANTRY,   // 步兵
        TANK,       // 坦克
        AIRCRAFT,   // 飞机
        SHIP,       // 舰船
        ARTILLERY   // 火炮
    }

    /**
     * 模型状态
     */
    public enum Status {
        ACTIVE,     // 活跃
        MOVING,     // 移动中
        ATTACKING,  // 攻击中
        DETECTING,  // 侦查中
        DAMAGED,    // 受损
        DESTROYED   // 摧毁
    }

    /**
     * 检测状态
     */
    public enum DetectionStatus {
        UNDETECTED, // 未被发现
        DETECTED,   // 已被发现
        HIDDEN      // 隐藏
    }
}
