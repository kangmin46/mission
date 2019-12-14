package com.example.demo.utils;

import com.example.demo.vo.StatisticsDto;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

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
}
