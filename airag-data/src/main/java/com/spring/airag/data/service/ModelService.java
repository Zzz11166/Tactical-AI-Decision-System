package com.spring.airag.data.service;

import com.spring.airag.data.entity.ModelEntity;

import java.util.List;

public interface ModelService {
    ModelEntity saveModel(ModelEntity model);
    ModelEntity getModelById(String id);
    List<ModelEntity> getAllModels();
    List<ModelEntity> getModelsBySceneId(String sceneId);
    List<ModelEntity> getModelsBySide(String side);
    List<ModelEntity> getModelsByType(String type);
    List<ModelEntity> getModelsByStatus(String status);
    ModelEntity updateModel(ModelEntity model);
    boolean deleteModel(String id);
}