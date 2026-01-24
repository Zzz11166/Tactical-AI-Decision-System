package com.spring.airag.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 */
public class JsonUtil {

    /**
     * 对象转JSON字符串
     */
    public static String toJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            throw new RuntimeException("对象转JSON字符串失败", e);
        }
    }

    /**
     * JSON字符串转对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串转对象失败", e);
        }
    }

    /**
     * JSON字符串转指定类型
     */
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return JSON.parseObject(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串转指定类型失败", e);
        }
    }

    /**
     * JSON字符串转List
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串转List失败", e);
        }
    }

    /**
     * JSON字符串转Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        try {
            return JSON.parseObject(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串转Map失败", e);
        }
    }

    /**
     * JSON字符串转JSONObject
     */
    public static JSONObject toJsonObject(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            throw new RuntimeException("JSON字符串转JSONObject失败", e);
        }
    }

    /**
     * 检查JSON字符串是否有效
     */
    public static boolean isValidJson(String json) {
        try {
            JSON.parse(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 格式化JSON字符串
     */
    public static String formatJson(String json) {
        try {
            Object obj = JSON.parse(json);
            return JSON.toJSONString(obj, true); // true表示格式化输出
        } catch (Exception e) {
            throw new RuntimeException("格式化JSON字符串失败", e);
        }
    }
}