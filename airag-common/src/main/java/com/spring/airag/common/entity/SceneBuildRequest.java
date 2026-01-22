package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 场景构建请求实体类
 */
@Data
public class SceneBuildRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private String userId;
    private String mapId;
    private String operationType;  // 作战类型：进攻作战、防御作战、侦察作战等
    private String terrain;        // 地形：平原、山地、城市、丛林等
    private String weather;        // 天气：晴、雨、雪等
    private String redForceGoal;   // 红方目标
    private String blueForceGoal;  // 蓝方目标
    private boolean aiAssisted;    // 是否使用AI辅助构建
}
