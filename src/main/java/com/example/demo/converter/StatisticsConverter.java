package com.example.demo.converter;

import com.example.demo.Institution;
import com.example.demo.response.FundAverageMinMaxResponse;
import com.example.demo.response.FundResponse;
import com.example.demo.response.MaxInstituteResponse;
import com.example.demo.vo.AmountPerYear;
import com.example.demo.vo.MinMaxFund;
import com.example.demo.vo.StatisticsDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class StatisticsConverter {
    public static final int MONTH_COUNT = 12;
    public static FundAverageMinMaxResponse convertToFundAverageMinMaxResponse(StatisticsDto maxStatisticsDto, StatisticsDto minStatisticsDto, Institution institution) {
        List<MinMaxFund> minMaxFunds = getMinMaxFunds(maxStatisticsDto, minStatisticsDto);
        return new FundAverageMinMaxResponse(institution.getName(), minMaxFunds);
    }

    private static List<MinMaxFund> getMinMaxFunds(StatisticsDto maxStatisticsDto, StatisticsDto minStatisticsDto) {
        Integer maxYear = maxStatisticsDto.getYear();
        int maxAverage = Math.round(maxStatisticsDto.getAmount() / MONTH_COUNT);
        Integer minYear = minStatisticsDto.getYear();
        int minAverage = Math.round(minStatisticsDto.getAmount() / MONTH_COUNT);
        return Arrays.asList(new MinMaxFund(maxYear, maxAverage), new MinMaxFund(minYear, minAverage));
    }

    public static MaxInstituteResponse convertToMaxInstituteResponse(StatisticsDto statisticsDto) {
        return new MaxInstituteResponse(statisticsDto.getYear(), statisticsDto.getName());
    }
}
