package com.example.demo.controller;

import com.example.demo.repository.FundRepository;
import com.example.demo.service.FundInternalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
public class FundControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FundInternalService fundInternalService;

    @Test
    @DisplayName("데이터 저장 테스트")
    void save() {
        saveData();
    }

    @Test
    @DisplayName("주택금융 공급 금융기관(은행) 목록을 출력하는 API 테스")
    void find() {
        saveData();
        webTestClient.get()
            .uri("/funds")
            .exchange()
            .expectStatus()
            .isOk().expectBody()
        .jsonPath("$.name").isEqualTo("주택금융 공급현황")
            .jsonPath("$.amountPerYear").isArray();
    }

    private void saveData() {
        webTestClient.post()
            .uri("/funds")
            .exchange()
            .expectStatus().isOk();
    }
}
