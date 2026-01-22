package com.spring.airag.scene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 场景构设服务主应用类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SceneApplication {
    public static void main(String[] args) {
        SpringApplication.run(SceneApplication.class, args);
    }
}
