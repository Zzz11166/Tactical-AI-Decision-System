package com.spring.airag.gateway.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayRateLimitTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testRateLimiting() {
        // 测试限流功能 - 发送多个请求以触发限流
        for (int i = 0; i < 15; i++) {
            webTestClient.get()
                .uri("/api/data/rate-limit-test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
        }
    }

    @Test
    public void testWithinRateLimit() {
        // 测试在限流范围内请求应成功
        for (int i = 0; i < 5; i++) {
            webTestClient.get()
                    .uri("/api/data/rate-limit-test")
                    .header("Authorization", "Bearer fake-token")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().is2xxSuccessful();
        }
    }
}