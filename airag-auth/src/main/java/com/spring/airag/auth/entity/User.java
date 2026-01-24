package com.spring.airag.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类 - 对应数据库中的users表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String role = "USER";

    private UserStatus status = UserStatus.ACTIVE;

    private Date createdAt;

    private Date updatedAt;

    public enum UserStatus {
        ACTIVE, INACTIVE
    }
}