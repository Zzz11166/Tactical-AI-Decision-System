package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 标绘数据实体类
 */
@Data
public class Annotation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Type type;
    private Position position;
    private String userId;
    private Date createTime;
    private Map<String, Object> properties;  // 额外属性

    /**
     * 标绘类型
     */
    public enum Type {
        POINT,      // 点
        LINE,       // 线
        POLYGON,    // 多边形
        CIRCLE,     // 圆
        ICON,       // 图标
        TEXT        // 文本
    }
}
