package com.spring.airag.common.constants;

/**
 * 微服务通信相关常量类
 */
public class RpcConstants {

    // RPC框架类型
    public static final String RPC_TYPE_FEIGN = "FEIGN";
    public static final String RPC_TYPE_DUBBO = "DUBBO";
    public static final String RPC_TYPE_GRPC = "GRPC";
    public static final String RPC_TYPE_THRIFT = "THRIFT";
    public static final String RPC_TYPE_HTTP = "HTTP";

    // 通信协议
    public static final String PROTOCOL_HTTP = "HTTP";
    public static final String PROTOCOL_HTTPS = "HTTPS";
    public static final String PROTOCOL_TCP = "TCP";
    public static final String PROTOCOL_UDP = "UDP";
    public static final String PROTOCOL_GRPC = "GRPC";

    // 序列化方式
    public static final String SERIALIZE_JSON = "JSON";
    public static final String SERIALIZE_PROTOBUF = "PROTOBUF";
    public static final String SERIALIZE_HESSIAN = "HESSIAN";
    public static final String SERIALIZE_JAVA = "JAVA";
    public static final String SERIALIZE_XML = "XML";

    // 负载均衡策略
    public static final String LB_RULE_RANDOM = "RandomRule";
    public static final String LB_RULE_ROUND_ROBIN = "RoundRobinRule";
    public static final String LB_RULE_WEIGHTED_RESPONSE = "WeightedResponseTimeRule";
    public static final String LB_RULE_RETRY = "RetryRule";
    public static final String LB_RULE_BEST_AVAILABLE = "BestAvailableRule";
    public static final String LB_RULE_ZONE_AVOIDANCE = "ZoneAvoidanceRule";

    // 熔断器相关
    public static final String CIRCUIT_BREAKER_TYPE_SENTINEL = "SENTINEL";
    public static final String CIRCUIT_BREAKER_TYPE_HYSTRIX = "HYSTRIX";
    public static final String CIRCUIT_BREAKER_TYPE_RESSILIENCE4J = "RESILIENCE4J";

    // 熔断器状态
    public static final String CB_STATE_CLOSED = "CLOSED";
    public static final String CB_STATE_OPEN = "OPEN";
    public static final String CB_STATE_HALF_OPEN = "HALF_OPEN";

    // 超时配置
    public static final int TIMEOUT_CONNECT_DEFAULT = 2000; // 连接超时 2秒
    public static final int TIMEOUT_READ_DEFAULT = 5000;    // 读取超时 5秒
    public static final int TIMEOUT_WRITE_DEFAULT = 5000;   // 写入超时 5秒
    public static final int TIMEOUT_CALL_DEFAULT = 10000;   // 调用超时 10秒

    // 重试配置
    public static final int RETRY_COUNT_DEFAULT = 3;
    public static final int RETRY_COUNT_MAX = 5;
    public static final long RETRY_INTERVAL_DEFAULT = 1000L; // 1秒
    public static final long RETRY_INTERVAL_MAX = 5000L;     // 5秒

    // 连接池配置
    public static final int POOL_MAX_TOTAL_DEFAULT = 200;
    public static final int POOL_MAX_PER_ROUTE_DEFAULT = 50;
    public static final int POOL_MIN_IDLE_DEFAULT = 10;
    public static final int POOL_MAX_IDLE_DEFAULT = 50;

    // 请求相关
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_PUT = "PUT";
    public static final String REQUEST_METHOD_DELETE = "DELETE";
    public static final String REQUEST_METHOD_PATCH = "PATCH";

    // 响应相关
    public static final String RESPONSE_TYPE_JSON = "application/json";
    public static final String RESPONSE_TYPE_XML = "application/xml";
    public static final String RESPONSE_TYPE_TEXT = "text/plain";
    public static final String RESPONSE_TYPE_FORM = "application/x-www-form-urlencoded";

    // 负载均衡相关
    public static final String LB_STRATEGY_RANDOM = "random";
    public static final String LB_STRATEGY_ROUND_ROBIN = "round_robin";
    public static final String LB_STRATEGY_WEIGHTED = "weighted";
    public static final String LB_STRATEGY_LEAST_CONNECTIONS = "least_connections";
    public static final String LB_STRATEGY_IP_HASH = "ip_hash";

    // 服务发现相关
    public static final String DISCOVERY_STRATEGY_POLLING = "polling";
    public static final String DISCOVERY_STRATEGY_PUSH = "push";
    public static final String DISCOVERY_STRATEGY_PULL = "pull";

