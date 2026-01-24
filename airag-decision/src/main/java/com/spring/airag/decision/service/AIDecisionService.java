package com.spring.airag.decision.service;

import com.spring.airag.common.entity.DecisionSuggestion;
import com.spring.airag.common.entity.Model;

import com.spring.airag.decision.feign.DataFeignClient;
import com.spring.airag.decision.feign.SituationFeignClient;
import com.spring.airag.decision.rag.RAGService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * AI辅助决策服务类
 */
@Slf4j
@Service
public class AIDecisionService {

    @Autowired
    private RAGService ragService;

    @Autowired
    private SituationFeignClient situationFeignClient;

    @Autowired
    private DataFeignClient dataFeignClient;

    @Value("${airag.decision.ai-model-type}")
    private String aiModelType;

    @Value("${airag.decision.decision-confidence-threshold}")
    private Double decisionConfidenceThreshold;

    /**
     * 生成AI决策建议
     */
    public DecisionSuggestion generateDecisionSuggestion(String sceneId, String side) {
        try {
            // 1. 获取当前态势数据
            Map<String, Model> models = situationFeignClient.getCurrentSituation(sceneId, side);

            // 2. 从RAG知识库检索相关战术案例
            String tacticalCaseQuery = generateTacticalQuery(models, side);
            List<Map<String, Object>> tacticalCases = ragService.hybridSearch(tacticalCaseQuery, 5);

            // 3. 基于态势和战术案例生成决策建议
            DecisionSuggestion suggestion = analyzeSituationAndGenerateSuggestion(models, side, tacticalCases);

            // 4. 保存决策日志
            saveDecisionLog(sceneId, side, suggestion);

            log.info("生成AI决策建议: 场景ID={}, 方={}, 置信度={}", sceneId, side, suggestion.getConfidence());

            return suggestion;
        } catch (Exception e) {
            log.error("生成AI决策建议失败: 场景ID={}, 方={}", sceneId, side, e);
            return createFallbackSuggestion(sceneId, side);
        }
    }

    /**
     * 生成战术查询语句
     */
    private String generateTacticalQuery(Map<String, Model> models, String side) {
        StringBuilder query = new StringBuilder();

        // 统计双方兵力
        Map<Model.Type, Integer> ownTypeCount = new HashMap<>();
        Map<Model.Type, Integer> enemyTypeCount = new HashMap<>();
        double ownAverageHealth = 0;
        double enemyAverageHealth = 0;
        int ownCount = 0;
        int enemyCount = 0;

        for (Model model : models.values()) {
            if (side.equals(model.getSide())) {
                ownTypeCount.put(model.getType(), ownTypeCount.getOrDefault(model.getType(), 0) + 1);
                ownAverageHealth += model.getHealth();
                ownCount++;
            } else {
                enemyTypeCount.put(model.getType(), enemyTypeCount.getOrDefault(model.getType(), 0) + 1);
                enemyAverageHealth += model.getHealth();
                enemyCount++;
            }
        }

        if (ownCount > 0) {
            ownAverageHealth /= ownCount;
        }
        if (enemyCount > 0) {
            enemyAverageHealth /= enemyCount;
        }

        // 构建查询语句
        query.append("我方兵力构成: ");
        for (Map.Entry<Model.Type, Integer> entry : ownTypeCount.entrySet()) {
            query.append(entry.getKey()).append(": ").append(entry.getValue()).append("个, ");
        }
        query.append("平均健康值: ").append(String.format("%.1f", ownAverageHealth)).append("%; ");

        query.append("敌方兵力构成: ");
        for (Map.Entry<Model.Type, Integer> entry : enemyTypeCount.entrySet()) {
            query.append(entry.getKey()).append(": ").append(entry.getValue()).append("个, ");
        }
        query.append("平均健康值: ").append(String.format("%.1f", enemyAverageHealth)).append("%; ");

        return query.toString();
    }

