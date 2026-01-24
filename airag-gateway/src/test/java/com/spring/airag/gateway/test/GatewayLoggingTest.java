package com.spring.airag.gateway.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayLoggingTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testRequestLogging() {
        // 测试请求日志记录功能
        webTestClient.get()
                .uri("/api/data/log-test")
                .header("Authorization", "Bearer fake-token")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
                
        // 注意：此测试主要是为了触发日志记录，实际的日志验证可能需要自定义Appender
    }

    @Test
    public void testRequestLoggingWithoutAuth() {
        // 测试无认证请求的日志记录
        webTestClient.get()
                .uri("/api/auth/login")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}