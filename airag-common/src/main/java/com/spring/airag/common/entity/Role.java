package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 * 用于表示系统中的角色信息
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;      // 角色名称
    private String code;      // 角色编码
    private String description; // 描述
    private Integer level;    // 角色级别
    private Integer status;   // 状态：0-禁用，1-启用
    private Date createTime;
    private Date updateTime;

    public Role() {
        this.status = 1; // 默认启用
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Role(String id, String name, String code) {
        this();
        this.id = id;
        this.name = name;
        this.code = code;
    }
}