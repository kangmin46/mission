package com.example.demo;

import com.example.demo.exception.InstitutionNotFoundException;
import com.example.demo.exception.InvalidInstitutionNameException;

import java.util.Arrays;

public enum Institution {
    HOUSING_CITY_FUND("주택도시기금","hou00"),
    KOOKMIN("국민은행","bhk00"),
    WOORI("우리은행","bhk01"),
    SHINHAN("신한은행","bhk02"),
    HANKOOK_CITY("한국시티은행","bhk03"),
    HANNA("하나은행","bhk04"),
    NONGHYUB_SOOHYUN("농협은행/수협은행","bhk05"),
    WAEWHAN("외환은행","bhk06"),
    ETC("기타은행","bhk07");

    private final String name;
    private final String code;

    Institution(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static Institution of(final String name) {
        return Arrays.stream(Institution.values())
            .filter(institution -> institution.getName().equals(name))
            .findAny()
            .orElseThrow(InstitutionNotFoundException::new);
    }

    public static Institution ofCode(final String code) {
        return Arrays.stream(Institution.values())
            .filter(institution -> institution.getCode().equals(code))
            .findAny()
            .orElseThrow(InstitutionNotFoundException::new);
    }

    public static String checkValidInstitution(final String name) {
        return Arrays.stream(Institution.values())
            .filter(institution -> name.contains(institution.getName()))
            .map(Institution::getName)
            .findFirst().orElseThrow(InvalidInstitutionNameException::new);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
