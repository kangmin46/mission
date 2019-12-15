package com.example.demo.service;

import com.example.demo.Entity.Fund;
import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.vo.Record;
import com.example.demo.converter.FundConverter;
import com.example.demo.response.FundResponse;
import com.example.demo.response.RecommendResponse;
import com.example.demo.utils.LinearRegression;
import com.example.demo.utils.RecordParser;
import com.example.demo.utils.TimeUtils;
import com.example.demo.vo.AmountPerYear;
import com.example.demo.vo.StatisticsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FundService {
    public static final int RECOMMEND_YEAR = 2018;

    private final FundInternalService fundInternalService;
    private final LinearRegression linearRegression;

    public FundService(FundInternalService fundInternalService, LinearRegression linearRegression) {
        this.fundInternalService = fundInternalService;
        this.linearRegression = linearRegression;
    }

    public void save(List<String[]> recordBody, List<Institute> institutes) {
        saveFunds(recordBody, institutes);
    }

    private void saveFunds(List<String[]> recordBody, List<Institute> institutes) {
        for (String[] record : recordBody) {
            saveFund(record, institutes);
        }
    }

    private void saveFund(final String[] csvRecord, List<Institute> institutes) {
        List<String> parsedRecord = RecordParser.parseRecord(Arrays.asList(csvRecord));
        Record record = new Record(parsedRecord);
        List<Fund> funds = FundConverter.toEntities(record, institutes);
        funds.forEach(fundInternalService::save);
    }

    public FundResponse find() {
        List<StatisticsDto> statisticsDtos = fundInternalService.findStatisticsDto();

        Map<Integer, List<StatisticsDto>> statisticsPerYear = getStatisticsPerYear(statisticsDtos);
        return generateFundResponse(statisticsPerYear);
    }

    private FundResponse generateFundResponse(Map<Integer, List<StatisticsDto>> statisticsPerYear) {
        List<AmountPerYear> amountPerYears = statisticsPerYear.keySet()
            .stream()
            .map(year -> generateAmountPerYear(statisticsPerYear, year))
            .collect(Collectors.toList());

        amountPerYears.sort(Comparator.comparing(AmountPerYear::getYear));

        return new FundResponse(amountPerYears);
    }

    private AmountPerYear generateAmountPerYear(Map<Integer, List<StatisticsDto>> statisticsPerYear, Integer year) {
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

    public RecommendResponse recommend(Integer month, String instituteCode) {
        List<Double> recommendDatas = fundInternalService.findRecommendData(instituteCode);

        int recommendDataSize = recommendDatas.size();
        int lastYearRemainMonth = TimeUtils.calculateLastYearRemainMonth(recommendDataSize);
        int predictVariable = recommendDataSize + lastYearRemainMonth + month;

        double predictResult = linearRegression.predict(recommendDatas, predictVariable);

        return new RecommendResponse(RECOMMEND_YEAR, Institution.of(instituteCode).getCode(), month, predictResult);
    }
}
