package com.spring.airag.situation.observer;

import com.spring.airag.common.entity.Model;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 态势主题类，用于管理观察者
 */
@Slf4j
public class SituationSubject {
    private final List<SituationObserver> observers = new ArrayList<>();

    /**
     * 添加观察者
     */
    public void addObserver(SituationObserver observer) {
        synchronized (observers) {
            observers.add(observer);
            log.info("添加观察者: {}", observer.getClass().getSimpleName());
        }
    }

    /**
     * 移除观察者
     */
    public void removeObserver(SituationObserver observer) {
        synchronized (observers) {
            observers.remove(observer);
            log.info("移除观察者: {}", observer.getClass().getSimpleName());
        }
    }

    /**
     * 通知所有观察者
     */
    public void notifyObservers(String sceneId, Map<String, Model> models, String eventType) {
        synchronized (observers) {
            for (SituationObserver observer : observers) {
                try {
                    observer.update(sceneId, models, eventType);
                } catch (Exception e) {
                    log.error("通知观察者失败", e);
                }
            }
        }
    }
}