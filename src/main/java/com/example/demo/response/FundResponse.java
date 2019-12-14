package com.example.demo.response;

import com.example.demo.vo.AmountPerYear;

import java.util.List;

public class FundResponse {

    private String name = "주택금융 공급현황";
    private List<AmountPerYear> amountPerYear;

    public FundResponse(List<AmountPerYear> amountPerYear) {
        this.amountPerYear = amountPerYear;
    }

    public FundResponse() {
    }

    public String getName() {
        return name;
    }

    public List<AmountPerYear> getAmountPerYear() {
        return amountPerYear;
    }
}
