package com.spring.airag.decision.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 数据服务Feign客户端
 */
@FeignClient(name = "airag-data", fallback = DataFeignClientFallback.class)
public interface DataFeignClient {

    /**
     * 检索知识库
     */
    @GetMapping("/data/knowledge/search")
    List<Map<String, Object>> searchKnowledge(@RequestParam("query") String query,
                                           @RequestParam(value = "topK", defaultValue = "5") int topK);

    /**
     * 获取战术案例
     */
    @GetMapping("/data/case/list")
    List<Map<String, Object>> getTacticalCases(@RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit);

    /**
     * 保存决策日志
     */
    @GetMapping("/data/log/save")
    Boolean saveDecisionLog(@RequestParam("sceneId") String sceneId,
                           @RequestParam("side") String side,
                           @RequestParam("content") String content);
}