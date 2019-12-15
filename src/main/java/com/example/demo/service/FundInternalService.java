package com.example.demo.service;

import com.example.demo.Entity.Fund;
import com.example.demo.repository.FundRepository;
import com.example.demo.utils.ObjectMapper;
import com.example.demo.vo.StatisticsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FundInternalService {

    private final FundRepository fundRepository;
    private final ObjectMapper objectMapper;

    public FundInternalService(FundRepository fundRepository, ObjectMapper objectMapper) {
        this.fundRepository = fundRepository;
        this.objectMapper = objectMapper;
    }

    public Fund save(final Fund fund) {
        return fundRepository.save(fund);
    }

    public List<StatisticsDto> findStatisticsDto() {
        List<Object[]> statistics = fundRepository.findStatistics();
        return objectMapper.mapToStatisticsDto(statistics);
    }

    public List<Double> findRecommendData(String name) {
        List<Object[]> recommendData = fundRepository.findRecommendData(name);
        return objectMapper.mapToLinearRegressionData(recommendData);
    }
}
