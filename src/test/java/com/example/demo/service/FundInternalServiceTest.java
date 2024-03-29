package com.example.demo.service;

import com.example.demo.repository.FundRepository;
import com.example.demo.utils.ObjectMapper;
import com.example.demo.vo.StatisticsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FundInternalServiceTest {

    @InjectMocks
    private FundInternalService fundInternalService;

    @Mock
    private FundRepository fundRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void findStatisticsDto_test() {
        List<Object[]> statistics = new ArrayList<>();
        Object[] record = {"국민은행", "13231", "2005"};
        statistics.add(record);
        when(fundRepository.findStatistics()).thenReturn(statistics);
        when(objectMapper.mapToStatisticsDto(statistics)).thenReturn(Arrays.asList(new StatisticsDto("국민은행", 13231, 2005)));

        List<StatisticsDto> statisticsDto = fundInternalService.findStatisticsDto();
        assertThat(statisticsDto).hasSize(1)
            .containsExactly(new StatisticsDto("국민은행", 13231, 2005));
    }
}
