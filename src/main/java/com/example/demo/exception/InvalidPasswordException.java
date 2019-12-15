package com.example.demo.exception;

public class InvalidPasswordException extends RuntimeException {
    private static final String INVALID_PASSWORD_MESSAGE = "올바른 패스워드가 아닙니다.";

    public InvalidPasswordException() {
        super(INVALID_PASSWORD_MESSAGE);
    }
}
