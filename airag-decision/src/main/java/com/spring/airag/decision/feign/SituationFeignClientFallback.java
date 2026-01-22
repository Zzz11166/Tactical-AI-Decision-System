package com.spring.airag.decision.feign;

import com.spring.airag.common.entity.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 态势服务Feign客户端降级实现
 */
@Slf4j
@Component
public class SituationFeignClientFallback implements SituationFeignClient {

    @Override
    public Map<String, Model> getCurrentSituation(String sceneId, String side) {
        log.warn("获取当前态势失败，触发降级: sceneId={}, side={}", sceneId, side);
        return new HashMap<>();
    }

    @Override
    public Map<String, Model> getAllModelsInScene(String sceneId) {
        log.warn("获取场景所有模型失败，触发降级: sceneId={}", sceneId);
        return new HashMap<>();
    }

    @Override
    public Boolean sendDecisionCommand(String sceneId, String side, String command) {
        log.warn("发送决策指令失败，触发降级: sceneId={}, side={}, command={}", sceneId, side, command);
        return false;
    }
}