package com.spring.airag.gateway.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCompleteRequestFlow() {
        // 测试完整的请求流程：认证 -> 路由 -> 安全 -> 日志
        webTestClient.post()
                .uri("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"username\":\"test\",\"password\":\"password\"}")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testMultipleServiceAccess() {
        // 测试访问多个微服务
        // 访问认证服务
        webTestClient.get()
                .uri("/api/auth/health")
                .exchange()
                .expectStatus().isOk();

        // 访问数据服务
        webTestClient.get()
                .uri("/api/data/health")
                .header("Authorization", "Bearer fake-token")
                .exchange()
                .expectStatus().isOk();

        // 访问场景服务
        webTestClient.get()
                .uri("/api/scene/health")
                .header("Authorization", "Bearer fake-token")
                .exchange()
                .expectStatus().isOk();

        // 访问态势服务
        webTestClient.get()
                .uri("/api/situation/health")
                .header("Authorization", "Bearer fake-token")
                .exchange()
                .expectStatus().isOk();

        // 访问决策服务
        webTestClient.get()
                .uri("/api/decision/health")
                .header("Authorization", "Bearer fake-token")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testCORSHandling() {
        // 测试CORS处理
        webTestClient.options()
                .uri("/api/data/cors-test")
                .header("Origin", "http://localhost:3000")
                .header("Access-Control-Request-Method", "POST")
                .header("Access-Control-Request-Headers", "authorization,content-type")
                .exchange()
                .expectStatus().isOk();
    }
}