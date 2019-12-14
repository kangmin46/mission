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
    public static FundAverageMinMaxResponse convertToFundAverageMinMaxResponse(List<StatisticsDto> statisticsAboutInstitute, Institution institution) {
        StatisticsDto maxStatisticsDto = getMaxStatisticsDto(statisticsAboutInstitute);
        StatisticsDto minStatisticsDto = getMinStatisticsDto(statisticsAboutInstitute);

        List<MinMaxFund> minMaxFunds = getMinMaxFunds(maxStatisticsDto, minStatisticsDto);
        return new FundAverageMinMaxResponse(institution.getName(), minMaxFunds);
    }

    private static List<MinMaxFund> getMinMaxFunds(StatisticsDto maxStatisticsDto, StatisticsDto minStatisticsDto) {
        Integer maxYear = maxStatisticsDto.getYear();
        int maxAverage = Math.round(maxStatisticsDto.getAmount() / 12);
        Integer minYear = minStatisticsDto.getYear();
        int minAverage = Math.round(minStatisticsDto.getAmount() / 12);
        return Arrays.asList(new MinMaxFund(maxYear, maxAverage), new MinMaxFund(minYear, minAverage));
    }

    private static StatisticsDto getMinStatisticsDto(List<StatisticsDto> statisticsAboutInstitute) {
        return statisticsAboutInstitute.stream()
            .min(Comparator.comparing(StatisticsDto::getAmount))
            .orElseThrow(NoSuchElementException::new);
    }

    private static StatisticsDto getMaxStatisticsDto(List<StatisticsDto> statisticsAboutInstitute) {
        return statisticsAboutInstitute.stream()
            .max(Comparator.comparing(StatisticsDto::getAmount))
            .orElseThrow(NoSuchElementException::new);
    }

    public static FundResponse convertToFundResponse(List<StatisticsDto> statisticsDtos) {
        Map<Integer, List<StatisticsDto>> statisticsPerYear = getStatisticsPerYear(statisticsDtos);
        return generateFundResponse(statisticsPerYear);
    }

    private static FundResponse generateFundResponse(Map<Integer, List<StatisticsDto>> statisticsPerYear) {
        List<AmountPerYear> amountPerYears = new ArrayList<>();
        for (Integer year : statisticsPerYear.keySet()) {
            AmountPerYear amountPerYear = generateAmountPerYear(statisticsPerYear, year);
            amountPerYears.add(amountPerYear);
        }
        amountPerYears.sort(Comparator.comparing(AmountPerYear::getYear));
        return new FundResponse(amountPerYears);
    }

    private static AmountPerYear generateAmountPerYear(Map<Integer, List<StatisticsDto>> statisticsPerYear, Integer year) {
        int totalAmount = getTotalAmount(statisticsPerYear, year);
        Map<String, Integer> detailAmount = getDetailAmount(statisticsPerYear.get(year));
        return new AmountPerYear(year, totalAmount, detailAmount);
    }

    private static Map<String, Integer> getDetailAmount(List<StatisticsDto> statisticsDtos1) {
        return statisticsDtos1.stream()
            .collect(Collectors.toMap(StatisticsDto::getName, StatisticsDto::getAmount));
    }

    private static int getTotalAmount(Map<Integer, List<StatisticsDto>> statisticsPerYear, Integer year) {
        return statisticsPerYear.get(year).stream()
            .mapToInt(StatisticsDto::getAmount)
            .max()
            .getAsInt();
    }

    private static Map<Integer, List<StatisticsDto>> getStatisticsPerYear(List<StatisticsDto> statisticsDtos) {
        return statisticsDtos.stream()
            .collect(Collectors.groupingBy(StatisticsDto::getYear));
    }

    public static MaxInstituteResponse convertToMaxInstituteResponse(StatisticsDto statisticsDto) {
        return new MaxInstituteResponse(statisticsDto.getYear(), statisticsDto.getName());
    }
}
