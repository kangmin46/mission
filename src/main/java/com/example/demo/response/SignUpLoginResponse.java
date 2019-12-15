package com.example.demo.response;

public class SignUpLoginResponse {
    private String token;

    public SignUpLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
