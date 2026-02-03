package com.spring.airag.situation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 态势服务启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.spring.airag.situation", "com.spring.airag.common"})
public class SituationApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SituationApplication.class, args);
    }
}