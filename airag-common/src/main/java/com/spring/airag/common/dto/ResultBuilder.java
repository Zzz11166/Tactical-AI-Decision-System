package com.spring.airag.common.dto;

/**
 * 结果构建器
 */
public class ResultBuilder<T> {

    private Integer code;
    private String message;
    private T data;

    private ResultBuilder() {
    }

    public static <T> ResultBuilder<T> create() {
        return new ResultBuilder<>();
    }

    public ResultBuilder<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public ResultBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result<T> build() {
        return new Result<>(code, message, data);
    }

    /**
     * 构建成功结果
     */
    public static <T> Result<T> success() {
        return Result.success();
    }

    /**
     * 构建带数据的成功结果
     */
    public static <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * 构建带消息和数据的成功结果
     */
    public static <T> Result<T> success(String message, T data) {
        return Result.success(message, data);
    }

    /**
     * 构建错误结果
     */
    public static <T> Result<T> error() {
        return Result.error();
    }

    /**
     * 构建带消息的错误结果
     */
    public static <T> Result<T> error(String message) {
        return Result.error(message);
    }

    /**
     * 构建带状态码和消息的错误结果
     */
    public static <T> Result<T> error(Integer code, String message) {
        return Result.error(code, message);
    }
}