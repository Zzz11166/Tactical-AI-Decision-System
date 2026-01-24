package com.spring.airag.common.enums;

import lombok.Getter;

/**
 * 通用状态枚举
 */
@Getter
public enum CommonStatusEnum {

    /**
     * 启用状态
     */
    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),

    /**
     * 删除状态
     */
    NORMAL(0, "正常"),
    DELETED(1, "已删除"),

    /**
     * 通用状态
     */
    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    private final Integer code;
    private final String desc;

    CommonStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommonStatusEnum getByCode(Integer code) {
        for (CommonStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}