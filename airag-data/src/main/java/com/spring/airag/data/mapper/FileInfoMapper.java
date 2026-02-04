package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.FileInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileInfoMapper {
    
    /**
     * 插入文件信息
     */
    int insert(FileInfoEntity fileInfo);
    
    /**
     * 根据ID查询文件信息
     */
    FileInfoEntity selectById(@Param("id") String id);
    
    /**
     * 查询所有文件信息
     */
    List<FileInfoEntity> selectAll();
    
    /**
     * 根据业务类型查询文件
     */
    List<FileInfoEntity> selectByBusinessType(@Param("businessType") String businessType);
    
    /**
     * 更新文件信息
     */
    int update(FileInfoEntity fileInfo);
    
    /**
     * 根据ID删除文件信息
     */
    int deleteById(@Param("id") String id);
    
    /**
     * 根据文件名查询
     */
    FileInfoEntity selectByFileName(@Param("fileName") String fileName);
}