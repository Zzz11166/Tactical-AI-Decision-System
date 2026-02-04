package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型/单位实体类
 * 对应数据库中的models表
 */
@Data
public class ModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sceneId;           // REFERENCES exercise_scenes(id)
    private String name;
    private String side;              // RED, BLUE, NEUTRAL
    private String type;              // INFANTRY, TANK, AIRCRAFT, SHIP, ARTILLERY
    private Double positionX;         // X坐标
    private Double positionY;         // Y坐标
    private Double positionZ;         // Z坐标
    private String status;            // ACTIVE, MOVING, ATTACKING, DETECTING, DAMAGED, DESTROYED
    private String detectionStatus;   // UNDETECTED, DETECTED, HIDDEN
    private String detectedBy;        // 被谁发现
    private Date detectionTime;       // 发现时间
    private Double health;            // 生命值
    private Double attackPower;       // 攻击力
    private Double defensePower;      // 防御力
    private Double attackRange;       // 攻击范围
    private Double detectionRange;    // 探测范围
    private Double moveSpeed;         // 移动速度
    private Date createdAt;
    private Date updatedAt;
}