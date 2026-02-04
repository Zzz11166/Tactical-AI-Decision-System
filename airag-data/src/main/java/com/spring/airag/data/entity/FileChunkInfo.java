package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件分块上传信息实体类
 */
@Data
public class FileChunkInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileId;           // 文件唯一标识
    private String fileName;         // 文件名
    private Long fileSize;           // 文件总大小
    private String fileMd5;          // 文件MD5值
    private Integer chunkNumber;     // 当前分块序号
    private Integer totalChunks;     // 总分块数
    private Long chunkSize;          // 分块大小
    private String chunkMd5;         // 分块MD5值
    private String uploadId;         // MinIO上传ID
    private String bucketName;       // 存储桶名称
    private String objectName;       // 对象名称
    private String status;           // 上传状态：UPLOADING, COMPLETED, FAILED
    private Long uploadedAt;         // 上传时间戳
}