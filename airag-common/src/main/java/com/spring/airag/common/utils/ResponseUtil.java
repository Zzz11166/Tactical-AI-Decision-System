package com.spring.airag.common.utils;

import com.spring.airag.common.dto.Result;

/**
 * 响应工具类
 */
public class ResponseUtil {

    /**
     * 成功响应
     */
    public static <T> Result<T> success() {
        return Result.success();
    }

    /**
     * 成功响应，带数据
     */
    public static <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * 成功响应，自定义消息和数据
     */
    public static <T> Result<T> success(String message, T data) {
        return Result.success(message, data);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> error() {
        return Result.error();
    }

    /**
     * 失败响应，自定义消息
     */
    public static <T> Result<T> error(String message) {
        return Result.error(message);
    }

    /**
     * 失败响应，自定义状态码和消息
     */
    public static <T> Result<T> error(Integer code, String message) {
        return Result.error(code, message);
    }
}