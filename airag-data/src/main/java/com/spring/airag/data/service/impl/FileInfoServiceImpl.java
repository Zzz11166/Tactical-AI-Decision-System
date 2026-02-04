package com.spring.airag.data.service.impl;

import com.spring.airag.data.entity.FileInfoEntity;
import com.spring.airag.data.mapper.FileInfoMapper;
import com.spring.airag.data.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileInfoServiceImpl implements FileInfoService {
    
    @Autowired
    private FileInfoMapper fileInfoMapper;
    
    @Override
    public FileInfoEntity saveFileInfo(FileInfoEntity fileInfo) {
        try {
            int result = fileInfoMapper.insert(fileInfo);
            if (result > 0) {
                log.info("文件信息保存成功: {}", fileInfo.getFileName());
                return fileInfo;
            }
            return null;
        } catch (Exception e) {
            log.error("保存文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("保存文件信息失败", e);
        }
    }
    
    @Override
    public FileInfoEntity getFileInfoById(String id) {
        try {
            return fileInfoMapper.selectById(id);
        } catch (Exception e) {
            log.error("查询文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("查询文件信息失败", e);
        }
    }
    
    @Override
    public List<FileInfoEntity> getAllFileInfos() {
        try {
            return fileInfoMapper.selectAll();
        } catch (Exception e) {
            log.error("查询所有文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("查询所有文件信息失败", e);
        }
    }
    
    @Override
    public List<FileInfoEntity> getFileInfosByBusinessType(String businessType) {
        try {
            return fileInfoMapper.selectByBusinessType(businessType);
        } catch (Exception e) {
            log.error("根据业务类型查询文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("根据业务类型查询文件信息失败", e);
        }
    }
    
    @Override
    public FileInfoEntity updateFileInfo(FileInfoEntity fileInfo) {
        try {
            int result = fileInfoMapper.update(fileInfo);
            if (result > 0) {
                log.info("文件信息更新成功: {}", fileInfo.getFileName());
                return fileInfo;
            }
            return null;
        } catch (Exception e) {
            log.error("更新文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("更新文件信息失败", e);
        }
    }
    
    @Override
    public boolean deleteFileInfo(String id) {
        try {
            int result = fileInfoMapper.deleteById(id);
            if (result > 0) {
                log.info("文件信息删除成功: {}", id);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("删除文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除文件信息失败", e);
        }
    }
    
    @Override
    public FileInfoEntity getFileInfoByFileName(String fileName) {
        try {
            return fileInfoMapper.selectByFileName(fileName);
        } catch (Exception e) {
            log.error("根据文件名查询文件信息失败: {}", e.getMessage(), e);
            throw new RuntimeException("根据文件名查询文件信息失败", e);
        }
    }
}