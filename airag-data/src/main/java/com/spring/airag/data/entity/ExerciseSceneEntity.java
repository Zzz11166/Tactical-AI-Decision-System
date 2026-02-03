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
    private Integer creatorId;        // 创建者ID (REFERENCES users(id))
    private Date startTime;
    private Date endTime;
    private String status;            // PREPARING, RUNNING, FINISHED, PAUSED，默认为PREPARING
    private Object config;            // 场景配置信息 (JSONB)
    private Date createdAt;
    private Date updatedAt;
}