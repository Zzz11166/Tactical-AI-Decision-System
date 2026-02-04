package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 演练场景实体类
 * 对应数据库中的exercise_scenes表
 */
@Data
public class ExerciseSceneEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String type;              // 演练类型：进攻作战、防御作战等
    private String description;
    private String mapId;             // 地图ID
    private String creatorId;         // 创建者ID
    private Date startTime;
    private Date endTime;
    private String status;            // PREPARING, RUNNING, FINISHED, PAUSED
    private String config;            // 场景配置信息(JSON)
    private Date createdAt;
    private Date updatedAt;
}