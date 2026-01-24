package com.spring.airag.common.constants;

/**
 * 缓存相关常量类
 */
public class CacheConstants {

    // 缓存类型
    public static final String CACHE_TYPE_LOCAL = "LOCAL";
    public static final String CACHE_TYPE_REDIS = "REDIS";
    public static final String CACHE_TYPE_MEMCACHED = "MEMCACHED";
    public static final String CACHE_TYPE_EHCACHE = "EHCACHE";
    public static final String CACHE_TYPE_CAFFEINE = "CAFFEINE";
    public static final String CACHE_TYPE_HAZELCAST = "HAZELCAST";
    public static final String CACHE_TYPE_INFINISPAN = "INFINISPAN";

    // Redis相关
    public static final String REDIS_HOST = "localhost";
    public static final int REDIS_PORT = 6379;
    public static final int REDIS_DATABASE = 0;
    public static final String REDIS_PASSWORD = "";
    public static final int REDIS_TIMEOUT = 2000;

    // Redis连接池相关
    public static final int REDIS_POOL_MAX_TOTAL = 8;
    public static final int REDIS_POOL_MAX_IDLE = 8;
    public static final int REDIS_POOL_MIN_IDLE = 0;
    public static final long REDIS_POOL_MAX_WAIT_MILLIS = -1L;

    // 缓存键前缀
    public static final String CACHE_KEY_PREFIX_USER = "user:";
    public static final String CACHE_KEY_PREFIX_ROLE = "role:";
    public static final String CACHE_KEY_PREFIX_PERMISSION = "permission:";
    public static final String CACHE_KEY_PREFIX_MENU = "menu:";
    public static final String CACHE_KEY_PREFIX_DICT = "dict:";
    public static final String CACHE_KEY_PREFIX_CONFIG = "config:";
    public static final String CACHE_KEY_PREFIX_TOKEN = "token:";
    public static final String CACHE_KEY_PREFIX_RATE_LIMIT = "rate_limit:";

    // 缓存过期时间（秒）
    public static final int CACHE_EXPIRE_TIME_SHORT = 300;      // 5分钟
    public static final int CACHE_EXPIRE_TIME_MEDIUM = 1800;    // 30分钟
    public static final int CACHE_EXPIRE_TIME_LONG = 3600;      // 1小时
    public static final int CACHE_EXPIRE_TIME_VERY_LONG = 86400; // 24小时
    public static final int CACHE_EXPIRE_TIME_NEVER = 0;        // 永不过期

    // 缓存命名空间
    public static final String CACHE_NAMESPACE_COMMON = "common";
    public static final String CACHE_NAMESPACE_USER = "user";
    public static final String CACHE_NAMESPACE_AUTH = "auth";
    public static final String CACHE_NAMESPACE_SYSTEM = "system";
    public static final String CACHE_NAMESPACE_BUSINESS = "business";

    // 缓存操作相关
    public static final String CACHE_OPERATION_GET = "get";
    public static final String CACHE_OPERATION_SET = "set";
    public static final String CACHE_OPERATION_DEL = "del";
    public static final String CACHE_OPERATION_UPDATE = "update";
    public static final String CACHE_OPERATION_CLEAR = "clear";

    // 缓存统计相关
    public static final String CACHE_STATS_HITS = "hits";
    public static final String CACHE_STATS_MISSES = "misses";
    public static final String CACHE_STATS_GETS = "gets";
    public static final String CACHE_STATS_SETS = "sets";
    public static final String CACHE_STATS_DELETES = "deletes";

    // 缓存键相关
    public static final String CACHE_KEY_SEPARATOR = ":";
    public static final String CACHE_KEY_WILDCARD = "*";
    public static final String CACHE_KEY_PATTERN_USER_INFO = CACHE_KEY_PREFIX_USER + "%s:info";
    public static final String CACHE_KEY_PATTERN_USER_ROLES = CACHE_KEY_PREFIX_USER + "%s:roles";
    public static final String CACHE_KEY_PATTERN_USER_PERMISSIONS = CACHE_KEY_PREFIX_USER + "%s:permissions";

    // 缓存策略
    public static final String CACHE_STRATEGY_READ_THROUGH = "read_through";
    public static final String CACHE_STRATEGY_WRITE_THROUGH = "write_through";
    public static final String CACHE_STRATEGY_WRITE_BEHIND = "write_behind";
    public static final String CACHE_STRATEGY_CACHE_ASIDE = "cache_aside";

