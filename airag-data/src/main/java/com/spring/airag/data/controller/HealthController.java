package com.spring.airag.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/health/check")
    public Map<String, Object> checkHealth() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 检查服务注册状态
            List<String> services = discoveryClient.getServices();
            boolean isRegistered = services.contains("airag-data");
            
            result.put("status", "UP");
            result.put("service", "airag-data");
            result.put("registered", isRegistered);
            result.put("availableServices", services);
            result.put("totalServices", services.size());
            result.put("timestamp", System.currentTimeMillis());
            
            if (isRegistered) {
                result.put("message", "服务已成功注册到Nacos");
            } else {
                result.put("message", "服务未在Nacos中找到，请检查配置");
            }
            
        } catch (Exception e) {
            result.put("status", "DOWN");
            result.put("error", e.getMessage());
            result.put("message", "健康检查失败");
        }
        
        return result;
    }
}