    // 路由相关
    public static final String ROUTE_TYPE_STATIC = "STATIC";
    public static final String ROUTE_TYPE_DYNAMIC = "DYNAMIC";
    public static final String ROUTE_TYPE_WEIGHTED = "WEIGHTED";
    public static final String ROUTE_TYPE_FAULT_TOLERANT = "FAULT_TOLERANT";

    // 容错相关
    public static final String FAULT_TOLERANCE_TYPE_FAIL_FAST = "FAIL_FAST";
    public static final String FAULT_TOLERANCE_TYPE_FAIL_OVER = "FAIL_OVER";
    public static final String FAULT_TOLERANCE_TYPE_FAIL_SAFE = "FAIL_SAFE";
    public static final String FAULT_TOLERANCE_TYPE_FAIL_BACK = "FAIL_BACK";
    public static final String FAULT_TOLERANCE_TYPE_FORKING = "FORKING";

    // 调用链相关
    public static final String TRACE_TYPE_OPENTRACING = "OPENTRACING";
    public static final String TRACE_TYPE_OPENTELEMETRY = "OPENTELEMETRY";
    public static final String TRACE_TYPE_ZIPKIN = "ZIPKIN";
    public static final String TRACE_TYPE_JAEGER = "JAEGAR";

    // 限流相关
    public static final String RATE_LIMIT_TYPE_TOKEN_BUCKET = "TOKEN_BUCKET";
    public static final String RATE_LIMIT_TYPE_LEAKY_BUCKET = "LEAKY_BUCKET";
    public static final String RATE_LIMIT_TYPE_COUNTER = "COUNTER";
    public static final String RATE_LIMIT_TYPE_SLIDING_WINDOW = "SLIDING_WINDOW";

    // 限流配置
    public static final int RATE_LIMIT_DEFAULT_PERMITS = 10; // 默认每秒请求数
    public static final int RATE_LIMIT_DEFAULT_BURST_CAPACITY = 20; // 默认突发容量

    // 服务降级相关
    public static final String FALLBACK_TYPE_RETURN_NULL = "RETURN_NULL";
    public static final String FALLBACK_TYPE_THROW_EXCEPTION = "THROW_EXCEPTION";
    public static final String FALLBACK_TYPE_USE_CACHE = "USE_CACHE";
    public static final String FALLBACK_TYPE_DEFAULT_VALUE = "DEFAULT_VALUE";

    // 服务注册相关
    public static final String REGISTRATION_STATUS_UP = "UP";
    public static final String REGISTRATION_STATUS_DOWN = "DOWN";
    public static final String REGISTRATION_STATUS_STARTING = "STARTING";
    public static final String REGISTRATION_STATUS_OUT_OF_SERVICE = "OUT_OF_SERVICE";

    // 心跳相关
    public static final long HEARTBEAT_INTERVAL_DEFAULT = 30000L; // 30秒
    public static final long HEARTBEAT_TIMEOUT_DEFAULT = 60000L; // 60秒

    // 健康检查相关
    public static final long HEALTH_CHECK_INTERVAL = 10000L; // 10秒
    public static final int HEALTH_CHECK_TIMEOUT = 5000;     // 5秒

    // 缓存相关
    public static final String CACHE_TYPE_LOCAL = "LOCAL";
    public static final String CACHE_TYPE_REMOTE = "REMOTE";
    public static final String CACHE_TYPE_MULTI_LEVEL = "MULTI_LEVEL";

    // 版本相关
    public static final String VERSION_DEFAULT = "1.0.0";
    public static final String VERSION_LATEST = "LATEST";

    // 环境相关
    public static final String ENVIRONMENT_DEV = "DEV";
    public static final String ENVIRONMENT_TEST = "TEST";
    public static final String ENVIRONMENT_PROD = "PROD";

    // 服务契约相关
    public static final String CONTRACT_TYPE_OPEN_API = "OPEN_API";
    public static final String CONTRACT_TYPE_PROTOBUF = "PROTOBUF";
    public static final String CONTRACT_TYPE_WSDL = "WSDL";

    // 服务契约版本
    public static final String CONTRACT_VERSION_V1 = "v1";
    public static final String CONTRACT_VERSION_V2 = "v2";
    public static final String CONTRACT_VERSION_V3 = "v3";
}