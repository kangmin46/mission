package com.example.demo.response;

public class InstituteResponse {
    private final String name;
    private final String code;

    public InstituteResponse(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
