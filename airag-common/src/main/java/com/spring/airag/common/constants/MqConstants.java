package com.spring.airag.common.constants;

/**
 * 消息队列相关常量类
 */
public class MqConstants {

    // 消息队列类型
    public static final String MQ_TYPE_ROCKETMQ = "ROCKETMQ";
    public static final String MQ_TYPE_RABBITMQ = "RABBITMQ";
    public static final String MQ_TYPE_KAFKA = "KAFKA";
    public static final String MQ_TYPE_REDIS = "REDIS";
    public static final String MQ_TYPE_ACTIVEMQ = "ACTIVEMQ";

    // 消息类型
    public static final String MSG_TYPE_STRING = "STRING";
    public static final String MSG_TYPE_OBJECT = "OBJECT";
    public static final String MSG_TYPE_BYTES = "BYTES";
    public static final String MSG_TYPE_MAP = "MAP";

    // 消息传递模式
    public static final String DELIVERY_MODE_PERSISTENT = "PERSISTENT";
    public static final String DELIVERY_MODE_NON_PERSISTENT = "NON_PERSISTENT";

    // 消息标签
    public static final String MSG_TAG_DEFAULT = "DEFAULT";
    public static final String MSG_TAG_ORDER = "ORDER";
    public static final String MSG_TAG_TRANSACTION = "TRANSACTION";
    public static final String MSG_TAG_DELAY = "DELAY";

    // 消息队列交换机类型
    public static final String EXCHANGE_TYPE_DIRECT = "DIRECT";
    public static final String EXCHANGE_TYPE_TOPIC = "TOPIC";
    public static final String EXCHANGE_TYPE_FANOUT = "FANOUT";
    public static final String EXCHANGE_TYPE_HEADERS = "HEADERS";

    // 消息队列路由键
    public static final String ROUTING_KEY_DEFAULT = "default";
    public static final String ROUTING_KEY_ALL = "#";
    public static final String ROUTING_KEY_SINGLE = "*";

    // 消息消费模式
    public static final String CONSUME_MODE_CLUSTERING = "CLUSTERING";
    public static final String CONSUME_MODE_BROADCASTING = "BROADCASTING";

    // 消息顺序性
    public static final String ORDERLY_MODE_TRUE = "ORDERLY";
    public static final String ORDERLY_MODE_FALSE = "CONCURRENTLY";

    // 消息重试相关
    public static final int MSG_RETRY_COUNT_DEFAULT = 3;
    public static final int MSG_RETRY_COUNT_MAX = 10;
    public static final long MSG_RETRY_INTERVAL_DEFAULT = 1000L; // 1秒
    public static final long MSG_RETRY_INTERVAL_MAX = 300000L; // 5分钟

    // 消息延迟级别
    public static final String DELAY_LEVEL_1S = "1s";
    public static final String DELAY_LEVEL_5S = "5s";
    public static final String DELAY_LEVEL_10S = "10s";
    public static final String DELAY_LEVEL_30S = "30s";
    public static final String DELAY_LEVEL_1M = "1m";
    public static final String DELAY_LEVEL_2M = "2m";
    public static final String DELAY_LEVEL_3M = "3m";
    public static final String DELAY_LEVEL_4M = "4m";
    public static final String DELAY_LEVEL_5M = "5m";
    public static final String DELAY_LEVEL_6M = "6m";
    public static final String DELAY_LEVEL_7M = "7m";
    public static final String DELAY_LEVEL_8M = "8m";
    public static final String DELAY_LEVEL_9M = "9m";
    public static final String DELAY_LEVEL_10M = "10m";
    public static final String DELAY_LEVEL_20M = "20m";
    public static final String DELAY_LEVEL_30M = "30m";
    public static final String DELAY_LEVEL_1H = "1h";
    public static final String DELAY_LEVEL_2H = "2h";

    // 消息队列配置相关
    public static final String CONFIG_NAMESRV_ADDR = "namesrvAddr";
    public static final String CONFIG_PRODUCER_GROUP = "producerGroup";
    public static final String CONFIG_CONSUMER_GROUP = "consumerGroup";
    public static final String CONFIG_TOPIC = "topic";
    public static final String CONFIG_TAGS = "tags";
    public static final String CONFIG_KEYS = "keys";

