package com.spring.airag.common.constants;

/**
 * 日志相关常量类
 */
public class LogConstants {

    // 日志级别
    public static final String LOG_LEVEL_TRACE = "TRACE";
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARN = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";
    public static final String LOG_LEVEL_FATAL = "FATAL";

    // 日志分类
    public static final String LOG_CATEGORY_ACCESS = "access";
    public static final String LOG_CATEGORY_BUSINESS = "business";
    public static final String LOG_CATEGORY_SECURITY = "security";
    public static final String LOG_CATEGORY_ERROR = "error";
    public static final String LOG_CATEGORY_PERFORMANCE = "performance";
    public static final String LOG_CATEGORY_AUDIT = "audit";

    // 日志格式
    public static final String LOG_PATTERN_DEFAULT = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
    public static final String LOG_PATTERN_ACCESS = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level ACCESS - %msg%n";
    public static final String LOG_PATTERN_AUDIT = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level AUDIT - %msg%n";

    // 日志文件相关
    public static final String LOG_FILE_EXTENSION = ".log";
    public static final String LOG_FILE_BACKUP_EXTENSION = ".log.%d{yyyy-MM-dd}";
    public static final String LOG_FILE_PATTERN = "logs/%s.log";
    public static final String LOG_FILE_ACCESS = "logs/access.log";
    public static final String LOG_FILE_ERROR = "logs/error.log";
    public static final String LOG_FILE_AUDIT = "logs/audit.log";

    // 日志字段
    public static final String LOG_FIELD_TIMESTAMP = "timestamp";
    public static final String LOG_FIELD_LEVEL = "level";
    public static final String LOG_FIELD_LOGGER = "logger";
    public static final String LOG_FIELD_MESSAGE = "message";
    public static final String LOG_FIELD_THREAD = "thread";
    public static final String LOG_FIELD_CLASS = "class";
    public static final String LOG_FIELD_METHOD = "method";
    public static final String LOG_FIELD_LINE = "line";
    public static final String LOG_FIELD_EXCEPTION = "exception";
    public static final String LOG_FIELD_STACKTRACE = "stacktrace";

    // 访问日志字段
    public static final String LOG_FIELD_REQUEST_ID = "requestId";
    public static final String LOG_FIELD_USER_ID = "userId";
    public static final String LOG_FIELD_IP_ADDRESS = "ipAddress";
    public static final String LOG_FIELD_USER_AGENT = "userAgent";
    public static final String LOG_FIELD_REQUEST_URI = "requestUri";
    public static final String LOG_FIELD_HTTP_METHOD = "httpMethod";
    public static final String LOG_FIELD_REQUEST_PARAMS = "requestParams";
    public static final String LOG_FIELD_RESPONSE_STATUS = "responseStatus";
    public static final String LOG_FIELD_RESPONSE_TIME = "responseTime";
    public static final String LOG_FIELD_REFERER = "referer";

    // 安全日志字段
    public static final String LOG_FIELD_ACTION = "action";
    public static final String LOG_FIELD_RESOURCE = "resource";
    public static final String LOG_FIELD_RESULT = "result";
    public static final String LOG_FIELD_PERMISSION = "permission";
    public static final String LOG_FIELD_ROLE = "role";

    // 性能日志字段
    public static final String LOG_FIELD_DURATION = "duration";
    public static final String LOG_FIELD_MEMORY_USED = "memoryUsed";
    public static final String LOG_FIELD_CPU_USAGE = "cpuUsage";
    public static final String LOG_FIELD_DB_CONNECTIONS = "dbConnections";
    public static final String LOG_FIELD_THREAD_COUNT = "threadCount";

    // 日志配置
    public static final String LOG_CONFIG_LEVEL = "logging.level";
    public static final String LOG_CONFIG_PATTERN = "logging.pattern.console";
    public static final String LOG_CONFIG_FILE = "logging.file.name";
    public static final String LOG_CONFIG_PATH = "logging.path";
    public static final String LOG_CONFIG_MAX_SIZE = "logging.file.max-size";
    public static final String LOG_CONFIG_MAX_HISTORY = "logging.file.max-history";

    // 日志大小限制
    public static final String LOG_SIZE_10MB = "10MB";
    public static final String LOG_SIZE_50MB = "50MB";
    public static final String LOG_SIZE_100MB = "100MB";
    public static final String LOG_SIZE_200MB = "200MB";
    public static final String LOG_SIZE_500MB = "500MB";

    // 日志保留天数
    public static final int LOG_RETENTION_7_DAYS = 7;
    public static final int LOG_RETENTION_15_DAYS = 15;
    public static final int LOG_RETENTION_30_DAYS = 30;
    public static final int LOG_RETENTION_60_DAYS = 60;
    public static final int LOG_RETENTION_90_DAYS = 90;
    public static final int LOG_RETENTION_180_DAYS = 180;
    public static final int LOG_RETENTION_365_DAYS = 365;

    // 日志切分策略
    public static final String LOG_ROLLING_TIME_DAILY = "daily";
    public static final String LOG_ROLLING_TIME_HOURLY = "hourly";
    public static final String LOG_ROLLING_SIZE_BASED = "size_based";
    public static final String LOG_ROLLING_COMPOSITE = "composite";

    // 日志过滤器
    public static final String LOG_FILTER_SENSITIVE_DATA = "SENSITIVE_DATA_FILTER";
    public static final String LOG_FILTER_PROFANITY = "PROFANITY_FILTER";
    public static final String LOG_FILTER_PERSONAL_INFO = "PERSONAL_INFO_FILTER";

    // 日志采样
    public static final int LOG_SAMPLING_RATE_100_PERCENT = 100;
    public static final int LOG_SAMPLING_RATE_50_PERCENT = 50;
    public static final int LOG_SAMPLING_RATE_10_PERCENT = 10;
    public static final int LOG_SAMPLING_RATE_5_PERCENT = 5;
    public static final int LOG_SAMPLING_RATE_1_PERCENT = 1;

    // 日志输出格式
    public static final String LOG_FORMAT_PLAIN = "plain";
    public static final String LOG_FORMAT_JSON = "json";
    public static final String LOG_FORMAT_XML = "xml";
    public static final String LOG_FORMAT_CUSTOM = "custom";

    // 日志异步相关
    public static final String LOG_ASYNC_QUEUE_SIZE = "async.queue.size";
    public static final String LOG_ASYNC_DISCARD_OLDEST = "async.discard.oldest";
    public static final String LOG_ASYNC_DISCARD_NEWEST = "async.discard.newest";

    // 日志追踪相关
    public static final String LOG_TRACE_ID = "traceId";
    public static final String LOG_SPAN_ID = "spanId";
    public static final String LOG_PARENT_SPAN_ID = "parentSpanId";
    public static final String LOG_TRACE_SAMPLED = "sampled";

    // 日志标记相关
    public static final String LOG_MARKER_SECURITY = "SECURITY";
    public static final String LOG_MARKER_PERFORMANCE = "PERFORMANCE";
    public static final String LOG_MARKER_AUDIT = "AUDIT";
    public static final String LOG_MARKER_BUSINESS = "BUSINESS";

    // 日志聚合相关
    public static final String LOG_AGGREGATION_ENABLED = "log.aggregation.enabled";
    public static final String LOG_AGGREGATION_INTERVAL = "log.aggregation.interval";
    public static final String LOG_AGGREGATION_BATCH_SIZE = "log.aggregation.batch.size";
}