package com.example.demo.vo;

import java.util.Objects;

public class StatisticsDto {
    private String name;
    private Integer amount;
    private Integer year;

    public StatisticsDto(String name, Integer amount, Integer year) {
        this.name = name;
        this.amount = amount;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsDto that = (StatisticsDto) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, year);
    }
}
