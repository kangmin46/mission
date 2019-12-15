package com.example.demo.response;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstituteResponse that = (InstituteResponse) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