    // 缓存一致性相关
    public static final String CACHE_CONSISTENCY_EVENTUAL = "eventual";
    public static final String CACHE_CONSISTENCY_STRONG = "strong";
    public static final String CACHE_CONSISTENCY_WEAK = "weak";

    // 缓存配置相关
    public static final String CACHE_CONFIG_MAX_MEMORY = "max_memory";
    public static final String CACHE_CONFIG_MAX_ENTRIES = "max_entries";
    public static final String CACHE_CONFIG_EVICTION_POLICY = "eviction_policy";
    public static final String CACHE_CONFIG_EXPIRY_POLICY = "expiry_policy";

    // 缓存驱逐策略
    public static final String CACHE_EVICTION_POLICY_LRU = "LRU";
    public static final String CACHE_EVICTION_POLICY_LFU = "LFU";
    public static final String CACHE_EVICTION_POLICY_FIFO = "FIFO";
    public static final String CACHE_EVICTION_POLICY_TTL = "TTL";

    // 分布式锁相关
    public static final String DISTRIBUTED_LOCK_PREFIX = "lock:";
    public static final int DISTRIBUTED_LOCK_DEFAULT_TIMEOUT = 30; // 秒
    public static final int DISTRIBUTED_LOCK_RETRY_INTERVAL = 100; // 毫秒
    public static final int DISTRIBUTED_LOCK_MAX_RETRY_TIMES = 5;

    // 缓存健康检查相关
    public static final String CACHE_HEALTH_CHECK_KEY = "health_check";
    public static final String CACHE_HEALTH_CHECK_VALUE = "ok";
    public static final int CACHE_HEALTH_CHECK_TIMEOUT = 1000; // 毫秒

    // 缓存区域相关
    public static final String CACHE_REGION_DEFAULT = "default";
    public static final String CACHE_REGION_SESSION = "session";
    public static final String CACHE_REGION_DATA = "data";
    public static final String CACHE_REGION_METADATA = "metadata";
    public static final String CACHE_REGION_QUERY = "query";

    // 缓存序列化方式
    public static final String CACHE_SERIALIZE_JDK = "jdk";
    public static final String CACHE_SERIALIZE_JSON = "json";
    public static final String CACHE_SERIALIZE_PROTOBUF = "protobuf";
    public static final String CACHE_SERIALIZE_KRYO = "kryo";

    // 缓存模式
    public static final String CACHE_MODE_LOCAL = "local";
    public static final String CACHE_MODE_CLUSTER = "cluster";
    public static final String CACHE_MODE_REPLICA = "replica";
    public static final String CACHE_MODE_PARTITION = "partition";

    // 缓存持久化
    public static final String CACHE_PERSISTENCE_NONE = "none";
    public static final String CACHE_PERSISTENCE_RDB = "rdb";
    public static final String CACHE_PERSISTENCE_AOF = "aof";
    public static final String CACHE_PERSISTENCE_BOTH = "both";

    // 缓存压缩
    public static final String CACHE_COMPRESS_NONE = "none";
    public static final String CACHE_COMPRESS_GZIP = "gzip";
    public static final String CACHE_COMPRESS_LZ4 = "lz4";
    public static final String CACHE_COMPRESS_SNAPPY = "snappy";

    // 缓存监控指标
    public static final String CACHE_METRIC_HITS_RATIO = "hit_ratio";
    public static final String CACHE_METRIC_MISS_RATIO = "miss_ratio";
    public static final String CACHE_METRIC_EVICTIONS = "evictions";
    public static final String CACHE_METRIC_MEMORY_USED = "memory_used";
    public static final String CACHE_METRIC_EXPIRED = "expired";

    // 缓存标签
    public static final String CACHE_TAG_USER = "user";
    public static final String CACHE_TAG_SESSION = "session";
    public static final String CACHE_TAG_PERMISSION = "permission";
    public static final String CACHE_TAG_CONFIG = "config";
    public static final String CACHE_TAG_DICTIONARY = "dictionary";
    public static final String CACHE_TAG_MENU = "menu";

    // 缓存预热相关
    public static final String CACHE_WARM_UP_ENABLED = "warm_up_enabled";
    public static final String CACHE_WARM_UP_STRATEGY = "warm_up_strategy";
    public static final String CACHE_WARM_UP_TIME = "warm_up_time";
}