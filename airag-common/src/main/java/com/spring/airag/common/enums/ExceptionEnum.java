package com.spring.airag.common.enums;

import lombok.Getter;

/**
 * 异常枚举
 */
@Getter
public enum ExceptionEnum {

    // 通用异常
    BUSINESS_EXCEPTION(10001, "业务异常"),
    PARAMETER_EXCEPTION(10002, "参数异常"),
    DATA_NOT_FOUND_EXCEPTION(10003, "数据不存在"),
    DATA_DUPLICATE_EXCEPTION(10004, "数据重复"),
    PERMISSION_DENIED_EXCEPTION(10005, "权限不足"),
    AUTHENTICATION_FAILED_EXCEPTION(10006, "认证失败"),
    VALIDATION_FAILED_EXCEPTION(10007, "验证失败"),
    OPERATION_FAILED_EXCEPTION(10008, "操作失败"),
    RESOURCE_LIMIT_EXCEEDED_EXCEPTION(10009, "资源超出限制"),
    ILLEGAL_STATE_EXCEPTION(10010, "非法状态"),
    ILLEGAL_ARGUMENT_EXCEPTION(10011, "非法参数"),
    NETWORK_EXCEPTION(10012, "网络异常"),
    TIMEOUT_EXCEPTION(10013, "超时异常"),
    FILE_OPERATION_EXCEPTION(10014, "文件操作异常"),
    DATABASE_EXCEPTION(10015, "数据库异常"),
    REMOTE_SERVICE_EXCEPTION(10016, "远程服务异常"),
    CONFIGURATION_EXCEPTION(10017, "配置异常"),
    SECURITY_EXCEPTION(10018, "安全异常"),
    ENCRYPTION_EXCEPTION(10019, "加密异常"),
    CACHE_EXCEPTION(10020, "缓存异常");

    private final Integer code;
    private final String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ExceptionEnum getByCode(Integer code) {
        for (ExceptionEnum exception : values()) {
            if (exception.getCode().equals(code)) {
                return exception;
            }
        }
        return null;
    }
}