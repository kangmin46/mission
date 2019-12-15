package com.example.demo.controller;

import com.example.demo.response.SignUpLoginResponse;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import com.example.demo.vo.SignUpLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.example.demo.JwtInterceptor.AUTHORIZATION_FIELD;
import static com.example.demo.controller.UserController.USER_URL;

@RestController
@RequestMapping(USER_URL)
public class UserController {
    public static final String USER_URL = "/users";

    private final JwtService jwtService;
    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpLoginDto signUpLoginDto, HttpServletResponse httpServletResponse) {
        String token = jwtService.createToken(signUpLoginDto.getId());
        httpServletResponse.setHeader(AUTHORIZATION_FIELD, token);
        userService.save(signUpLoginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity.BodyBuilder login(@RequestBody SignUpLoginDto signUpLoginDto) {
        userService.login(signUpLoginDto);
        return ResponseEntity.ok();
    }
}
