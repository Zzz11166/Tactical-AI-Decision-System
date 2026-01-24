package com.spring.airag.gateway.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewaySecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testPublicEndpointAccess() {
        // 测试公共端点（如登录）应该可以无认证访问
        webTestClient.get()
                .uri("/api/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testProtectedEndpointWithoutAuthShouldFail() {
        // 测试受保护端点无认证时应该失败
        webTestClient.get()
                .uri("/api/data/test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void testProtectedEndpointWithBearerToken() {
        // 测试受保护端点带有Bearer Token时应该成功
        webTestClient.get()
                .uri("/api/data/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testOptionsRequestShouldPass() {
        // 测试OPTIONS请求应该可以通过（用于CORS预检）
        webTestClient.options()
                .uri("/api/data/test")
                .exchange()
                .expectStatus().isOk();
    }
}