    /**
     * 分析态势并生成建议
     */
    private DecisionSuggestion analyzeSituationAndGenerateSuggestion(
            Map<String, Model> models, String side, List<Map<String, Object>> tacticalCases) {
        
        DecisionSuggestion suggestion = new DecisionSuggestion();
        suggestion.setSide(side);
        suggestion.setTimestamp(new Date());
        suggestion.setSituationSummary(generateSituationSummary(models));
        suggestion.setActions(generateDecisionActions(models, side));
        suggestion.setThreats(analyzeThreats(models, side));
        suggestion.setReasoning(generateReasoning(models, tacticalCases));
        suggestion.setConfidence(calculateConfidence(models));

        return suggestion;
    }

    /**
     * 生成态势摘要
     */
    private String generateSituationSummary(Map<String, Model> models) {
        Map<String, Integer> sideCount = new HashMap<>();
        Map<Model.Type, Integer> typeCount = new HashMap<>();
        double totalHealth = 0;
        int activeCount = 0;

        for (Model model : models.values()) {
            sideCount.put(model.getSide(), sideCount.getOrDefault(model.getSide(), 0) + 1);
            typeCount.put(model.getType(), typeCount.getOrDefault(model.getType(), 0) + 1);
            totalHealth += model.getHealth();
            if (model.getStatus() == Model.Status.ACTIVE) {
                activeCount++;
            }
        }

        StringBuilder summary = new StringBuilder();
        summary.append("当前态势: ");

        for (Map.Entry<String, Integer> entry : sideCount.entrySet()) {
            summary.append(entry.getKey()).append("方").append(entry.getValue()).append("个单位, ");
        }

        for (Map.Entry<Model.Type, Integer> entry : typeCount.entrySet()) {
            summary.append(entry.getKey()).append(" ").append(entry.getValue()).append("个, ");
        }

        summary.append("平均健康值 ").append(String.format("%.1f", totalHealth / models.size())).append("%, ");
        summary.append("活跃单位 ").append(activeCount).append("个");

        return summary.toString();
    }

    /**
     * 生成决策行动
     */
    private List<DecisionSuggestion.DecisionAction> generateDecisionActions(Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 遍历我方单位，为每个单位生成可能的行动
        for (Model model : models.values()) {
            if (!side.equals(model.getSide())) {
                continue;
            }

            // 根据单位类型生成相应行动
            switch (model.getType()) {
                case TANK:
                    // 坦克单位：进攻、防御、支援
                    actions.addAll(generateTankActions(model, models, side));
                    break;
                case AIRCRAFT:
                    // 飞机单位：空中打击、侦察、护航
                    actions.addAll(generateAircraftActions(model, models, side));
                    break;
                case INFANTRY:
                    // 步兵单位：占领要点、防御、机动
                    actions.addAll(generateInfantryActions(model, models, side));
                    break;
                case ARTILLERY:
                    // 火炮单位：火力支援、反炮兵、区域封锁
                    actions.addAll(generateArtilleryActions(model, models, side));
                    break;
                case SHIP:
                    // 舰船单位：海上封锁、火力支援、运输
                    actions.addAll(generateShipActions(model, models, side));
                    break;
            }
        }

        // 按优先级排序
        actions.sort((a, b) -> Integer.compare(b.getPriority(), a.getPriority()));

        return actions;
    }

