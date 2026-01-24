package com.spring.airag.common.constants;

/**
 * 任务调度相关常量类
 */
public class JobConstants {

    // 任务状态
    public static final String JOB_STATUS_RUNNING = "RUNNING";
    public static final String JOB_STATUS_PAUSED = "PAUSED";
    public static final String JOB_STATUS_COMPLETED = "COMPLETED";
    public static final String JOB_STATUS_ERROR = "ERROR";
    public static final String JOB_STATUS_BLOCKED = "BLOCKED";
    public static final String JOB_STATUS_REMOVED = "REMOVED";
    public static final String JOB_STATUS_NOT_EXECUTE = "NOT_EXECUTE";

    // 任务类型
    public static final String JOB_TYPE_LOCAL = "LOCAL";
    public static final String JOB_TYPE_REMOTE = "REMOTE";
    public static final String JOB_TYPE_CLUSTER = "CLUSTER";

    // 任务执行策略
    public static final String JOB_STRATEGY_SERIAL = "SERIAL";
    public static final String JOB_STRATEGY_PARALLEL = "PARALLEL";
    public static final String JOB_STRATEGY_DISCARD = "DISCARD";
    public static final String JOB_STRATEGY_RUN_NOW = "RUN_NOW";

    // 任务触发类型
    public static final String TRIGGER_TYPE_SIMPLE = "SIMPLE";
    public static final String TRIGGER_TYPE_CRON = "CRON";
    public static final String TRIGGER_TYPE_CALENDAR_INTERVAL = "CALENDAR_INTERVAL";
    public static final String TRIGGER_TYPE_DAILY_TIME_INTERVAL = "DAILY_TIME_INTERVAL";

    // Cron表达式相关
    public static final String CRON_EVERY_SECOND = "0/1 * * * * ?";
    public static final String CRON_EVERY_5_SECONDS = "0/5 * * * * ?";
    public static final String CRON_EVERY_10_SECONDS = "0/10 * * * * ?";
    public static final String CRON_EVERY_30_SECONDS = "0/30 * * * * ?";
    public static final String CRON_EVERY_MINUTE = "0 * * * * ?";
    public static final String CRON_EVERY_5_MINUTES = "0 */5 * * * ?";
    public static final String CRON_EVERY_HOUR = "0 0 * * * ?";
    public static final String CRON_EVERY_DAY_MIDNIGHT = "0 0 0 * * ?";
    public static final String CRON_EVERY_WEEK = "0 0 0 * * 1";
    public static final String CRON_EVERY_MONTH = "0 0 0 1 * ?";

    // 任务优先级
    public static final int PRIORITY_HIGHEST = 10;
    public static final int PRIORITY_HIGH = 7;
    public static final int PRIORITY_NORMAL = 5;
    public static final int PRIORITY_LOW = 3;
    public static final int PRIORITY_LOWEST = 1;

    // 任务执行时间相关
    public static final long DEFAULT_START_DELAY = 1000L; // 1秒
    public static final long DEFAULT_PERIOD = 5000L; // 5秒
    public static final long DEFAULT_FIXED_RATE = 5000L; // 5秒
    public static final long DEFAULT_FIXED_DELAY = 5000L; // 5秒

    // 任务执行次数相关
    public static final int EXECUTION_COUNT_INFINITE = -1;
    public static final int EXECUTION_COUNT_ONCE = 1;
    public static final int EXECUTION_COUNT_TWICE = 2;

    // 任务分片相关
    public static final int SHARDING_COUNT_DEFAULT = 1;
    public static final int SHARDING_COUNT_MAX = 100;
    public static final String SHARDING_ITEM_PARAMETERS = "sharding.item.parameters";
    public static final String SHARDING_EXECUTOR_SERVICE_KEY = "sharding.executor.service";

    // 任务恢复策略
    public static final String RECOVER_STRATEGY_NONE = "NONE";
    public static final String RECOVER_STRATEGY_IMMEDIATE = "IMMEDIATE";
    public static final String RECOVER_STRATEGY_NEXT_CYCLE = "NEXT_CYCLE";

