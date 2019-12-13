package com.example.demo;

import com.example.demo.exception.InstitutionNotFoundException;

import java.util.Arrays;

public enum Institution {
    HOUSING_CITY_FUND("주택도시기금",1000),
    KOOKMIN("국민은행",1001),
    WOORI("우리은행",1002),
    SHINHAN("신한은행",1003),
    HANKOOK_CITY("한국시티은행",1004),
    HANNA("하나은행",1005),
    NONGHYUB_SOOHYUN("농협은행/수협은행",1006),
    WAEWHAN("외환은행",1007),
    ETC("기타은행",9999);

    private final String name;
    private final int code;

    Institution(final String name, final int code) {
        this.name = name;
        this.code = code;
    }

    public static Institution of(final String name) {
        return Arrays.stream(Institution.values())
            .filter(institution -> institution.getName().equals(name))
            .findAny()
            .orElseThrow(InstitutionNotFoundException::new);
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
