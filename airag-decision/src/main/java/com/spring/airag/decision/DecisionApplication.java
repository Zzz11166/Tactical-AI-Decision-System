package com.spring.airag.decision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AI决策服务启动类
 */
@SpringBootApplication
@EnableFeignClients
public class DecisionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DecisionApplication.class, args);
    }
}