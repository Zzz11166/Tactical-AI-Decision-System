package com.spring.airag.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户会话实体类 - 对应数据库中的user_sessions表
 */
@Data
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String sessionToken;

    private Date expiresAt;

    private Date createdAt;

    private Date updatedAt;
}