    /**
     * 为坦克生成行动
     */
    private List<DecisionSuggestion.DecisionAction> generateTankActions(Model tank, Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 查找最近的敌方单位
        Model nearestEnemy = findNearestEnemy(tank, models, side);

        if (nearestEnemy != null) {
            double distance = calculateDistance(tank.getPosition(), nearestEnemy.getPosition());
            
            if (distance <= tank.getAttackRange()) {
                // 在攻击范围内，执行攻击
                DecisionSuggestion.DecisionAction attackAction = new DecisionSuggestion.DecisionAction();
                attackAction.setId(UUID.randomUUID().toString());
                attackAction.setActionType("ATTACK");
                attackAction.setModelId(tank.getId());
                attackAction.setModelName(tank.getName());
                attackAction.setDescription("对敌方单位" + nearestEnemy.getName() + "进行攻击");
                attackAction.setPriority(1); // 高优先级
                attackAction.setConfidence(0.8);
                attackAction.setTargetModelId(nearestEnemy.getId());
                
                actions.add(attackAction);
            } else {
                // 移动接近敌人
                DecisionSuggestion.DecisionAction moveAction = new DecisionSuggestion.DecisionAction();
                moveAction.setId(UUID.randomUUID().toString());
                moveAction.setActionType("MOVE");
                moveAction.setModelId(tank.getId());
                moveAction.setModelName(tank.getName());
                moveAction.setDescription("向敌方单位" + nearestEnemy.getName() + "移动");
                moveAction.setPriority(2); // 中优先级
                moveAction.setConfidence(0.7);
                moveAction.setTargetPosition(nearestEnemy.getPosition());
                
                actions.add(moveAction);
            }
        }

        return actions;
    }

    /**
     * 为飞机生成行动
     */
    private List<DecisionSuggestion.DecisionAction> generateAircraftActions(Model aircraft, Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 查找需要侦察的区域或敌方单位
        Model nearestEnemy = findNearestEnemy(aircraft, models, side);

        if (nearestEnemy != null) {
            DecisionSuggestion.DecisionAction reconAction = new DecisionSuggestion.DecisionAction();
            reconAction.setId(UUID.randomUUID().toString());
            reconAction.setActionType("RECONNAISSANCE");
            reconAction.setModelId(aircraft.getId());
            reconAction.setModelName(aircraft.getName());
            reconAction.setDescription("对敌方区域进行侦察");
            reconAction.setPriority(2); // 中优先级
            reconAction.setConfidence(0.75);
            
            actions.add(reconAction);
        }

        return actions;
    }

    /**
     * 为步兵生成行动
     */
    private List<DecisionSuggestion.DecisionAction> generateInfantryActions(Model infantry, Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 查找需要占领的关键位置
        DecisionSuggestion.DecisionAction occupyAction = new DecisionSuggestion.DecisionAction();
        occupyAction.setId(UUID.randomUUID().toString());
        occupyAction.setActionType("OCCUPY");
        occupyAction.setModelId(infantry.getId());
        occupyAction.setModelName(infantry.getName());
        occupyAction.setDescription("占领关键位置");
        occupyAction.setPriority(2); // 中优先级
        occupyAction.setConfidence(0.7);
        
        actions.add(occupyAction);

        return actions;
    }

    /**
     * 为火炮生成行动
     */
    private List<DecisionSuggestion.DecisionAction> generateArtilleryActions(Model artillery, Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 查找敌方集群目标
        Model clusterTarget = findClusterTarget(artillery, models, side);

        if (clusterTarget != null) {
            DecisionSuggestion.DecisionAction fireSupportAction = new DecisionSuggestion.DecisionAction();
            fireSupportAction.setId(UUID.randomUUID().toString());
            fireSupportAction.setActionType("FIRE_SUPPORT");
            fireSupportAction.setModelId(artillery.getId());
            fireSupportAction.setModelName(artillery.getName());
            fireSupportAction.setDescription("对敌方集群目标进行火力支援");
            fireSupportAction.setPriority(1); // 高优先级
            fireSupportAction.setConfidence(0.85);
            fireSupportAction.setTargetModelId(clusterTarget.getId());
            
            actions.add(fireSupportAction);
        }

        return actions;
    }

    /**
     * 为舰船生成行动
     */
    private List<DecisionSuggestion.DecisionAction> generateShipActions(Model ship, Map<String, Model> models, String side) {
        List<DecisionSuggestion.DecisionAction> actions = new ArrayList<>();

        // 海上封锁或巡逻
        DecisionSuggestion.DecisionAction patrolAction = new DecisionSuggestion.DecisionAction();
        patrolAction.setId(UUID.randomUUID().toString());
        patrolAction.setActionType("PATROL");
        patrolAction.setModelId(ship.getId());
        patrolAction.setModelName(ship.getName());
        patrolAction.setDescription("执行海上巡逻任务");
        patrolAction.setPriority(2); // 中优先级
        patrolAction.setConfidence(0.65);
        
        actions.add(patrolAction);

        return actions;
    }