    // 消息队列服务器相关
    public static final String DEFAULT_NAMESRV_ADDR = "localhost:9876";
    public static final String DEFAULT_PRODUCER_GROUP = "producer_group";
    public static final String DEFAULT_CONSUMER_GROUP = "consumer_group";

    // 消息状态
    public static final String MSG_STATUS_SEND_OK = "SEND_OK";
    public static final String MSG_STATUS_SEND_FAILED = "SEND_FAILED";
    public static final String MSG_STATUS_CONSUME_SUCCESS = "CONSUME_SUCCESS";
    public static final String MSG_STATUS_CONSUME_FAILED = "CONSUME_FAILED";
    public static final String MSG_STATUS_RETRY = "RETRY";
    public static final String MSG_STATUS_DROP = "DROP";

    // 消息分区相关
    public static final String PARTITION_STRATEGY_HASH = "HASH";
    public static final String PARTITION_STRATEGY_ROUND_ROBIN = "ROUND_ROBIN";
    public static final String PARTITION_STRATEGY_RANDOM = "RANDOM";
    public static final String PARTITION_STRATEGY_CUSTOM = "CUSTOM";

    // 消息批处理相关
    public static final int BATCH_SIZE_DEFAULT = 100;
    public static final int BATCH_SIZE_MAX = 1000;
    public static final long BATCH_TIMEOUT_DEFAULT = 5000L; // 5秒

    // 消息压缩相关
    public static final String COMPRESSION_TYPE_NONE = "NONE";
    public static final String COMPRESSION_TYPE_GZIP = "GZIP";
    public static final String COMPRESSION_TYPE_SNAPPY = "SNAPPY";
    public static final String COMPRESSION_TYPE_LZ4 = "LZ4";

    // 消息确认模式
    public static final String ACK_MODE_AUTO = "AUTO";
    public static final String ACK_MODE_MANUAL = "MANUAL";
    public static final String ACK_MODE_NONE = "NONE";

    // 消息队列主题命名规范
    public static final String TOPIC_PREFIX_BUSINESS = "business_";
    public static final String TOPIC_PREFIX_SYSTEM = "system_";
    public static final String TOPIC_PREFIX_LOG = "log_";
    public static final String TOPIC_PREFIX_EVENT = "event_";

    // 消息队列队列相关
    public static final int QUEUE_NUM_DEFAULT = 4;
    public static final int QUEUE_NUM_MIN = 1;
    public static final int QUEUE_NUM_MAX = 64;

    // 消息过滤相关
    public static final String FILTER_TYPE_TAG = "TAG";
    public static final String FILTER_TYPE_SQL92 = "SQL92";
    public static final String FILTER_TYPE_CUSTOM = "CUSTOM";

    // 消息事务相关
    public static final String TRANSACTION_STATE_COMMIT = "COMMIT";
    public static final String TRANSACTION_STATE_ROLLBACK = "ROLLBACK";
    public static final String TRANSACTION_STATE_UNKNOWN = "UNKNOWN";

    // 消息队列连接相关
    public static final int CONNECT_TIMEOUT_DEFAULT = 3000; // 3秒
    public static final int CONNECT_TIMEOUT_MAX = 10000; // 10秒
    public static final int SOCKET_TIMEOUT_DEFAULT = 3000; // 3秒

    // 消息队列消费者相关
    public static final int CONSUMER_THREADS_MIN = 1;
    public static final int CONSUMER_THREADS_MAX = 64;
    public static final int CONSUMER_PULL_BATCH_SIZE = 32;
    public static final long CONSUMER_PULL_TIMEOUT = 10000L; // 10秒

    // 消息队列生产者相关
    public static final int PRODUCER_RETRY_TIMES = 2;
    public static final int PRODUCER_SEND_TIMEOUT = 3000; // 3秒
    public static final int PRODUCER_MAX_MESSAGE_SIZE = 4194304; // 4MB

    // 消息死信队列相关
    public static final String DLQ_ENABLED = "dlq.enabled";
    public static final String DLQ_NAME = "dlq.name";
    public static final String DLQ_ROUTING_KEY = "dlq.routing.key";
    public static final String DLQ_EXCHANGE = "dlq.exchange";
}