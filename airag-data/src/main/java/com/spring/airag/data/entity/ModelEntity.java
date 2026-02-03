package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型实体类
 * 对应数据库中的models表
 */
@Data
public class ModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sceneId;            // REFERENCES exercise_scenes(id)
    private String name;
    private String side;               // RED, BLUE, NEUTRAL
    private String type;               // INFANTRY, TANK, AIRCRAFT, SHIP, ARTILLERY
    private Double positionX;          // 默认0.0
    private Double positionY;          // 默认0.0
    private Double positionZ;          // 默认0.0
    private String status;             // ACTIVE, MOVING, ATTACKING, DETECTING, DAMAGED, DESTROYED，默认ACTIVE
    private String detectionStatus;    // UNDETECTED, DETECTED, HIDDEN，默认UNDETECTED
    private String detectedBy;         // 被谁发现
    private Date detectionTime;        // 发现时间
    private Double health;             // 生命值，默认100.0
    private Double attackPower;        // 攻击力，默认10.0
    private Double defensePower;       // 防御力，默认5.0
    private Double attackRange;        // 攻击范围，默认100.0
    private Double detectionRange;     // 探测范围，默认500.0
    private Double moveSpeed;          // 移动速度，默认10.0
    private Date createdAt;
    private Date updatedAt;
}