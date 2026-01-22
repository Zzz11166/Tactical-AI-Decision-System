package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型指令实体类
 */
@Data
public class ModelCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String modelId;
    private String userId;
    private CommandType commandType;
    private Position targetPosition;
    private String targetModelId;
    private Date createTime;
    private String parameters;  // JSON格式的额外参数

    /**
     * 指令类型
     */
    public enum CommandType {
        MOVE,       // 移动
        ATTACK,     // 攻击
        DETECT,     // 侦查
        STOP        // 停止
    }

    public ModelCommand() {
        this.createTime = new Date();
    }

    public ModelCommand(String modelId, String userId, CommandType commandType) {
        this();
        this.modelId = modelId;
        this.userId = userId;
        this.commandType = commandType;
    }
}
