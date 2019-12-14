package com.example.demo.vo;

import java.util.Map;

public class AmountPerYear {

    private final int year;
    private final int totalAmount;
    private final Map<String, Integer> detailAmount;

    public AmountPerYear(int year, int totalAmount, Map<String, Integer> detailAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
        this.detailAmount = detailAmount;
    }

    public int getYear() {
        return year;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Map<String, Integer> getDetailAmount() {
        return detailAmount;
    }
}
