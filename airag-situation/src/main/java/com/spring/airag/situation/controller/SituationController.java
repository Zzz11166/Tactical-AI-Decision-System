package com.spring.airag.situation.controller;

import com.spring.airag.common.entity.Model;
import com.spring.airag.situation.service.SituationUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 态势控制器
 */
@Slf4j
@RestController
@RequestMapping("/situation")
public class SituationController {

    @Autowired
    private SituationUpdateService situationUpdateService;

    /**
     * 获取当前态势
     */
    @GetMapping("/current/{sceneId}/{side}")
    public Map<String, Model> getCurrentSituation(@PathVariable("sceneId") String sceneId, 
                                                 @PathVariable("side") String side) {
        log.info("获取当前态势: sceneId={}, side={}", sceneId, side);
        
        // 模拟返回态势数据，实际应用中应从数据库或缓存获取
        Map<String, Model> models = new HashMap<>();
        
        // 示例数据
        Model model1 = new Model();
        model1.setId("model1");
        model1.setSceneId(sceneId);
        model1.setName("红方坦克1");
        model1.setSide(side);
        model1.setType(Model.Type.TANK);
        model1.setStatus(Model.Status.ACTIVE);
        models.put("model1", model1);
        
        Model model2 = new Model();
        model2.setId("model2");
        model2.setSceneId(sceneId);
        model2.setName("蓝方步兵1");
        model2.setSide("BLUE");
        model2.setType(Model.Type.INFANTRY);
        model2.setStatus(Model.Status.ACTIVE);
        models.put("model2", model2);
        
        return models;
    }

    /**
     * 获取指定场景的所有模型
     */
    @GetMapping("/models/{sceneId}")
    public Map<String, Model> getAllModelsInScene(@PathVariable("sceneId") String sceneId) {
        log.info("获取场景所有模型: sceneId={}", sceneId);
        
        Map<String, Model> models = new HashMap<>();
        
        // 示例数据
        Model model1 = new Model();
        model1.setId("model1");
        model1.setSceneId(sceneId);
        model1.setName("红方坦克1");
        model1.setSide("RED");
        model1.setType(Model.Type.TANK);
        model1.setStatus(Model.Status.ACTIVE);
        models.put("model1", model1);
        
        Model model2 = new Model();
        model2.setId("model2");
        model2.setSceneId(sceneId);
        model2.setName("蓝方步兵1");
        model2.setSide("BLUE");
        model2.setType(Model.Type.INFANTRY);
        model2.setStatus(Model.Status.ACTIVE);
        models.put("model2", model2);
        
        return models;
    }

    /**
     * 发送决策指令
     */
    @GetMapping("/command/send")
    public Boolean sendDecisionCommand(@RequestParam("sceneId") String sceneId,
                                     @RequestParam("side") String side,
                                     @RequestParam("command") String command) {
        log.info("发送决策指令: sceneId={}, side={}, command={}", sceneId, side, command);
        
        // 模拟发送成功
        return true;
    }

    /**
     * 手动触发态势更新推送（用于测试）
     */
    @PostMapping("/push-update/{sceneId}")
    public Boolean pushUpdate(@PathVariable("sceneId") String sceneId,
                             @RequestBody Map<String, Model> models,
                             @RequestParam(value = "eventType", defaultValue = "MODEL_UPDATE") String eventType) {
        log.info("手动推送态势更新: sceneId={}, eventType={}, modelCount={}", 
                sceneId, eventType, models.size());
        
        try {
            situationUpdateService.pushSituationUpdate(sceneId, models, eventType);
            return true;
        } catch (Exception e) {
            log.error("推送态势更新失败", e);
            return false;
        }
    }
}