    // 任务监听器
    public static final String LISTENER_TYPE_JOB = "JOB_LISTENER";
    public static final String LISTENER_TYPE_TRIGGER = "TRIGGER_LISTENER";
    public static final String LISTENER_NOTIFY_IMMEDIATELY = "IMMEDIATE";
    public static final String LISTENER_NOTIFY_DEFERRED = "DEFERRED";

    // 任务持久化相关
    public static final String PERSISTENCE_TYPE_IN_MEMORY = "IN_MEMORY";
    public static final String PERSISTENCE_TYPE_JDBC = "JDBC";
    public static final String PERSISTENCE_TYPE_CUSTOM = "CUSTOM";

    // 任务执行引擎
    public static final String ENGINE_QUARTZ = "QUARTZ";
    public static final String ENGINE_SPRING_TASK = "SPRING_TASK";
    public static final String ENGINE_ELASTIC_JOB = "ELASTIC_JOB";
    public static final String ENGINE_XXL_JOB = "XXL_JOB";

    // 任务分组
    public static final String GROUP_DEFAULT = "DEFAULT";
    public static final String GROUP_SYSTEM = "SYSTEM";
    public static final String GROUP_BUSINESS = "BUSINESS";
    public static final String GROUP_MAINTENANCE = "MAINTENANCE";

    // 任务执行结果
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_FAILURE = "FAILURE";
    public static final String RESULT_TIMEOUT = "TIMEOUT";
    public static final String RESULT_INTERRUPTED = "INTERRUPTED";
    public static final String RESULT_SKIPPED = "SKIPPED";

    // 任务错误处理
    public static final String ERROR_STRATEGY_RETRY = "RETRY";
    public static final String ERROR_STRATEGY_ABORT = "ABORT";
    public static final String ERROR_STRATEGY_CONTINUE = "CONTINUE";
    public static final String ERROR_STRATEGY_NOTIFY = "NOTIFY";

    // 任务重试相关
    public static final int RETRY_COUNT_DEFAULT = 3;
    public static final int RETRY_COUNT_MAX = 10;
    public static final long RETRY_INTERVAL_DEFAULT = 1000L; // 1秒
    public static final long RETRY_INTERVAL_MAX = 300000L; // 5分钟

    // 任务依赖相关
    public static final String DEPENDENCY_TYPE_BEFORE = "BEFORE";
    public static final String DEPENDENCY_TYPE_AFTER = "AFTER";
    public static final String DEPENDENCY_TYPE_PARALLEL = "PARALLEL";

    // 任务通知相关
    public static final String NOTIFICATION_TYPE_EMAIL = "EMAIL";
    public static final String NOTIFICATION_TYPE_SMS = "SMS";
    public static final String NOTIFICATION_TYPE_WEBHOOK = "WEBHOOK";
    public static final String NOTIFICATION_TYPE_PUSH = "PUSH";

    // 任务监控相关
    public static final String MONITOR_TYPE_EXECUTION_TIME = "EXECUTION_TIME";
    public static final String MONITOR_TYPE_EXECUTION_COUNT = "EXECUTION_COUNT";
    public static final String MONITOR_TYPE_ERROR_RATE = "ERROR_RATE";
    public static final String MONITOR_TYPE_SUCCESS_RATE = "SUCCESS_RATE";

    // 任务配置相关
    public static final String CONFIG_KEY_JOB_DETAIL = "jobDetail";
    public static final String CONFIG_KEY_TRIGGER = "trigger";
    public static final String CONFIG_KEY_SCHEDULER = "scheduler";
    public static final String CONFIG_KEY_LISTENER = "listener";
    public static final String CONFIG_KEY_PLUGIN = "plugin";

    // 任务分片策略
    public static final String SHARDING_STRATEGY_ROUND_ROBIN = "ROUND_ROBIN";
    public static final String SHARDING_STRATEGY_AVERAGE = "AVERAGE";
    public static final String SHARDING_STRATEGY_HASH = "HASH";

    // 任务故障转移
    public static final String FAILOVER_ENABLED = "ENABLED";
    public static final String FAILOVER_DISABLED = "DISABLED";
    public static final String FAILOVER_STRATEGY_NEAREST = "NEAREST";
    public static final String FAILOVER_STRATEGY_ROUND_ROBIN = "ROUND_ROBIN";
}