package com.example.demo.utils;

import com.example.demo.vo.StatisticsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    public List<StatisticsDto> mapToStatisticsDto(List<Object[]> statistics) {
        return statistics.stream()
            .map(this::generateStatisticsDto)
            .collect(Collectors.toList());
    }

    private StatisticsDto generateStatisticsDto(Object[] statistic) {
        String name = String.valueOf(statistic[0]);
        int amount = Integer.parseInt(statistic[1].toString());
        int year= Integer.parseInt(statistic[2].toString());
        return new StatisticsDto(name, amount, year);
    }

    public List<Double> mapToLinearRegressionData(List<Object[]> recommendDatas) {
        return recommendDatas.stream()
            .map(re -> re[0])
            .mapToDouble(re -> Double.parseDouble(re.toString()))
            .boxed()
            .collect(Collectors.toList());
    }
}
