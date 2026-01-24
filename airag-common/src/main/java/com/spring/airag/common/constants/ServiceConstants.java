package com.spring.airag.common.constants;

/**
 * 微服务相关常量类
 */
public class ServiceConstants {

    // 服务名称
    public static final String SERVICE_GATEWAY = "airag-gateway";
    public static final String SERVICE_AUTH = "airag-auth";
    public static final String SERVICE_DATA = "airag-data";
    public static final String SERVICE_SCENE = "airag-scene";
    public static final String SERVICE_SITUATION = "airag-situation";
    public static final String SERVICE_DECISION = "airag-decision";

    // 服务端口
    public static final int PORT_GATEWAY = 8888;
    public static final int PORT_AUTH = 8081;
    public static final int PORT_DATA = 8082;
    public static final int PORT_SITUATION = 8083;
    public static final int PORT_SCENE = 8084;
    public static final int PORT_DECISION = 8085;

    // 服务路径前缀
    public static final String PATH_AUTH = "/api/auth";
    public static final String PATH_DATA = "/api/data";
    public static final String PATH_SCENE = "/api/scene";
    public static final String PATH_SITUATION = "/api/situation";
    public static final String PATH_DECISION = "/api/decision";
    public static final String PATH_WS = "/ws";

    // 服务配置相关
    public static final String NACOS_NAMESPACE = "c18d0e3d-7b32-497b-8eab-cadf30a37454";
    public static final String NACOS_GROUP = "DEFAULT_GROUP";
    public static final String NACOS_CONFIG_FILE_EXTENSION = "yml";

    // 负载均衡策略
    public static final String LB_STRATEGY_RANDOM = "random";
    public static final String LB_STRATEGY_ROUND_ROBIN = "round_robin";
    public static final String LB_STRATEGY_WEIGHTED_RESPONSE = "weighted_response_time";

    // 服务注册相关
    public static final String REGISTRATION_STATUS_UP = "UP";
    public static final String REGISTRATION_STATUS_DOWN = "DOWN";
    public static final String REGISTRATION_STATUS_OUT_OF_SERVICE = "OUT_OF_SERVICE";

    // 服务发现相关
    public static final String DISCOVERY_ENABLED = "true";
    public static final String DISCOVERY_DISABLED = "false";

    // 服务熔断相关
    public static final String CIRCUIT_BREAKER_ENABLED = "true";
    public static final String CIRCUIT_BREAKER_DISABLED = "false";
    public static final int CIRCUIT_BREAKER_FAILURE_THRESHOLD = 50; // 失败率阈值
    public static final int CIRCUIT_BREAKER_SLEEP_WINDOW_MS = 60000; // 睡眠窗口（毫秒）

    // 服务限流相关
    public static final int RATE_LIMITER_DEFAULT_PERMITS = 10; // 默认每秒请求数
    public static final int RATE_LIMITER_DEFAULT_BURST_CAPACITY = 20; // 默认突发容量

    // 服务监控相关
    public static final String METRICS_ENDPOINT = "/actuator/metrics";
    public static final String HEALTH_ENDPOINT = "/actuator/health";
    public static final String INFO_ENDPOINT = "/actuator/info";

    // 服务配置刷新
    public static final String CONFIG_REFRESH_ENDPOINT = "/actuator/refresh";
    public static final String CONFIG_REFRESH_PARAM = "refresh";

    // 服务间通信相关
    public static final String FEIGN_CLIENT_DEFAULT_TIMEOUT = "5000";
    public static final String FEIGN_CLIENT_CONNECT_TIMEOUT = "3000";
    public static final String FEIGN_CLIENT_READ_TIMEOUT = "5000";

    // 服务安全相关
    public static final String SECURITY_JWT_HEADER = "Authorization";
    public static final String SECURITY_JWT_PREFIX = "Bearer ";
    public static final long SECURITY_JWT_EXPIRATION = 86400L; // 24小时（秒）
    public static final String SECURITY_JWT_SECRET = "mySecretKeyForAIRAGDemo";

    // 服务路由相关
    public static final String ROUTE_ID_AUTH = "auth-service";
    public static final String ROUTE_ID_DATA = "data-service";
    public static final String ROUTE_ID_SCENE = "scene-service";
    public static final String ROUTE_ID_SITUATION = "situation-service";
    public static final String ROUTE_ID_DECISION = "decision-service";
    public static final String ROUTE_ID_WEBSOCKET = "websocket-route";

    // 服务降级相关
    public static final String FALLBACK_ENABLED = "true";
    public static final String FALLBACK_DISABLED = "false";

    // 服务配置相关
    public static final String CONFIG_IMPORT_NACOS = "optional:nacos:";
    public static final String CONFIG_FILE_EXTENSION_YML = "yml";
    public static final String CONFIG_FILE_EXTENSION_YAML = "yaml";
    public static final String CONFIG_FILE_EXTENSION_PROPERTIES = "properties";

    // 服务健康检查相关
    public static final String HEALTH_INDICATOR_DISK_SPACE = "diskSpace";
    public static final String HEALTH_INDICATOR_DB = "db";
    public static final String HEALTH_INDICATOR_REDIS = "redis";
    public static final String HEALTH_INDICATOR_NACOS = "nacos";

    // 服务熔断器相关
    public static final String CIRCUIT_BREAKER_INSTANCE_NAME = "default";
    public static final String CIRCUIT_BREAKER_FALLBACK_METHOD = "fallback";
    public static final String CIRCUIT_BREAKER_ENABLED_PROPERTY = "resilience4j.circuitbreaker.enabled";

    // 服务限流器相关
    public static final String RATE_LIMITER_INSTANCE_NAME = "default";
    public static final String RATE_LIMITER_KEY_RESOLVER = "keyResolver";
    public static final String RATE_LIMITER_ENABLED_PROPERTY = "spring.cloud.gateway.filter.request-rate-limiter.enabled";

    // 服务注册中心相关
    public static final String REGISTRY_NACOS = "nacos";
    public static final String REGISTRY_EUREKA = "eureka";
    public static final String REGISTRY_CONSUL = "consul";
    public static final String REGISTRY_ZOOKEEPER = "zookeeper";

    // 服务配置中心相关
    public static final String CONFIG_CENTER_NACOS = "nacos";
    public static final String CONFIG_CENTER_APOLLO = "apollo";
    public static final String CONFIG_CENTER_SPRING_CLOUD_CONFIG = "spring-cloud-config";

    // 服务追踪相关
    public static final String TRACING_ENABLED = "tracing.enabled";
    public static final String TRACING_SAMPLE_RATE = "tracing.sample.rate";
    public static final String TRACING_EXPORTER = "tracing.exporter";

    // 服务网格相关
    public static final String SERVICE_MESH_ENABLED = "service.mesh.enabled";
    public static final String SERVICE_MESH_ISTIO = "istio";
    public static final String SERVICE_MESH_LINKERD = "linkerd";
}