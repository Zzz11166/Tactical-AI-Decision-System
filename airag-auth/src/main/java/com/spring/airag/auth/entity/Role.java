package com.spring.airag.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类 - 对应数据库中的roles表
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String roleName;

    private String description;

    private Date createdAt;
}