package com.spring.airag.situation.observer;

import com.spring.airag.common.entity.Model;

import java.util.Map;

/**
 * 态势观察者接口
 */
public interface SituationObserver {
    /**
     * 更新态势信息
     * @param sceneId 场景ID
     * @param models 模型集合
     * @param eventType 事件类型
     */
    void update(String sceneId, Map<String, Model> models, String eventType);
}