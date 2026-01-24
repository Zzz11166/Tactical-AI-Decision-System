package com.spring.airag.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

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

                // 数据服务路由（带限流）
                .route("data-service", r -> r.path("/api/data/**")
                        .filters(f -> f.stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())))
                        .uri("lb://airag-data"))

                .build();
    }

    /**
     * 安全配置
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 允许跨域预检请求
                        .pathMatchers("/api/auth/login", "/api/auth/register").permitAll() // 登录注册不需要认证
                        .pathMatchers("/api/auth/**").authenticated() // 认证相关接口需要认证
                        .pathMatchers("/api/data/**").authenticated() // 数据接口需要认证
                        .pathMatchers("/api/scene/**").authenticated() // 场景接口需要认证
                        .pathMatchers("/api/situation/**").authenticated() // 态势接口需要认证
                        .pathMatchers("/api/decision/**").authenticated() // 决策接口需要认证
                        .pathMatchers("/ws/**").authenticated() // WebSocket需要认证
                        .anyExchange().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable()); // 可以配置具体的CORS规则替代这个

        return http.build();
    }
    
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        // 默认每秒允许10个请求，突发容量为20
        return new RedisRateLimiter(10, 20);
    }
    
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(
            exchange.getRequest().getHeaders().getFirst("X-Forwarded-For"))
            .cast(String.class)
            .map(ip -> ip.replaceAll(":", "_")); // 防止IPv6中的冒号导致错误
    }
}