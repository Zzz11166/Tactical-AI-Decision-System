package com.spring.airag.data.controller;

import com.spring.airag.common.dto.Result;
import com.spring.airag.data.entity.FileChunkInfo;
import com.spring.airag.data.service.FileChunkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/data/chunk")
public class FileChunkController {
    
    @Autowired
    private FileChunkService fileChunkService;
    
    /**
     * 初始化分块上传
     */
    @PostMapping("/init")
    public Result<FileChunkInfo> initChunkUpload(
            @RequestParam String fileName,
            @RequestParam Long fileSize,
            @RequestParam String fileMd5,
            @RequestParam Integer totalChunks) {
        try {
            String fileId = UUID.randomUUID().toString();
            FileChunkInfo chunkInfo = fileChunkService.initChunkUpload(fileId, fileName, fileSize, fileMd5, totalChunks);
            return Result.success("初始化分块上传成功", chunkInfo);
        } catch (Exception e) {
            log.error("初始化分块上传失败", e);
            return Result.error("初始化分块上传失败: " + e.getMessage());
        }
    }
    
    /**
     * 上传分块
     */
    @PostMapping("/upload")
    public Result<FileChunkInfo> uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam String fileId,
            @RequestParam Integer chunkNumber,
            @RequestParam String chunkMd5) {
        try {
            FileChunkInfo chunkInfo = fileChunkService.uploadChunk(file, fileId, chunkNumber, chunkMd5);
            return Result.success("分块上传成功", chunkInfo);
        } catch (Exception e) {
            log.error("分块上传失败", e);
            return Result.error("分块上传失败: " + e.getMessage());
        }
    }
    
    /**
     * 检查分块是否已上传
     */
    @GetMapping("/check/{fileId}/{chunkNumber}")
    public Result<Boolean> checkChunkUploaded(@PathVariable String fileId, @PathVariable Integer chunkNumber) {
        try {
            boolean uploaded = fileChunkService.isChunkUploaded(fileId, chunkNumber);
            return Result.success("检查成功", uploaded);
        } catch (Exception e) {
            log.error("检查分块上传状态失败", e);
            return Result.error("检查分块上传状态失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取已上传的分块列表
     */
    @GetMapping("/uploaded/{fileId}")
    public Result<List<Integer>> getUploadedChunks(@PathVariable String fileId) {
        try {
            List<Integer> chunks = fileChunkService.getUploadedChunks(fileId);
            return Result.success("获取成功", chunks);
        } catch (Exception e) {
            log.error("获取已上传分块列表失败", e);
            return Result.error("获取已上传分块列表失败: " + e.getMessage());
        }
    }
    
    /**
     * 合并分块
     */
    @PostMapping("/merge/{fileId}")
    public Result<Boolean> mergeChunks(@PathVariable String fileId) {
        try {
            boolean result = fileChunkService.mergeChunks(fileId);
            if (result) {
                return Result.success("分块合并成功", true);
            } else {
                return Result.error("分块合并失败");
            }
        } catch (Exception e) {
            log.error("分块合并失败", e);
            return Result.error("分块合并失败: " + e.getMessage());
        }
    }
    
    /**
     * 取消上传
     */
    @DeleteMapping("/cancel/{fileId}")
    public Result<Boolean> cancelUpload(@PathVariable String fileId) {
        try {
            boolean result = fileChunkService.cancelUpload(fileId);
            if (result) {
                return Result.success("取消上传成功", true);
            } else {
                return Result.error("取消上传失败");
            }
        } catch (Exception e) {
            log.error("取消上传失败", e);
            return Result.error("取消上传失败: " + e.getMessage());
        }
    }
}