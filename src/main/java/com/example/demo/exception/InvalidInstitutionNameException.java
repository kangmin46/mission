package com.example.demo.exception;

public class InvalidInstitutionNameException extends RuntimeException {
    public static final String INVALID_INSTITUTION_NAME_MESSAGE = "올바른 기관 이름이 아닙니다.";

    public InvalidInstitutionNameException() {
        super(INVALID_INSTITUTION_NAME_MESSAGE);
    }
}
