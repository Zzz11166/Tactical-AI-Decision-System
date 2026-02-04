package com.spring.airag.data.service;

import com.spring.airag.data.entity.FileInfoEntity;

import java.util.List;

public interface FileInfoService {
    
    /**
     * 保存文件信息
     */
    FileInfoEntity saveFileInfo(FileInfoEntity fileInfo);
    
    /**
     * 根据ID查询文件信息
     */
    FileInfoEntity getFileInfoById(String id);
    
    /**
     * 查询所有文件信息
     */
    List<FileInfoEntity> getAllFileInfos();
    
    /**
     * 根据业务类型查询文件
     */
    List<FileInfoEntity> getFileInfosByBusinessType(String businessType);
    
    /**
     * 更新文件信息
     */
    FileInfoEntity updateFileInfo(FileInfoEntity fileInfo);
    
    /**
     * 删除文件信息
     */
    boolean deleteFileInfo(String id);
    
    /**
     * 根据文件名查询
     */
    FileInfoEntity getFileInfoByFileName(String fileName);
}