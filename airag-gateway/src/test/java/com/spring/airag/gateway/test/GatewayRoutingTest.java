package com.spring.airag.gateway.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayRoutingTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testAuthServiceRouting() {
        // 测试认证服务路由
        webTestClient.get()
                .uri("/api/auth/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testDataServiceRouting() {
        // 测试数据服务路由
        webTestClient.get()
                .uri("/api/data/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testSceneServiceRouting() {
        // 测试场景服务路由
        webTestClient.get()
                .uri("/api/scene/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testSituationServiceRouting() {
        // 测试态势服务路由
        webTestClient.get()
                .uri("/api/situation/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testDecisionServiceRouting() {
        // 测试决策服务路由
        webTestClient.get()
                .uri("/api/decision/test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testWebSocketRouting() {
        // 测试WebSocket路由
        webTestClient.get()
                .uri("/ws/connection")
                .header("Authorization", "Bearer fake-token")
                .exchange()
                .expectStatus().isOk();
    }
}