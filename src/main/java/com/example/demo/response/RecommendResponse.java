package com.example.demo.response;

public class RecommendResponse {
    private final Integer year;
    private final String bank;
    private final Integer month;
    private final Double amount;

    public RecommendResponse(int year, String bank, Integer month, Double amount) {
        this.year = year;
        this.bank = bank;
        this.month = month;
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public Integer getMonth() {
        return month;
    }

    public Double getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }
}
