package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 演练场景实体类
 */
@Data
public class ExerciseScene implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private String mapId;
    private Status status;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private String createdBy;
    private List<Model> models;

    /**
     * 场景状态
     */
    public enum Status {
        CREATED,    // 已创建
        RUNNING,    // 运行中
        PAUSED,     // 已暂停
        COMPLETED   // 已完成
    }
}