    /**
     * 分析威胁
     */
    private List<DecisionSuggestion.ThreatAssessment> analyzeThreats(Map<String, Model> models, String side) {
        List<DecisionSuggestion.ThreatAssessment> threats = new ArrayList<>();

        // 分析敌方对我方单位的潜在威胁
        for (Model enemyModel : models.values()) {
            if (side.equals(enemyModel.getSide())) {
                continue; // 跳过我方单位
            }

            for (Model ownModel : models.values()) {
                if (!side.equals(ownModel.getSide())) {
                    continue; // 跳过敌方单位
                }

                // 计算敌方单位对己方单位的威胁程度
                double distance = calculateDistance(enemyModel.getPosition(), ownModel.getPosition());
                double threatLevel = calculateThreatLevel(enemyModel, ownModel, distance);

                if (threatLevel > 0.3) { // 如果威胁超过阈值
                    DecisionSuggestion.ThreatAssessment threat = new DecisionSuggestion.ThreatAssessment();
                    threat.setThreatModelId(enemyModel.getId());
                    threat.setAffectedModelId(ownModel.getId());
                    threat.setThreatLevel(threatLevel);
                    threat.setThreatType(determineThreatType(enemyModel, ownModel));
                    threat.setDescription(enemyModel.getType() + "单位" + enemyModel.getName() + 
                                         "对我方" + ownModel.getType() + "单位" + ownModel.getName() + "构成威胁");

                    threats.add(threat);
                }
            }
        }

        return threats;
    }

    /**
     * 生成推理说明
     */
    private String generateReasoning(Map<String, Model> models, List<Map<String, Object>> tacticalCases) {
        StringBuilder reasoning = new StringBuilder();

        reasoning.append("基于当前战场态势和历史战术案例进行决策推理。");

        if (!tacticalCases.isEmpty()) {
            reasoning.append("参考了").append(tacticalCases.size()).append("个相似战术案例，包括:");
            for (int i = 0; i < Math.min(3, tacticalCases.size()); i++) {
                Map<String, Object> tc = tacticalCases.get(i);
                reasoning.append(tc.getOrDefault("title", "未知案例")).append(", ");
            }
        } else {
            reasoning.append("未找到完全匹配的历史案例，基于规则和实时态势进行推理。");
        }

        return reasoning.toString();
    }

    /**
     * 计算决策置信度
     */
    private Double calculateConfidence(Map<String, Model> models) {
        // 简单的置信度计算，实际情况会更复杂
        int totalCount = models.size();
        int detectedCount = 0;

        for (Model model : models.values()) {
            if (model.getDetectionStatus() == Model.DetectionStatus.DETECTED) {
                detectedCount++;
            }
        }

        double detectionRate = totalCount > 0 ? (double) detectedCount / totalCount : 0;
        return Math.min(1.0, 0.5 + detectionRate * 0.4); // 基础置信度0.5，加上探测率影响
    }

    /**
     * 创建降级建议
     */
    private DecisionSuggestion createFallbackSuggestion(String sceneId, String side) {
        DecisionSuggestion suggestion = new DecisionSuggestion();
        suggestion.setSide(side);
        suggestion.setTimestamp(new Date());
        suggestion.setSituationSummary("无法获取实时态势数据");
        suggestion.setActions(new ArrayList<>());
        suggestion.setThreats(new ArrayList<>());
        suggestion.setReasoning("由于系统故障，返回默认决策建议");
        suggestion.setConfidence(0.3); // 较低的置信度

        return suggestion;
    }

    /**
     * 查找最近的敌方单位
     */
    private Model findNearestEnemy(Model model, Map<String, Model> allModels, String ownSide) {
        Model nearestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Model otherModel : allModels.values()) {
            if (otherModel.getSide().equals(ownSide)) {
                continue; // 跳过己方单位
            }

            double distance = calculateDistance(model.getPosition(), otherModel.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                nearestEnemy = otherModel;
            }
        }

