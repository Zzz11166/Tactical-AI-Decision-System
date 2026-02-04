package com.spring.airag.data.controller;

import com.spring.airag.common.dto.Result;
import com.spring.airag.data.entity.FileInfoEntity;
import com.spring.airag.data.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/data/files")
public class FileInfoController {
    
    @Autowired
    private FileInfoService fileInfoService;
    
    /**
     * 保存文件信息
     */
    @PostMapping
    public Result<FileInfoEntity> saveFileInfo(@RequestBody FileInfoEntity fileInfo) {
        try {
            FileInfoEntity savedFile = fileInfoService.saveFileInfo(fileInfo);
            return Result.success("文件信息保存成功", savedFile);
        } catch (Exception e) {
            log.error("保存文件信息失败", e);
            return Result.error("保存文件信息失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID查询文件信息
     */
    @GetMapping("/{id}")
    public Result<FileInfoEntity> getFileInfoById(@PathVariable String id) {
        try {
            FileInfoEntity fileInfo = fileInfoService.getFileInfoById(id);
            if (fileInfo != null) {
                return Result.success("查询成功", fileInfo);
            } else {
                return Result.error("文件信息不存在");
            }
        } catch (Exception e) {
            log.error("查询文件信息失败", e);
            return Result.error("查询文件信息失败: " + e.getMessage());
        }
    }
    
    /**
     * 查询所有文件信息
     */
    @GetMapping
    public Result<List<FileInfoEntity>> getAllFileInfos() {
        try {
            List<FileInfoEntity> fileInfos = fileInfoService.getAllFileInfos();
            return Result.success("查询成功", fileInfos);
        } catch (Exception e) {
            log.error("查询所有文件信息失败", e);
            return Result.error("查询所有文件信息失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据业务类型查询文件
     */
    @GetMapping("/business/{businessType}")
    public Result<List<FileInfoEntity>> getFileInfosByBusinessType(@PathVariable String businessType) {
        try {
            List<FileInfoEntity> fileInfos = fileInfoService.getFileInfosByBusinessType(businessType);
            return Result.success("查询成功", fileInfos);
        } catch (Exception e) {
            log.error("根据业务类型查询文件信息失败", e);
            return Result.error("根据业务类型查询文件信息失败: " + e.getMessage());
        }
    }
    
    /**
     * 更新文件信息
     */
    @PutMapping("/{id}")
    public Result<FileInfoEntity> updateFileInfo(@PathVariable String id, @RequestBody FileInfoEntity fileInfo) {
        try {
            fileInfo.setId(id);
            FileInfoEntity updatedFile = fileInfoService.updateFileInfo(fileInfo);
            if (updatedFile != null) {
                return Result.success("文件信息更新成功", updatedFile);
            } else {
                return Result.error("文件信息更新失败");
            }
        } catch (Exception e) {
            log.error("更新文件信息失败", e);
            return Result.error("更新文件信息失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除文件信息
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteFileInfo(@PathVariable String id) {
        try {
            boolean result = fileInfoService.deleteFileInfo(id);
            if (result) {
                return Result.success("文件信息删除成功", true);
            } else {
                return Result.error("文件信息删除失败");
            }
        } catch (Exception e) {
            log.error("删除文件信息失败", e);
            return Result.error("删除文件信息失败: " + e.getMessage());
        }
    }
}