package com.example.demo.exception;

public class InvalidUserNameException extends RuntimeException {
    private static final String INVALID_USER_NAME_MESSAGE = "올바른 유저 이름이 아닙니다.";

    public InvalidUserNameException() {
        super(INVALID_USER_NAME_MESSAGE);
    }
}
