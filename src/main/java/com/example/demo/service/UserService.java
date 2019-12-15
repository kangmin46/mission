package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.vo.SignUpLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserInternalService userInternalService;

    public UserService(UserInternalService userInternalService) {
        this.userInternalService = userInternalService;
    }

    public void save(SignUpLoginDto signUpLoginDto) {
        userInternalService.save(signUpLoginDto);
    }

    @Transactional(readOnly = true)
    public void login(SignUpLoginDto signUpLoginDto) {
        userInternalService.findByName(signUpLoginDto.getId(), signUpLoginDto.getPassword());
    }
}