        return nearestEnemy;
    }

    /**
     * 查找集群目标
     */
    private Model findClusterTarget(Model model, Map<String, Model> allModels, String ownSide) {
        // 简化的集群目标查找逻辑
        // 实际应用中需要更复杂的聚类算法
        Model clusterTarget = null;
        double minAvgDistance = Double.MAX_VALUE;

        for (Model otherModel : allModels.values()) {
            if (otherModel.getSide().equals(ownSide)) {
                continue; // 跳过己方单位
            }

            // 计算该目标与其他敌方单位的平均距离
            double avgDistance = 0;
            int count = 0;
            for (Model compareModel : allModels.values()) {
                if (compareModel.getSide().equals(ownSide) || 
                    compareModel.getId().equals(otherModel.getId())) {
                    continue; // 跳过己方单位和自身
                }
                avgDistance += calculateDistance(otherModel.getPosition(), compareModel.getPosition());
                count++;
            }

            if (count > 0) {
                avgDistance /= count;
                if (avgDistance < minAvgDistance) {
                    minAvgDistance = avgDistance;
                    clusterTarget = otherModel;
                }
            }
        }

        return clusterTarget;
    }

    /**
     * 计算两点间距离
     */
    private double calculateDistance(com.spring.airag.common.entity.Position pos1, 
                                   com.spring.airag.common.entity.Position pos2) {
        double dx = pos1.getX() - pos2.getX();
        double dy = pos1.getY() - pos2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算威胁等级
     */
    private double calculateThreatLevel(Model attacker, Model target, double distance) {
        // 基础威胁值
        double baseThreat = attacker.getAttackPower() / target.getDefensePower();
        
        // 距离衰减因子
        double distanceFactor = Math.max(0.1, 1.0 - distance / attacker.getAttackRange());
        
        // 类型克制因子
        double typeFactor = getTypeAdvantageFactor(attacker.getType(), target.getType());
        
        return Math.min(1.0, baseThreat * distanceFactor * typeFactor);
    }

    /**
     * 获取类型优势因子
     */
    private double getTypeAdvantageFactor(Model.Type attackerType, Model.Type targetType) {
        // 简化的类型克制关系
        if (attackerType == Model.Type.AIRCRAFT && targetType == Model.Type.ARTILLERY) {
            return 1.5; // 飞机对火炮有优势
        } else if (attackerType == Model.Type.TANK && targetType == Model.Type.INFANTRY) {
            return 1.3; // 坦克对步兵有优势
        } else if (attackerType == Model.Type.ARTILLERY && targetType == Model.Type.TANK) {
            return 1.2; // 火炮对坦克有优势
        } else if (attackerType == Model.Type.INFANTRY && targetType == Model.Type.AIRCRAFT) {
            return 0.8; // 步兵对飞机劣势
        }
        
        return 1.0; // 无特殊优势
    }

    /**
     * 判断威胁类型
     */
    private String determineThreatType(Model attacker, Model target) {
        double distance = calculateDistance(attacker.getPosition(), target.getPosition());
        
        if (distance <= attacker.getAttackRange()) {
            return "IMMINENT_THREAT"; // 即时威胁
        } else if (distance <= attacker.getAttackRange() * 1.5) {
            return "POTENTIAL_THREAT"; // 潜在威胁
        } else {
            return "MONITORING"; // 监控中
        }
    }

    /**
     * 保存决策日志
     */
    private void saveDecisionLog(String sceneId, String side, DecisionSuggestion suggestion) {
        // 实际项目中应将决策日志保存到数据库或消息队列
        log.info("保存决策日志: 场景={}, 方={}, 行动数={}, 威脽数={}, 置信度={}", 
                sceneId, side, 
                suggestion.getActions() != null ? suggestion.getActions().size() : 0,
                suggestion.getThreats() != null ? suggestion.getThreats().size() : 0,
                suggestion.getConfidence());
    }
}