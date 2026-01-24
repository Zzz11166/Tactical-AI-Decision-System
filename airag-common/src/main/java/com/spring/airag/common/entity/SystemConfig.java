package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置实体类
 * 用于表示系统配置项
 */
@Data
public class SystemConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String configKey;       // 配置键
    private String configValue;     // 配置值
    private String configName;      // 配置名称
    private String configDesc;      // 配置描述
    private String configType;      // 配置类型
    private String groupName;       // 配置组名
    private Integer status;         // 状态：0-禁用，1-启用
    private Integer sort;           // 排序
    private Date createTime;
    private Date updateTime;

    public SystemConfig() {
        this.status = 1; // 默认启用
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public SystemConfig(String configKey, String configValue) {
        this();
        this.configKey = configKey;
        this.configValue = configValue;
    }
}