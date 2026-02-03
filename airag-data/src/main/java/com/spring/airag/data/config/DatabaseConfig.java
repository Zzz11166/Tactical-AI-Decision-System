package com.spring.airag.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据库配置类
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.spring.airag.data.repository")
@EntityScan(basePackages = "com.spring.airag.data.entity")
@EnableTransactionManagement
public class DatabaseConfig {
    // 数据库配置
}