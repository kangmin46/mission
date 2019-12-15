package com.example.demo.exception;

public class MisMatchPasswordException extends RuntimeException {
    private static final String MIS_MATCH_PASSWORD_MESSAGE = "올바르지 않은 패스워드 입니다.";

    public MisMatchPasswordException() {
        super(MIS_MATCH_PASSWORD_MESSAGE);
    }
}
