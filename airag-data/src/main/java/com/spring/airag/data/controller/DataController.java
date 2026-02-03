package com.spring.airag.data.controller;

import com.spring.airag.common.dto.Result;
import com.spring.airag.common.entity.FileInfo;
import com.spring.airag.data.service.DataManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 数据管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataManagementService dataManagementService;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public Result<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 确保bucket存在
            dataManagementService.ensureBucketExists();
            
            FileInfo fileInfo = dataManagementService.uploadFile(file);
            return Result.success("文件上传成功", fileInfo);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 下载文件
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        try {
            InputStream inputStream = dataManagementService.downloadFile(fileName);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(inputStream.available())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            log.error("文件下载失败", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete/{fileName}")
    public Result<Boolean> deleteFile(@PathVariable String fileName) {
        try {
            dataManagementService.deleteFile(fileName);
            return Result.success("文件删除成功", true);
        } catch (Exception e) {
            log.error("文件删除失败", e);
            return Result.error("文件删除失败: " + e.getMessage());
        }
    }

    /**
     * 检查服务状态
     */
    @GetMapping("/health")
    public Result<String> healthCheck() {
        return Result.success("数据服务运行正常");
    }
}