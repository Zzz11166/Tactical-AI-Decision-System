package com.spring.airag.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * RAG知识库实体类
 * 对应数据库中的rag_knowledge表
 */
@Data
public class RagKnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String parentId;           // 父文档ID，用于跟踪原始文档
    private String title;
    private String content;
    private String category;           // KNOWLEDGE, TACTICAL_CASE, RULE, PROCEDURE等
    private Object embeddingVector;    // 向量嵌入，使用JSONB存储
    private Object metadata;           // 额外元数据 (JSONB)
    private Date createdAt;
    private Date updatedAt;
}