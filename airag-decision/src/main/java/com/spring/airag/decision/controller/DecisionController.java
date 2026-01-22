package com.spring.airag.decision.controller;

import com.spring.airag.common.entity.DecisionSuggestion;
import com.spring.airag.decision.service.AIDecisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * AI决策控制器
 */
@Slf4j
@RestController
@RequestMapping("/decision")
public class DecisionController {

    @Autowired
    private AIDecisionService aiDecisionService;

    /**
     * 生成AI决策建议
     */
    @GetMapping("/suggest/{sceneId}/{side}")
    public DecisionSuggestion generateDecisionSuggestion(@PathVariable("sceneId") String sceneId,
                                                        @PathVariable("side") String side) {
        log.info("接收AI决策请求: 场景ID={}, 方={}", sceneId, side);
        
        DecisionSuggestion suggestion = aiDecisionService.generateDecisionSuggestion(sceneId, side);
        
        log.info("返回AI决策建议: 场景ID={}, 方={}, 置信度={}", 
                sceneId, side, suggestion.getConfidence());
        
        return suggestion;
    }

    /**
     * 手动生成决策建议（POST方式，可接受更多参数）
     */
    @PostMapping("/suggest")
    public DecisionSuggestion generateDecisionSuggestionPost(@RequestBody DecisionRequest request) {
        log.info("接收AI决策请求(POST): 场景ID={}, 方={}", request.getSceneId(), request.getSide());
        
        DecisionSuggestion suggestion = aiDecisionService.generateDecisionSuggestion(
                request.getSceneId(), request.getSide());
        
        log.info("返回AI决策建议(POST): 场景ID={}, 方={}, 置信度={}", 
                request.getSceneId(), request.getSide(), suggestion.getConfidence());
        
        return suggestion;
    }

    /**
     * 决策请求体
     */
    public static class DecisionRequest {
        private String sceneId;
        private String side;
        private String scenarioType;  // 场景类型，如"进攻作战"、"防御作战"等
        private String priority;      // 优先级

        // getter和setter
        public String getSceneId() {
            return sceneId;
        }

        public void setSceneId(String sceneId) {
            this.sceneId = sceneId;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getScenarioType() {
            return scenarioType;
        }

        public void setScenarioType(String scenarioType) {
            this.scenarioType = scenarioType;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }
    }
}