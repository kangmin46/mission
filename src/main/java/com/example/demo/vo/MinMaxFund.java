package com.example.demo.vo;

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
}
