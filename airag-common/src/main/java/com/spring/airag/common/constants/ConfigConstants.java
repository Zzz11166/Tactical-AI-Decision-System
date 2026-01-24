package com.spring.airag.common.constants;

/**
 * 配置相关常量类
 */
public class ConfigConstants {

    // 配置源类型
    public static final String CONFIG_SOURCE_PROPERTIES = "properties";
    public static final String CONFIG_SOURCE_YAML = "yaml";
    public static final String CONFIG_SOURCE_JSON = "json";
    public static final String CONFIG_SOURCE_XML = "xml";
    public static final String CONFIG_SOURCE_ENVIRONMENT = "environment";
    public static final String CONFIG_SOURCE_COMMAND_LINE = "command_line";
    public static final String CONFIG_SOURCE_NACOS = "nacos";
    public static final String CONFIG_SOURCE_APOLLO = "apollo";
    public static final String CONFIG_SOURCE_ZOOKEEPER = "zookeeper";

    // 配置文件扩展名
    public static final String EXTENSION_PROPERTIES = ".properties";
    public static final String EXTENSION_YAML = ".yaml";
    public static final String EXTENSION_YML = ".yml";
    public static final String EXTENSION_JSON = ".json";
    public static final String EXTENSION_XML = ".xml";
    public static final String EXTENSION_TXT = ".txt";

    // 配置文件名称
    public static final String FILE_APPLICATION = "application";
    public static final String FILE_BOOTSTRAP = "bootstrap";
    public static final String FILE_CONFIG = "config";

    // 配置环境
    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_TEST = "test";
    public static final String PROFILE_PROD = "prod";
    public static final String PROFILE_LOCAL = "local";
    public static final String PROFILE_UAT = "uat";
    public static final String PROFILE_STAGING = "staging";

    // 配置属性前缀
    public static final String PREFIX_SPRING = "spring.";
    public static final String PREFIX_SERVER = "server.";
    public static final String PREFIX_MYBATIS = "mybatis.";
    public static final String PREFIX_JPA = "spring.jpa.";
    public static final String PREFIX_DATA = "spring.data.";
    public static final String PREFIX_REDIS = "spring.redis.";
    public static final String PREFIX_MONGODB = "spring.data.mongodb.";
    public static final String PREFIX_RABBITMQ = "spring.rabbitmq.";
    public static final String PREFIX_KAFKA = "spring.kafka.";
    public static final String PREFIX_SECURITY = "spring.security.";
    public static final String PREFIX_CLOUD = "spring.cloud.";
    public static final String PREFIX_NACOS = "spring.cloud.nacos.";
    public static final String PREFIX_FEIGN = "feign.";
    public static final String PREFIX_GATEWAY = "spring.cloud.gateway.";

    // 配置刷新相关
    public static final String REFRESH_ENDPOINT = "/actuator/refresh";
    public static final String REFRESH_PARAM_KEY = "refresh";
    public static final String REFRESH_PARAM_VALUE = "true";

    // 配置热加载
    public static final String HOTSWAP_ENABLED = "hotswap.enabled";
    public static final String HOTSWAP_INTERVAL = "hotswap.interval";

    // 配置缓存
    public static final String CACHE_ENABLED = "config.cache.enabled";
    public static final String CACHE_TTL = "config.cache.ttl";

    // 配置加密
    public static final String ENCRYPT_KEY = "encrypt.key";
    public static final String ENCRYPT_IV = "encrypt.iv";
    public static final String ENCRYPT_ALGORITHM = "encrypt.algorithm";

    // 配置验证
    public static final String VALIDATION_ENABLED = "config.validation.enabled";
    public static final String VALIDATION_SCHEMA = "config.validation.schema";

    // 配置默认值
    public static final String DEFAULT_VALUE_PREFIX = "${";
    public static final String DEFAULT_VALUE_SUFFIX = "}";
    public static final String DEFAULT_VALUE_SEPARATOR = ":";

    // 配置占位符
    public static final String PLACEHOLDER_PREFIX = "#{";
    public static final String PLACEHOLDER_SUFFIX = "}";
    public static final String PLACEHOLDER_ESCAPE_CHAR = "\\";

    // 配置文件编码
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    public static final String ENCODING_ISO8859_1 = "ISO-8859-1";

    // 配置加载顺序
    public static final int ORDER_SYSTEM_PROPERTIES = 100;
    public static final int ORDER_APPLICATION_PROPERTIES = 200;
    public static final int ORDER_BOOTSTRAP_PROPERTIES = 300;
    public static final int ORDER_ENVIRONMENT_VARIABLES = 400;
    public static final int ORDER_COMMAND_LINE_ARGS = 500;

    // 配置类型
    public static final String TYPE_STRING = "string";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_LONG = "long";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_LIST = "list";
    public static final String TYPE_MAP = "map";
    public static final String TYPE_JSON = "json";

    // 配置验证规则
    public static final String VALIDATION_REQUIRED = "required";
    public static final String VALIDATION_NOT_NULL = "not_null";
    public static final String VALIDATION_NOT_EMPTY = "not_empty";
    public static final String VALIDATION_MIN = "min";
    public static final String VALIDATION_MAX = "max";
    public static final String VALIDATION_REGEX = "regex";
    public static final String VALIDATION_EMAIL = "email";
    public static final String VALIDATION_PHONE = "phone";

    // 配置版本
    public static final String VERSION_CURRENT = "current";
    public static final String VERSION_LATEST = "latest";
    public static final String VERSION_PREVIOUS = "previous";

    // 配置备份
    public static final String BACKUP_ENABLED = "config.backup.enabled";
    public static final String BACKUP_LOCATION = "config.backup.location";
    public static final String BACKUP_RETENTION = "config.backup.retention";

    // 配置监听
    public static final String LISTENER_ENABLED = "config.listener.enabled";
    public static final String LISTENER_TOPIC = "config.listener.topic";
    public static final String LISTENER_GROUP = "config.listener.group";

    // 配置加密类型
    public static final String ENCRYPT_TYPE_NONE = "none";
    public static final String ENCRYPT_TYPE_AES = "aes";
    public static final String ENCRYPT_TYPE_RSA = "rsa";
    public static final String ENCRYPT_TYPE_SM4 = "sm4";

    // 配置文件格式
    public static final String FORMAT_FLAT = "flat";
    public static final String FORMAT_HIERARCHICAL = "hierarchical";
    public static final String FORMAT_TREE = "tree";

    // 配置同步
    public static final String SYNC_ENABLED = "config.sync.enabled";
    public static final String SYNC_INTERVAL = "config.sync.interval";
    public static final String SYNC_STRATEGY = "config.sync.strategy";

    // 配置快照
    public static final String SNAPSHOT_ENABLED = "config.snapshot.enabled";
    public static final String SNAPSHOT_LOCATION = "config.snapshot.location";
    public static final String SNAPSHOT_RETENTION = "config.snapshot.retention";

    // 配置变更通知
    public static final String NOTIFICATION_ENABLED = "config.notification.enabled";
    public static final String NOTIFICATION_TYPE = "config.notification.type";
    public static final String NOTIFICATION_TOPIC = "config.notification.topic";
}