package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * 用于表示系统中的用户信息
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private Integer gender;  // 0-未知，1-男，2-女
    private Date birthday;
    private String departmentId;
    private String departmentName;
    private String position;  // 职位
    private Integer status;   // 0-禁用，1-启用
    private Date lastLoginTime;
    private String lastLoginIp;
    private Date createTime;
    private Date updateTime;

    public User() {
        this.status = 1; // 默认启用
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public User(String id, String username, String nickname) {
        this();
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
}