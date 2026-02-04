package com.spring.airag.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 数据服务启动类
 * 使用Spring Boot框架启动数据服务，并集成Nacos服务发现
 */
@EnableDiscoveryClient  // 启用Nacos服务发现客户端
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})  // Spring Boot主启动类，排除Redis自动配置
@ComponentScan(basePackages = {"com.spring.airag.data", "com.spring.airag.common"})  // 组件扫描，指定扫描包路径
@MapperScan("com.spring.airag.data.mapper")  // MyBatis Mapper接口扫描
public class DataApplication {
    private static final Logger logger = LoggerFactory.getLogger(DataApplication.class);  // 日志记录器
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);  // 启动Spring Boot应用
        logger.info("DataApplication started successfully");  // 启动成功日志
    }
}