package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息实体类
 * 对应数据库中的files表
 */
@Data
public class FileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String filename;
    private String originalName;
    private Long fileSize;
    private String contentType;
    private String bucketName;
    private String objectName;
    private String fileCategory;  // SCENARIO, TACTICAL_CASE, MAP, VIDEO等
    private Integer uploadedBy;   // 对应数据库中的INTEGER REFERENCES users(id)
    private Date uploadedAt;
}