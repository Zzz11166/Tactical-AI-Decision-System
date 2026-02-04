package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息实体类
 * 对应数据库中的files表
 */
@Data
public class FileInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String originalName;      // 原始文件名
    private String fileName;          // 存储文件名
    private String filePath;          // 文件路径
    private Long fileSize;            // 文件大小(字节)
    private String fileType;          // 文件类型(MIME)
    private String fileExtension;     // 文件扩展名
    private String storageType;       // 存储类型：MINIO, LOCAL, S3等
    private String uploadUserId;      // 上传用户ID
    private String uploadUserName;    // 上传用户名
    private String businessType;      // 业务类型
    private String businessId;        // 业务ID
    private Integer status;           // 状态：1-启用，0-禁用
    private Date createdAt;
    private Date updatedAt;
}