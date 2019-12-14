package com.example.demo.response;

import com.example.demo.vo.MinMaxFund;

import java.util.List;

public class FundAverageMinMaxResponse {
    private final String bank;
    private final List<MinMaxFund> minMaxFunds;

    public FundAverageMinMaxResponse(String bank, List<MinMaxFund> minMaxFunds) {
        this.bank = bank;
        this.minMaxFunds = minMaxFunds;
    }

    public String getBank() {
        return bank;
    }

    public List<MinMaxFund> getMinMaxFunds() {
        return minMaxFunds;
    }
}
