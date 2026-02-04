package com.spring.airag.data.service;

import com.spring.airag.data.entity.ExerciseSceneEntity;

import java.util.List;

public interface ExerciseSceneService {
    ExerciseSceneEntity saveScene(ExerciseSceneEntity scene);
    ExerciseSceneEntity getSceneById(String id);
    List<ExerciseSceneEntity> getAllScenes();
    List<ExerciseSceneEntity> getScenesByStatus(String status);
    List<ExerciseSceneEntity> getScenesByType(String type);
    ExerciseSceneEntity updateScene(ExerciseSceneEntity scene);
    boolean deleteScene(String id);
}