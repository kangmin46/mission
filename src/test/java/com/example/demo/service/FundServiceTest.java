package com.example.demo.service;

import com.example.demo.response.FundResponse;
import com.example.demo.response.RecommendResponse;
import com.example.demo.utils.LinearRegression;
import com.example.demo.vo.AmountPerYear;
import com.example.demo.vo.StatisticsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FundServiceTest {

    @InjectMocks
    private FundService fundService;

    @Mock
    private FundInternalService fundInternalService;

    @Mock
    private LinearRegression linearRegression;

    @Test
    void find() {
        List<StatisticsDto> statisticsDtos = Arrays.asList(new StatisticsDto("외환은행", 1000, 2005),
            new StatisticsDto("국민은행", 1500, 2008));
        when(fundInternalService.findStatisticsDto()).thenReturn(statisticsDtos);

        FundResponse fundResponse = fundService.find();
        AmountPerYear firstAmountPerYear = fundResponse.getAmountPerYear().get(0);

        assertThat(fundResponse.getName()).isEqualTo("주택금융 공급현황");
        assertThat(firstAmountPerYear.getTotalAmount()).isEqualTo(1000);
        assertThat(firstAmountPerYear.getYear()).isEqualTo(2005);
    }

    @Test
    void recommend() {
        List<Double> recommendDatas = Arrays.asList(864.0, 416.0, 263.0, 1659.0, 394.0, 2233.0, 1140.0, 2527.0, 3486.0, 2932.0, 3906.0, 5073.0, 3278.0);
        when(fundInternalService.findRecommendData("국민은행")).thenReturn(recommendDatas);
        when(linearRegression.predict(recommendDatas, 14)).thenReturn(10.0);

        RecommendResponse recommendResponse = fundService.recommend(2, "국민은행");
        assertThat(recommendResponse.getBank()).isEqualTo("bhk00");
        assertThat(recommendResponse.getMonth()).isEqualTo(2);
        assertThat(recommendResponse.getYear()).isEqualTo(2018);
    }
}
