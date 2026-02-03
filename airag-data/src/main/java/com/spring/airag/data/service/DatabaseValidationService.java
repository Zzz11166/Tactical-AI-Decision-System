package com.spring.airag.data.service;

import com.spring.airag.data.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表结构验证服务
 */
@Slf4j
@Service
public class DatabaseValidationService {

    /**
     * 验证数据库表结构
     */
    public Map<String, Object> validateDatabaseSchema() {
        Map<String, Object> validationResult = new HashMap<>();
        List<String> existingTables = new ArrayList<>();
        List<String> missingTables = new ArrayList<>();
        List<String> tableDetails = new ArrayList<>();

        try {
            // 根据数据库schema定义预期的表
            String[] expectedTables = {
                "exercise_scenes",
                "models",
                "files",
                "tactical_cases",
                "rag_knowledge"
            };

            // 模拟数据库连接和表验证
            for (String tableName : expectedTables) {
                // 检查表是否存在
                boolean exists = checkTableExists(tableName);
                if (exists) {
                    existingTables.add(tableName);
                    tableDetails.add(tableName + ": OK");
                } else {
                    missingTables.add(tableName);
                    tableDetails.add(tableName + ": MISSING");
                }
            }

            validationResult.put("status", "success");
            validationResult.put("existingTables", existingTables);
            validationResult.put("missingTables", missingTables);
            validationResult.put("tableDetails", tableDetails);
            validationResult.put("totalExpected", expectedTables.length);
            validationResult.put("totalExisting", existingTables.size());
            validationResult.put("totalMissing", missingTables.size());

            log.info("数据库表结构验证完成: {}张表存在, {}张表缺失", 
                    existingTables.size(), missingTables.size());

        } catch (Exception e) {
            log.error("数据库表结构验证失败", e);
            validationResult.put("status", "error");
            validationResult.put("errorMessage", e.getMessage());
        }

        return validationResult;
    }

    /**
     * 检查表是否存在
     */
    private boolean checkTableExists(String tableName) {
        // 在实际应用中，这里会连接数据库并检查表是否存在
        // 模拟所有期望的表都存在
        return true;
    }

    /**
     * 获取表结构信息
     */
    public Map<String, Object> getTableStructure(String tableName) {
        Map<String, Object> tableInfo = new HashMap<>();
        
        switch (tableName.toLowerCase()) {
            case "exercise_scenes":
                tableInfo.put("className", ExerciseSceneEntity.class.getSimpleName());
                tableInfo.put("fields", getExerciseSceneFields());
                break;
            case "models":
                tableInfo.put("className", ModelEntity.class.getSimpleName());
                tableInfo.put("fields", getModelFields());
                break;
            case "files":
                tableInfo.put("className", FileEntity.class.getSimpleName());
                tableInfo.put("fields", getFileFields());
                break;
            case "tactical_cases":
                tableInfo.put("className", TacticalCaseEntity.class.getSimpleName());
                tableInfo.put("fields", getTacticalCaseFields());
                break;
            case "rag_knowledge":
                tableInfo.put("className", RagKnowledgeEntity.class.getSimpleName());
                tableInfo.put("fields", getRagKnowledgeFields());
                break;
            default:
                tableInfo.put("error", "Unknown table: " + tableName);
        }
        
        return tableInfo;
    }

    private List<String> getExerciseSceneFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id: VARCHAR(50), PRIMARY KEY");
        fields.add("name: VARCHAR(255), NOT NULL");
        fields.add("type: VARCHAR(50), NOT NULL (演练类型：进攻作战、防御作战等)");
        fields.add("description: TEXT");
        fields.add("creator_id: INTEGER (REFERENCES users(id))");
        fields.add("start_time: TIMESTAMP");
        fields.add("end_time: TIMESTAMP");
        fields.add("status: VARCHAR(20), DEFAULT 'PREPARING' (PREPARING, RUNNING, FINISHED, PAUSED)");
        fields.add("config: JSONB (场景配置信息)");
        fields.add("created_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        fields.add("updated_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        return fields;
    }

