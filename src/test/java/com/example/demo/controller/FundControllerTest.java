package com.example.demo.controller;

import com.example.demo.BasicControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.example.demo.JwtInterceptor.AUTHORIZATION_FIELD;
import static com.example.demo.controller.FundController.FUND_URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
public class FundControllerTest extends BasicControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("데이터 저장 테스트")
    void save() {
        String token = signUp();
        saveData(token);
    }

    @Test
    @DisplayName("주택금융 공급 금융기관(은행) 목록을 출력하는 API 테스트")
    void find() {
        String token = signUp();
        saveData(token);
        webTestClient.get()
            .uri(FUND_URL)
            .header(AUTHORIZATION_FIELD, token)
            .exchange()
            .expectStatus()
            .isOk().expectBody()
            .jsonPath("$.name").isEqualTo("주택금융 공급현황")
            .jsonPath("$.amountPerYear").isArray();
    }

    @Test
    @DisplayName("특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API 테스트")
    void recommend() {
        String token = signUp();
        saveData(token);

        webTestClient.get()
            .uri(FUND_URL + "/predict/2/국민은행")
            .header(AUTHORIZATION_FIELD, token)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.bank").isEqualTo("bhk00")
            .jsonPath("$.year").isEqualTo(2018)
            .jsonPath("$.month").isEqualTo(2)
            .jsonPath("$.amount").isNotEmpty();
    }
}
