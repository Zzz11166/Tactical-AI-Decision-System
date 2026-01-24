package com.spring.airag.common.utils;

import java.util.UUID;

/**
 * ID生成工具类
 */
public class IdGenerator {

    /**
     * 生成UUID
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成带前缀的ID
     */
    public static String generateIdWithPrefix(String prefix) {
        return prefix + "_" + generateId();
    }
}