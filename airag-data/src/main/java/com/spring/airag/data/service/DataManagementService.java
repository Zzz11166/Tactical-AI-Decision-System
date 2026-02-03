package com.spring.airag.data.service;

import com.spring.airag.common.entity.FileInfo;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 数据管理服务类
 */
@Slf4j
@Service
public class DataManagementService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    /**
     * 上传文件到MinIO
     */
    public FileInfo uploadFile(MultipartFile file) throws Exception {
        // 确保bucket存在
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String contentType = file.getContentType();

        // 上传文件
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(contentType)
                .build();

        minioClient.putObject(putObjectArgs);

        // 创建文件信息对象
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalName(file.getOriginalFilename());
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(bucketName + "/" + fileName);
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFileType(contentType);
        fileInfo.setStorageType("MINIO");

        log.info("文件上传成功: {}", fileName);
        return fileInfo;
    }

    /**
     * 从MinIO下载文件
     */
    public InputStream downloadFile(String fileName) throws Exception {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();

        return minioClient.getObject(getObjectArgs);
    }

    /**
     * 从MinIO删除文件
     */
    public void deleteFile(String fileName) throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();

        minioClient.removeObject(removeObjectArgs);
        log.info("文件删除成功: {}", fileName);
    }

    /**
     * 检查bucket是否存在，不存在则创建
     */
    public void ensureBucketExists() throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("创建MinIO bucket: {}", bucketName);
        }
    }
}