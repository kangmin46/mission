package com.example.demo.controller;

import com.example.demo.BasicControllerTest;
import com.example.demo.vo.SignUpLoginDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.example.demo.JwtInterceptor.AUTHORIZATION_FIELD;
import static com.example.demo.controller.UserController.USER_URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest extends BasicControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("유저 저장 API")
    @DirtiesContext
    void saveUser() {
        String token = signUp();
        saveData(token);
    }

    @Test
    @DisplayName("로그인 테스트")
    @DirtiesContext
    void login() {
        SignUpLoginDto signUpLoginDto = new SignUpLoginDto("robby", "password");
        String token = signUp();
        saveData(token);

        webTestClient.post()
            .uri(USER_URL + "/login")
            .contentType(MediaType.APPLICATION_JSON)
            .header(AUTHORIZATION_FIELD, token)
            .body(Mono.just(signUpLoginDto), SignUpLoginDto.class)
            .exchange()
            .expectStatus().isOk();
    }
}
