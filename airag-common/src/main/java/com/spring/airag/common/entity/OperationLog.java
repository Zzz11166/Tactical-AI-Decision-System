package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志实体类
 * 用于记录系统中的操作日志
 */
@Data
public class OperationLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;          // 操作用户ID
    private String userName;        // 操作用户名
    private String operation;       // 操作描述
    private String method;          // 请求方法
    private String url;             // 请求地址
    private String params;          // 请求参数
    private String result;          // 返回结果
    private Long time;              // 执行时间(毫秒)
    private String ip;              // IP地址
    private String userAgent;       // 用户代理
    private String exceptionDetail; // 异常详情
    private Date createTime;        // 创建时间

    public OperationLog() {
        this.createTime = new Date();
    }

    public OperationLog(String userId, String userName, String operation, String url) {
        this();
        this.userId = userId;
        this.userName = userName;
        this.operation = operation;
        this.url = url;
    }
}