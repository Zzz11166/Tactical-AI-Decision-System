package com.spring.airag.common.constants;

/**
 * 验证相关常量类
 */
public class ValidationConstants {

    // 通用验证
    public static final String VALIDATION_REQUIRED = "required";
    public static final String VALIDATION_NOT_EMPTY = "not_empty";
    public static final String VALIDATION_NOT_BLANK = "not_blank";
    public static final String VALIDATION_MIN = "min";
    public static final String VALIDATION_MAX = "max";
    public static final String VALIDATION_SIZE = "size";
    public static final String VALIDATION_EMAIL = "email";
    public static final String VALIDATION_PHONE = "phone";
    public static final String VALIDATION_URL = "url";
    public static final String VALIDATION_REGEX = "regex";

    // 长度验证
    public static final int LENGTH_MIN_SHORT = 1;
    public static final int LENGTH_MIN_NORMAL = 2;
    public static final int LENGTH_MIN_USERNAME = 3;
    public static final int LENGTH_MIN_PASSWORD = 6;
    public static final int LENGTH_MIN_MEDIUM = 10;
    public static final int LENGTH_MIN_LONG = 20;
    public static final int LENGTH_MAX_SHORT = 10;
    public static final int LENGTH_MAX_NORMAL = 50;
    public static final int LENGTH_MAX_USERNAME = 20;
    public static final int LENGTH_MAX_PASSWORD = 128;
    public static final int LENGTH_MAX_MEDIUM = 100;
    public static final int LENGTH_MAX_LONG = 500;
    public static final int LENGTH_MAX_VERY_LONG = 2000;
    public static final int LENGTH_MAX_HUGE = 10000;

    // 数值验证
    public static final double NUMBER_MIN_SMALL = 0.01;
    public static final double NUMBER_MIN_NORMAL = 1.0;
    public static final double NUMBER_MAX_SMALL = 100.0;
    public static final double NUMBER_MAX_NORMAL = 1000.0;
    public static final double NUMBER_MAX_LARGE = 10000.0;
    public static final double NUMBER_MAX_VERY_LARGE = 1000000.0;

    // 邮箱验证
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final int EMAIL_MAX_LENGTH = 254;

    // 手机号验证
    public static final String PHONE_CHINA_REGEX = "^1[3-9]\\d{9}$";
    public static final int PHONE_MIN_LENGTH = 11;
    public static final int PHONE_MAX_LENGTH = 11;

    // 身份证验证
    public static final String ID_CARD_REGEX = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    public static final int ID_CARD_LENGTH = 18;

    // 用户名校验
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$";
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 20;

    // 密码校验
    public static final String PASSWORD_REGEX = "^.{6,20}$";
    public static final String PASSWORD_COMPLEX_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{6,}$";
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 20;

    // URL校验
    public static final String URL_REGEX = "^(https?://)?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)*[a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])(:\\d+)?(/.*)?$";
    public static final int URL_MAX_LENGTH = 2048;

    // IP地址校验
    public static final String IP_V4_REGEX = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    public static final String IP_V6_REGEX = "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$|^::1$|^::$";
    public static final String IP_REGEX = "(" + IP_V4_REGEX + ")|(" + IP_V6_REGEX + ")";

    // MAC地址校验
    public static final String MAC_REGEX = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";

    // 日期时间校验
    public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String TIME_REGEX = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
    public static final String DATETIME_REGEX = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";

    // 文件名校验
    public static final String FILE_NAME_REGEX = "^[a-zA-Z0-9_-]+\\.[a-zA-Z0-9]+$";
    public static final String FILE_NAME_SAFE_REGEX = "^[a-zA-Z0-9_\\u4e00-\\u9fa5\\-\\.]*$";
    public static final int FILE_NAME_MAX_LENGTH = 255;

    // 文件大小校验
    public static final long FILE_SIZE_1KB = 1024L;
    public static final long FILE_SIZE_10KB = 10240L;
    public static final long FILE_SIZE_100KB = 102400L;
    public static final long FILE_SIZE_1MB = 1048576L;
    public static final long FILE_SIZE_10MB = 10485760L;
    public static final long FILE_SIZE_50MB = 52428800L;
    public static final long FILE_SIZE_100MB = 104857600L;

    // 颜色值校验
    public static final String COLOR_HEX_REGEX = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    public static final String COLOR_RGB_REGEX = "^rgb\\(\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*\\)$";
    public static final String COLOR_RGBA_REGEX = "^rgba\\(\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*(0|1|0\\.\\d+)\\s*\\)$";

    // 验证错误消息
    public static final String MSG_REQUIRED = "{validation.required}";
    public static final String MSG_NOT_EMPTY = "{validation.not_empty}";
    public static final String MSG_NOT_BLANK = "{validation.not_blank}";
    public static final String MSG_MIN = "{validation.min}";
    public static final String MSG_MAX = "{validation.max}";
    public static final String MSG_SIZE = "{validation.size}";
    public static final String MSG_EMAIL = "{validation.email}";
    public static final String MSG_PHONE = "{validation.phone}";
    public static final String MSG_URL = "{validation.url}";
    public static final String MSG_REGEX = "{validation.regex}";
    public static final String MSG_RANGE = "{validation.range}";

    // 验证组
    public static final String GROUP_CREATE = "create";
    public static final String GROUP_UPDATE = "update";
    public static final String GROUP_DELETE = "delete";
    public static final String GROUP_VIEW = "view";

    // 验证策略
    public static final String STRATEGY_FAST_FAIL = "fast_fail";
    public static final String STRATEGY_ALL = "all";
    public static final String STRATEGY_CUSTOM = "custom";

    // 验证级别
    public static final String LEVEL_STRICT = "strict";
    public static final String LEVEL_NORMAL = "normal";
    public static final String LEVEL_RELAXED = "relaxed";

    // 正则表达式验证模式
    public static final String PATTERN_CHINESE = "[\\u4e00-\\u9fa5]";
    public static final String PATTERN_ENGLISH = "[a-zA-Z]";
    public static final String PATTERN_DIGIT = "\\d";
    public static final String PATTERN_SPECIAL_CHAR = "[~!@#$%^&*()_+-=\\[\\]{}|;':\",./<>?]";
    public static final String PATTERN_CHINESE_NAME = "^[\u4e00-\u9fa5]{2,10}$";
    public static final String PATTERN_POSTAL_CODE = "^\\d{6}$";
    public static final String PATTERN_QQ = "^[1-9]\\d{4,10}$";
    public static final String PATTERN_WECHAT = "^[a-zA-Z][-_a-zA-Z0-9]{5,19}$";
    public static final String PATTERN_COMPANY = "^[\\u4e00-\\u9fa5a-zA-Z0-9\\-]{2,50}$";
}