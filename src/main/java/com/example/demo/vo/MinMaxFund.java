package com.example.demo.vo;

import java.util.Objects;

public class MinMaxFund {
    private final int year;
    private final int amount;

    public MinMaxFund(int year, int amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinMaxFund that = (MinMaxFund) o;
        return year == that.year &&
            amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, amount);
    }
}
