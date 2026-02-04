package com.spring.airag.data.service;

import com.spring.airag.data.entity.FileChunkInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileChunkService {
    
    /**
     * 初始化分块上传
     */
    FileChunkInfo initChunkUpload(String fileId, String fileName, Long fileSize, String fileMd5, Integer totalChunks);
    
    /**
     * 上传分块
     */
    FileChunkInfo uploadChunk(MultipartFile file, String fileId, Integer chunkNumber, String chunkMd5);
    
    /**
     * 检查分块是否已上传
     */
    boolean isChunkUploaded(String fileId, Integer chunkNumber);
    
    /**
     * 获取已上传的分块列表
     */
    List<Integer> getUploadedChunks(String fileId);
    
    /**
     * 合并分块
     */
    boolean mergeChunks(String fileId);
    
    /**
     * 取消上传
     */
    boolean cancelUpload(String fileId);
    
    /**
     * 清理过期的上传记录
     */
    void cleanupExpiredUploads();
}