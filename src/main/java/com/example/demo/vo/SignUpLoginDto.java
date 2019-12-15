package com.example.demo.vo;

public class SignUpLoginDto {
    private String id;
    private String password;

    public SignUpLoginDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public SignUpLoginDto() {
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
