package com.example.demo.converter;

import com.example.demo.Institution;
import com.example.demo.response.FundAverageMinMaxResponse;
import com.example.demo.vo.StatisticsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsConverterTest {
    public static final int MAX_AMOUNT = 61380;
    public static final int MIN_AMOUNT = 5811;
    private List<StatisticsDto> statisticsDtos;

    @BeforeEach
    void setUp() {
        statisticsDtos = Arrays.asList(
            new StatisticsDto("국민은행", 31480, 2017),
            new StatisticsDto("국민은행", MAX_AMOUNT, 2016),
            new StatisticsDto("국민은행", 57749, 2016),
            new StatisticsDto("국민은행", 48338, 2014),
            new StatisticsDto("국민은행", 33063, 2013),
            new StatisticsDto("국민은행", 37597, 2012),
            new StatisticsDto("국민은행", 29118, 2011),
            new StatisticsDto("국민은행", 16017, 2010),
            new StatisticsDto("국민은행", 8682, 2009),
            new StatisticsDto("국민은행", 12786, 2008),
            new StatisticsDto("국민은행", 8260, 2007),
            new StatisticsDto("국민은행", MIN_AMOUNT, 2006),
            new StatisticsDto("국민은행", 13231, 2005)
            );
    }

    @Test
    @DisplayName("한 은행에 대하여 최대값과 최소값을 잘 계산하고 매핑하는 지 테스")
    void convert_to_fund_average_min_max_response_test() {
        FundAverageMinMaxResponse fundAverageMinMaxResponse = StatisticsConverter
            .convertToFundAverageMinMaxResponse(statisticsDtos, Institution.ETC);
        assertThat(fundAverageMinMaxResponse.getBank()).isEqualTo("기타은행");
        assertThat(fundAverageMinMaxResponse.getMinMaxFunds().get(0).getAmount()).isEqualTo(MAX_AMOUNT / 12);
        assertThat(fundAverageMinMaxResponse.getMinMaxFunds().get(1).getAmount()).isEqualTo(MIN_AMOUNT / 12);
    }
}
