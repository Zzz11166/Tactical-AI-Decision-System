package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.FileChunkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileChunkMapper {
    
    /**
     * 插入分块信息
     */
    int insert(FileChunkInfo chunkInfo);
    
    /**
     * 根据文件ID和分块序号查询
     */
    FileChunkInfo selectByFileIdAndChunkNumber(@Param("fileId") String fileId, @Param("chunkNumber") Integer chunkNumber);
    
    /**
     * 根据文件ID查询所有分块
     */
    List<FileChunkInfo> selectByFileId(@Param("fileId") String fileId);
    
    /**
     * 根据文件ID和状态查询分块
     */
    List<FileChunkInfo> selectByFileIdAndStatus(@Param("fileId") String fileId, @Param("status") String status);
    
    /**
     * 更新分块信息
     */
    int update(FileChunkInfo chunkInfo);
    
    /**
     * 根据文件ID删除所有分块信息
     */
    int deleteByFileId(@Param("fileId") String fileId);
    
    /**
     * 根据文件ID和分块序号删除
     */
    int deleteByFileIdAndChunkNumber(@Param("fileId") String fileId, @Param("chunkNumber") Integer chunkNumber);
}