package com.example.demo.exception;

public class InstitutionNotFoundException extends RuntimeException {
    public static final String INSTITUTE_NOT_FOUND_MESSAGE = "해당 기관을 찾을 수 없습니다.";

    public InstitutionNotFoundException() {
        super(INSTITUTE_NOT_FOUND_MESSAGE);
    }
}
