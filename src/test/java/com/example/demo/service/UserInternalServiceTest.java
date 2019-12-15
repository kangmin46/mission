package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserInternalServiceTest {

    @InjectMocks
    private UserInternalService userInternalService;

    @Mock
    private UserRepository userRepository;

    @Test
    void login() {
        String hashPassword = BCrypt.hashpw("password", BCrypt.gensalt());
        User user = new User("kangmin", hashPassword);
        when(userRepository.findByName("kangmin")).thenReturn(user);

        User findUser = userInternalService.findByName("kangmin", "password");
        assertThat(findUser.getName()).isEqualTo("kangmin");
    }
}
