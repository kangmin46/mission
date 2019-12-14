package com.example.demo.exception;

public class InvalidNumberException extends RuntimeException {
    public static final String INVALID_NUMBER_MESSAGE = "올바른 숫자를 입력해 주세요.";

    public InvalidNumberException() {
        super(INVALID_NUMBER_MESSAGE);
    }
}
