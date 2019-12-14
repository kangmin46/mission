package com.example.demo.response;

public class MaxInstituteResponse {

    private int year;
    private String bank;

    public MaxInstituteResponse(int year, String bank) {
        this.year = year;
        this.bank = bank;
    }

    public int getYear() {
        return year;
    }

    public String getBank() {
        return bank;
    }
}
