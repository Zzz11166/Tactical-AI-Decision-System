package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用基础实体类
 * 所有业务实体类可以继承此类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    protected String id;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;

    /**
     * 创建人ID
     */
    protected String creatorId;

    /**
     * 更新人ID
     */
    protected String updaterId;

    /**
     * 版本号，用于乐观锁
     */
    protected Integer version;

    /**
     * 删除标志位，0-未删除，1-已删除
     */
    protected Integer deleted;

    public BaseEntity() {
        this.createTime = new Date();
        this.updateTime = new Date();
        this.version = 0;
        this.deleted = 0;
    }
}