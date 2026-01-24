package com.spring.airag.common.constants;

/**
 * 系统常量类
 */
public class SystemConstants {

    // 系统相关
    public static final String SYSTEM_NAME = "AI辅助决策系统";
    public static final String SYSTEM_VERSION = "1.0.0";
    public static final String SYSTEM_AUTHOR = "Spring Team";

    // 服务相关
    public static final String SERVICE_GATEWAY = "airag-gateway";
    public static final String SERVICE_AUTH = "airag-auth";
    public static final String SERVICE_DATA = "airag-data";
    public static final String SERVICE_SCENE = "airag-scene";
    public static final String SERVICE_SITUATION = "airag-situation";
    public static final String SERVICE_DECISION = "airag-decision";

    // 环境相关
    public static final String ENV_DEV = "dev";
    public static final String ENV_TEST = "test";
    public static final String ENV_PROD = "prod";

    // 数据库相关
    public static final String DB_POSTGRESQL = "postgresql";
    public static final String DB_MYSQL = "mysql";
    public static final String DB_ORACLE = "oracle";

    // 消息队列相关
    public static final String MQ_ROCKETMQ = "rocketmq";
    public static final String MQ_RABBITMQ = "rabbitmq";
    public static final String MQ_KAFKA = "kafka";

    // 缓存相关
    public static final String CACHE_REDIS = "redis";
    public static final String CACHE_EHCACHE = "ehcache";

    // 日志级别
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARN = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";

    // 日期时间格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    // HTTP方法
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_DELETE = "DELETE";
    public static final String HTTP_PATCH = "PATCH";
    public static final String HTTP_HEAD = "HEAD";
    public static final String HTTP_OPTIONS = "OPTIONS";

    // HTTP状态码
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_NO_CONTENT = 204;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_FORBIDDEN = 403;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_STATUS_REQUEST_TIMEOUT = 408;
    public static final int HTTP_STATUS_TOO_MANY_REQUESTS = 429;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_STATUS_BAD_GATEWAY = 502;
    public static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    public static final int HTTP_STATUS_GATEWAY_TIMEOUT = 504;

    // 文件上传
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024L; // 10MB
    public static final String[] ALLOWED_IMAGE_TYPES = {"jpg", "jpeg", "png", "gif", "bmp"};
    public static final String[] ALLOWED_DOC_TYPES = {"doc", "docx", "pdf", "txt", "xls", "xlsx"};

    // 分页相关
    public static final int PAGE_SIZE_DEFAULT = 10;
    public static final int PAGE_SIZE_MAX = 100;
    public static final int PAGE_NUM_DEFAULT = 1;

    // 缓存相关
    public static final int CACHE_TTL_DEFAULT = 3600; // 1小时
    public static final int CACHE_TTL_SHORT = 300;    // 5分钟
    public static final int CACHE_TTL_LONG = 86400;   // 24小时
}