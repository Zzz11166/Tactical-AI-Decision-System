package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 战术案例实体类
 * 对应数据库中的tactical_cases表
 */
@Data
public class TacticalCaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String description;
    private String scenarioType;      // 案例适用的场景类型
    private String forcesComposition; // 双方兵力配置(JSON)
    private String tacticsUsed;       // 使用的战术
    private String outcome;           // 案例结果
    private String lessonsLearned;    // 经验教训
    private Integer difficultyLevel;  // 难度等级 1-5
    private List<String> tags;        // 标签数组
    private String createdBy;         // 创建者
    private Date createdAt;
    private Date updatedAt;
}