package com.spring.airag.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 通用配置类
 */
@Configuration
public class CommonConfig {

    /**
     * 配置Jackson ObjectMapper
     * 处理Java 8时间类型序列化
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        // 注册JavaTimeModule以支持Java 8时间类型
        mapper.registerModule(new JavaTimeModule());
        
        return mapper;
    }
}