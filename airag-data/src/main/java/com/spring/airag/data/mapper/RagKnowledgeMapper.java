package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.RagKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RagKnowledgeMapper {
    
    /**
     * 插入RAG知识
     */
    int insert(RagKnowledgeEntity knowledge);
    
    /**
     * 根据ID查询知识
     */
    RagKnowledgeEntity selectById(@Param("id") String id);
    
    /**
     * 查询所有知识
     */
    List<RagKnowledgeEntity> selectAll();
    
    /**
     * 根据类别查询知识
     */
    List<RagKnowledgeEntity> selectByCategory(@Param("category") String category);
    
    /**
     * 根据父ID查询知识
     */
    List<RagKnowledgeEntity> selectByParentId(@Param("parentId") String parentId);
    
    /**
     * 更新知识信息
     */
    int update(RagKnowledgeEntity knowledge);
    
    /**
     * 根据ID删除知识
     */
    int deleteById(@Param("id") String id);
}