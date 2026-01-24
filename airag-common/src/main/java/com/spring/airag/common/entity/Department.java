package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门实体类
 * 用于表示组织架构中的部门信息
 */
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String parentId;  // 父部门ID
    private String name;      // 部门名称
    private String code;      // 部门编码
    private String description; // 描述
    private Integer level;    // 层级
    private String leaderId;  // 部门负责人ID
    private String leaderName; // 部门负责人姓名
    private String contactPhone; // 联系电话
    private Integer sort;     // 排序
    private Integer status;   // 状态：0-禁用，1-启用
    private Date createTime;
    private Date updateTime;

    public Department() {
        this.status = 1; // 默认启用
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Department(String id, String name, String parentId) {
        this();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}