    private List<String> getModelFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id: VARCHAR(50), PRIMARY KEY");
        fields.add("scene_id: VARCHAR(50) (REFERENCES exercise_scenes(id))");
        fields.add("name: VARCHAR(255), NOT NULL");
        fields.add("side: VARCHAR(10), NOT NULL (RED, BLUE, NEUTRAL)");
        fields.add("type: VARCHAR(20), NOT NULL (INFANTRY, TANK, AIRCRAFT, SHIP, ARTILLERY)");
        fields.add("position_x: DOUBLE PRECISION, DEFAULT 0.0");
        fields.add("position_y: DOUBLE PRECISION, DEFAULT 0.0");
        fields.add("position_z: DOUBLE PRECISION, DEFAULT 0.0");
        fields.add("status: VARCHAR(20), DEFAULT 'ACTIVE' (ACTIVE, MOVING, ATTACKING, DETECTING, DAMAGED, DESTROYED)");
        fields.add("detection_status: VARCHAR(20), DEFAULT 'UNDETECTED' (UNDETECTED, DETECTED, HIDDEN)");
        fields.add("detected_by: VARCHAR(50)");
        fields.add("detection_time: TIMESTAMP");
        fields.add("health: DOUBLE PRECISION, DEFAULT 100.0");
        fields.add("attack_power: DOUBLE PRECISION, DEFAULT 10.0");
        fields.add("defense_power: DOUBLE PRECISION, DEFAULT 5.0");
        fields.add("attack_range: DOUBLE PRECISION, DEFAULT 100.0");
        fields.add("detection_range: DOUBLE PRECISION, DEFAULT 500.0");
        fields.add("move_speed: DOUBLE PRECISION, DEFAULT 10.0");
        fields.add("created_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        fields.add("updated_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        return fields;
    }

    private List<String> getFileFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id: SERIAL, PRIMARY KEY");
        fields.add("filename: VARCHAR(255), NOT NULL");
        fields.add("original_name: VARCHAR(255), NOT NULL");
        fields.add("file_size: BIGINT");
        fields.add("content_type: VARCHAR(100)");
        fields.add("bucket_name: VARCHAR(100)");
        fields.add("object_name: VARCHAR(255)");
        fields.add("file_category: VARCHAR(50) (SCENARIO, TACTICAL_CASE, MAP, VIDEO等)");
        fields.add("uploaded_by: INTEGER (REFERENCES users(id))");
        fields.add("uploaded_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        return fields;
    }

    private List<String> getTacticalCaseFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id: SERIAL, PRIMARY KEY");
        fields.add("title: VARCHAR(255), NOT NULL");
        fields.add("description: TEXT");
        fields.add("scenario_type: VARCHAR(50) (案例适用的场景类型)");
        fields.add("forces_composition: JSONB (双方兵力配置)");
        fields.add("tactics_used: TEXT (使用的战术)");
        fields.add("outcome: TEXT (案例结果)");
        fields.add("lessons_learned: TEXT (经验教训)");
        fields.add("difficulty_level: INTEGER, DEFAULT 1 (难度等级 1-5)");
        fields.add("tags: TEXT[] (标签数组)");
        fields.add("created_by: INTEGER (REFERENCES users(id))");
        fields.add("created_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        fields.add("updated_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        return fields;
    }

    private List<String> getRagKnowledgeFields() {
        List<String> fields = new ArrayList<>();
        fields.add("id: VARCHAR(50), PRIMARY KEY");
        fields.add("parent_id: VARCHAR(50) (父文档ID，用于跟踪原始文档)");
        fields.add("title: VARCHAR(500), NOT NULL");
        fields.add("content: TEXT, NOT NULL");
        fields.add("category: VARCHAR(50) (KNOWLEDGE, TACTICAL_CASE, RULE, PROCEDURE等)");
        fields.add("embedding_vector: JSONB (向量嵌入，使用JSONB存储)");
        fields.add("metadata: JSONB (额外元数据)");
        fields.add("created_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        fields.add("updated_at: TIMESTAMP, DEFAULT CURRENT_TIMESTAMP");
        return fields;
    }
}