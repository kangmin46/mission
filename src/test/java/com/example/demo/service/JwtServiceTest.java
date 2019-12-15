package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Test
    @DisplayName("토큰 생성이 잘 되었는 지 테스트")
    void token() {
        String token = jwtService.createToken("kangmin2");
        assertThat(token).isNotNull();
        assertThat(jwtService.isValidToken(token)).isTrue();
    }
}
