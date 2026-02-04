package com.spring.airag.data.service.impl;

import com.spring.airag.data.entity.FileChunkInfo;
import com.spring.airag.data.mapper.FileChunkMapper;
import com.spring.airag.data.service.FileChunkService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileChunkServiceImpl implements FileChunkService {
    
    @Autowired
    private MinioClient minioClient;
    
    @Autowired
    private FileChunkMapper fileChunkMapper;
    
    @Value("${minio.bucket-name}")
    private String bucketName;
    
    private static final long CHUNK_SIZE = 100 * 1024 * 1024; // 100MB
    
    @Override
    public FileChunkInfo initChunkUpload(String fileId, String fileName, Long fileSize, String fileMd5, Integer totalChunks) {
        try {
            // 直接创建文件信息记录，不使用multipart upload
            FileChunkInfo chunkInfo = new FileChunkInfo();
            chunkInfo.setFileId(fileId);
            chunkInfo.setFileName(fileName);
            chunkInfo.setFileSize(fileSize);
            chunkInfo.setFileMd5(fileMd5);
            chunkInfo.setTotalChunks(totalChunks);
            chunkInfo.setUploadId(UUID.randomUUID().toString()); // 生成唯一标识
            chunkInfo.setBucketName(bucketName);
            chunkInfo.setObjectName(fileId + "_" + fileName);
            chunkInfo.setStatus("INITIALIZED");
            chunkInfo.setUploadedAt(System.currentTimeMillis());
            
            fileChunkMapper.insert(chunkInfo);
            
            log.info("初始化文件上传成功: fileId={}, fileName={}", fileId, fileName);
            return chunkInfo;
        } catch (Exception e) {
            log.error("初始化文件上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("初始化文件上传失败", e);
        }
    }
    
    @Override
    @Transactional
    public FileChunkInfo uploadChunk(MultipartFile file, String fileId, Integer chunkNumber, String chunkMd5) {
        try {
            FileChunkInfo chunkInfo = fileChunkMapper.selectByFileIdAndChunkNumber(fileId, chunkNumber);
            if (chunkInfo == null) {
                throw new RuntimeException("文件信息不存在: fileId=" + fileId);
            }
            
            if ("COMPLETED".equals(chunkInfo.getStatus())) {
                log.info("分块已上传: fileId={}, chunkNumber={}", fileId, chunkNumber);
                return chunkInfo;
            }
            
            // 上传分块到MinIO - 使用标准的PutObject API
            String chunkObjectName = chunkInfo.getFileId() + "_chunk_" + chunkNumber;
            InputStream inputStream = file.getInputStream();
            
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(chunkObjectName)
                    .stream(inputStream, file.getSize(), -1)
                    .build()
            );
            
            // 更新分块信息
            chunkInfo.setChunkNumber(chunkNumber);
            chunkInfo.setChunkSize(file.getSize());
            chunkInfo.setChunkMd5(chunkMd5);
            chunkInfo.setStatus("UPLOADED");
            chunkInfo.setUploadedAt(System.currentTimeMillis());
            
            fileChunkMapper.update(chunkInfo);
            
            log.info("分块上传成功: fileId={}, chunkNumber={}", fileId, chunkNumber);
            return chunkInfo;
        } catch (Exception e) {
            log.error("分块上传失败: fileId={}, chunkNumber={}", fileId, chunkNumber, e);
            throw new RuntimeException("分块上传失败", e);
        }
    }
    
    @Override
    public boolean isChunkUploaded(String fileId, Integer chunkNumber) {
        try {
            FileChunkInfo chunkInfo = fileChunkMapper.selectByFileIdAndChunkNumber(fileId, chunkNumber);
            return chunkInfo != null && "UPLOADED".equals(chunkInfo.getStatus());
        } catch (Exception e) {
            log.error("检查分块上传状态失败: fileId={}, chunkNumber={}", fileId, chunkNumber, e);
            return false;
        }
    }
    
    @Override
    public List<Integer> getUploadedChunks(String fileId) {
        try {
            List<FileChunkInfo> chunks = fileChunkMapper.selectByFileIdAndStatus(fileId, "UPLOADED");
            List<Integer> chunkNumbers = new ArrayList<>();
            for (FileChunkInfo chunk : chunks) {
                chunkNumbers.add(chunk.getChunkNumber());
            }
            return chunkNumbers;
        } catch (Exception e) {
            log.error("获取已上传分块列表失败: fileId={}", fileId, e);
            return new ArrayList<>();
        }
    }
    
    @Override
    @Transactional
    public boolean mergeChunks(String fileId) {
        try {
            // 获取所有已上传的分块
            List<FileChunkInfo> chunks = fileChunkMapper.selectByFileIdAndStatus(fileId, "UPLOADED");
            if (chunks.isEmpty()) {
                log.warn("没有找到已上传的分块: fileId={}", fileId);
                return false;
            }
            
            FileChunkInfo fileInfo = fileChunkMapper.selectByFileId(fileId).get(0);
            String finalObjectName = fileInfo.getObjectName();
            
            // 这里简化处理：假设只需要标记文件为完成状态
            // 实际项目中可能需要真正的文件合并逻辑
            fileInfo.setStatus("COMPLETED");
            fileChunkMapper.update(fileInfo);
            
            // 更新所有分块状态
            for (FileChunkInfo chunk : chunks) {
                chunk.setStatus("MERGED");
                fileChunkMapper.update(chunk);
            }
            
            log.info("文件合并标记成功: fileId={}", fileId);
            return true;
        } catch (Exception e) {
            log.error("文件合并失败: fileId={}", fileId, e);
            throw new RuntimeException("文件合并失败", e);
        }
    }
    
    @Override
    @Transactional
    public boolean cancelUpload(String fileId) {
        try {
            // 删除所有相关的分块记录
            fileChunkMapper.deleteByFileId(fileId);
            
            log.info("取消上传成功: fileId={}", fileId);
            return true;
        } catch (Exception e) {
            log.error("取消上传失败: fileId={}", fileId, e);
            return false;
        }
    }
    
    @Override
    public void cleanupExpiredUploads() {
        // TODO: 实现过期上传记录清理逻辑
        log.info("清理过期上传记录");
    }
}