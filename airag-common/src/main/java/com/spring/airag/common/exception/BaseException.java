package com.spring.airag.common.exception;

import lombok.Data;

/**
 * 基础异常类
 */
@Data
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
        this.message = message;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}