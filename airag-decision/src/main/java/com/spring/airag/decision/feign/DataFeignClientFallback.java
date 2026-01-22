package com.spring.airag.decision.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据服务Feign客户端降级实现
 */
@Slf4j
@Component
public class DataFeignClientFallback implements DataFeignClient {

    @Override
    public List<Map<String, Object>> searchKnowledge(String query, int topK) {
        log.warn("检索知识库失败，触发降级: query={}", query);
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getTacticalCases(String type, int limit) {
        log.warn("获取战术案例失败，触发降级: type={}", type);
        return new ArrayList<>();
    }

    @Override
    public Boolean saveDecisionLog(String sceneId, String side, String content) {
        log.warn("保存决策日志失败，触发降级: sceneId={}, side={}", sceneId, side);
        return false;
    }
}