package com.example.demo.service;

import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.response.FundAverageMinMaxResponse;
import com.example.demo.response.InstituteResponse;
import com.example.demo.response.MaxInstituteResponse;
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
public class InstituteServiceTest {

    @InjectMocks
    private InstituteService instituteService;

    @Mock
    private InstituteInternalService instituteInternalService;

    @Mock
    private FundInternalService fundInternalService;


    @Test
    void find() {
        List<Institute> institutes = Arrays.asList(new Institute("외환은행", Institution.WAEWHAN.getCode()),
            new Institute("하나은행", Institution.HANNA.getCode()));

        when(instituteInternalService.findAll()).thenReturn(institutes);

        List<InstituteResponse> instituteResponses = instituteService.find();
        assertThat(instituteResponses).hasSize(2).containsExactly(new InstituteResponse("외환은행", Institution.WAEWHAN.getCode()),
            new InstituteResponse("하나은행", Institution.HANNA.getCode()));
    }

    @Test
    void find_average_min_max() {
        List<StatisticsDto> statisticsDtos = Arrays.asList(new StatisticsDto("외환은행", 1732, 2005),
            new StatisticsDto("외환은행", 2187, 2006));
        when(fundInternalService.findStatisticsDto()).thenReturn(statisticsDtos);

        FundAverageMinMaxResponse fundAverageMinMaxResponse = instituteService.findAverageMinMax("외환은행");
        assertThat(fundAverageMinMaxResponse.getMinMaxFunds()).hasSize(2);
        assertThat(fundAverageMinMaxResponse.getBank()).isEqualTo("외환은행");
    }

    @Test
    void find_max_institute() {
        List<StatisticsDto> statisticsDtos = Arrays.asList(new StatisticsDto("국민은행", 13231, 2005)
            , new StatisticsDto("기타은행", 1376, 2005)
            , new StatisticsDto("농협은행/수협은행", 1486, 2005));
        when(fundInternalService.findStatisticsDto()).thenReturn(statisticsDtos);

        MaxInstituteResponse maxInstitute = instituteService.findMaxInstitute();
        assertThat(maxInstitute.getBank()).isEqualTo("국민은행");
        assertThat(maxInstitute.getYear()).isEqualTo(2005);
    }
}
