package com.spring.airag.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关路由配置
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 认证服务路由
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://airag-auth"))

                // 数据管理服务路由
                .route("data-service", r -> r.path("/api/data/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://airag-data"))

                // 场景构设服务路由
                .route("scene-service", r -> r.path("/api/scene/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://airag-scene"))

                // 态势展示服务路由
                .route("situation-service", r -> r.path("/api/situation/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://airag-situation"))

                // AI决策服务路由
                .route("decision-service", r -> r.path("/api/decision/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://airag-decision"))

                // WebSocket路由
                .route("websocket-route", r -> r.path("/ws/**")
                        .uri("lb://airag-situation"))

                .build();
    }
}
