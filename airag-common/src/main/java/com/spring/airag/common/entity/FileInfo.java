package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息实体类
 * 用于表示系统中上传或存储的文件信息
 */
@Data
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String originalName;    // 原始文件名
    private String fileName;        // 存储文件名
    private String filePath;        // 文件路径
    private Long fileSize;          // 文件大小(字节)
    private String fileType;        // 文件类型(MIME)
    private String fileExtension;   // 文件扩展名
    private String storageType;     // 存储类型：local-本地，oss-对象存储等
    private String uploadUserId;    // 上传用户ID
    private String uploadUserName;  // 上传用户名
    private String businessType;    // 业务类型
    private String businessId;      // 业务ID
    private Integer status;         // 状态：0-禁用，1-启用
    private Date createTime;
    private Date updateTime;

    public FileInfo() {
        this.status = 1; // 默认启用
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public FileInfo(String originalName, String fileName, String filePath, Long fileSize) {
        this();
        this.originalName = originalName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}