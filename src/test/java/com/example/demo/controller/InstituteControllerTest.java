package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static com.example.demo.controller.FundController.FUND_URL;
import static com.example.demo.controller.InstituteController.INSTITUTE_URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class InstituteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("전체 년도(2005~2016)에서 은행코드 입력으로 들어온 은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API테스")
    void find_min_max_average() {
        webTestClient.post()
            .uri(FUND_URL)
            .exchange()
            .expectStatus().isOk();

        webTestClient.get()
            .uri(INSTITUTE_URL + "/averages?instituteName=외환은행")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.bank").isEqualTo("외환은행")
            .jsonPath("$.minMaxFunds").isArray();
    }

    @Test
    @DisplayName("각 년도 별 각 기관의 전체 지원금액 중에서 가장 큰금액의 기관명을 출력하는 API 테스")
    void find_max_institute() {
        webTestClient.post()
            .uri(FUND_URL)
            .exchange()
            .expectStatus().isOk();

        webTestClient.get()
            .uri(INSTITUTE_URL + "/max")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.year").isNotEmpty()
            .jsonPath("$.bank").isNotEmpty();
    }
}
