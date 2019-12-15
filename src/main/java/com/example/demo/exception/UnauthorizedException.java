package com.example.demo.exception;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

public class UnauthorizedException extends RuntimeException {
    private static final String UN_AUTHORIZED_MESSAGE = "권한이 유효하지 않습니다.";

    public UnauthorizedException() {
        super(UN_AUTHORIZED_MESSAGE);
    }
}
