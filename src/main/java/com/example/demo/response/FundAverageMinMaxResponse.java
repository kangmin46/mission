package com.example.demo.response;

import com.example.demo.vo.MinMaxFund;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundAverageMinMaxResponse that = (FundAverageMinMaxResponse) o;
        return Objects.equals(bank, that.bank) &&
            Objects.equals(minMaxFunds, that.minMaxFunds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, minMaxFunds);
    }
}
