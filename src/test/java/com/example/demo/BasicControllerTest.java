package com.example.demo;

import com.example.demo.vo.SignUpLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.example.demo.JwtInterceptor.AUTHORIZATION_FIELD;
import static com.example.demo.controller.FundController.FUND_URL;
import static com.example.demo.controller.UserController.USER_URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BasicControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    protected void saveData(String token) {
        webTestClient.post()
            .uri(FUND_URL)
            .header(AUTHORIZATION_FIELD, token)
            .exchange()
            .expectStatus().isOk();
    }

    protected String signUp() {
        SignUpLoginDto signUpLoginDto = new SignUpLoginDto("robby", "password");

        return webTestClient.post()
            .uri(USER_URL + "/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(signUpLoginDto), SignUpLoginDto.class)
            .exchange()
            .expectBody(String.class)
            .returnResult()
            .getResponseBody();
    }
}
