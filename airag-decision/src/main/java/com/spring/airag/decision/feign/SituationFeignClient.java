package com.spring.airag.decision.feign;

import com.spring.airag.common.entity.Model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 态势服务Feign客户端
 */
@FeignClient(name = "airag-situation", fallback = SituationFeignClientFallback.class)
public interface SituationFeignClient {

    /**
     * 获取当前态势
     */
    @GetMapping("/situation/current/{sceneId}/{side}")
    Map<String, Model> getCurrentSituation(@PathVariable("sceneId") String sceneId, 
                                          @PathVariable("side") String side);

    /**
     * 获取指定场景的所有模型
     */
    @GetMapping("/situation/models/{sceneId}")
    Map<String, Model> getAllModelsInScene(@PathVariable("sceneId") String sceneId);

    /**
     * 发送决策指令
     */
    @GetMapping("/situation/command/send")
    Boolean sendDecisionCommand(@RequestParam("sceneId") String sceneId,
                               @RequestParam("side") String side,
                               @RequestParam("command") String command);
}