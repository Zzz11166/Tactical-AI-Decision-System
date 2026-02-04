package com.spring.airag.data.service;

import com.spring.airag.data.entity.RagKnowledgeEntity;

import java.util.List;

public interface RagKnowledgeService {
    RagKnowledgeEntity saveKnowledge(RagKnowledgeEntity knowledge);
    RagKnowledgeEntity getKnowledgeById(String id);
    List<RagKnowledgeEntity> getAllKnowledges();
    List<RagKnowledgeEntity> getKnowledgesByCategory(String category);
    List<RagKnowledgeEntity> getKnowledgesByParentId(String parentId);
    RagKnowledgeEntity updateKnowledge(RagKnowledgeEntity knowledge);
    boolean deleteKnowledge(String id);
}