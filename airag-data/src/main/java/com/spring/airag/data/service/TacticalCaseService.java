package com.spring.airag.data.service;

import com.spring.airag.data.entity.TacticalCaseEntity;

import java.util.List;

public interface TacticalCaseService {
    TacticalCaseEntity saveTacticalCase(TacticalCaseEntity tacticalCase);
    TacticalCaseEntity getTacticalCaseById(String id);
    List<TacticalCaseEntity> getAllTacticalCases();
    List<TacticalCaseEntity> getTacticalCasesByScenarioType(String scenarioType);
    List<TacticalCaseEntity> getTacticalCasesByDifficultyLevel(Integer difficultyLevel);
    TacticalCaseEntity updateTacticalCase(TacticalCaseEntity tacticalCase);
    boolean deleteTacticalCase(String id);
}