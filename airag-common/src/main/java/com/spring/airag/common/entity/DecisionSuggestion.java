package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 决策建议实体类
 */
@Data
public class DecisionSuggestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String side;
    private Date timestamp;
    private String situationSummary;
    private List<DecisionAction> actions;
    private List<ThreatAssessment> threats;
    private String reasoning;
    private Double confidence;

    /**
     * 决策行动类
     */
    @Data
    public static class DecisionAction implements Serializable {
        private static final long serialVersionUID = 1L;

        private String id;
        private String actionType;  // 进攻、防守、侦察等
        private String modelId;
        private String modelName;
        private String description;
        private Integer priority;  // 1-高优先级, 2-中优先级, 3-低优先级
        private Double confidence;
        private Position targetPosition;
        private String targetModelId;
        private String parameters;  // JSON格式的额外参数
    }

    /**
     * 威胁评估类
     */
    @Data
    public static class ThreatAssessment implements Serializable {
        private static final long serialVersionUID = 1L;

        private String threatId;
        private String threatType;  // 坦克、步兵、火炮等
        private String threatModelId;
        private String threatModelName;
        private Position threatPosition;
        private Double threatLevel;  // 威胁等级 0-1
        private String affectedModelId;
        private String affectedModelName;
        private String description;
        private String recommendedAction;
    }
}
