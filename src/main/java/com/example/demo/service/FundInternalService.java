package com.example.demo.service;

import com.example.demo.Entity.Fund;
import com.example.demo.repository.FundRepository;
import com.example.demo.utils.DtoMapper;
import com.example.demo.vo.StatisticsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FundInternalService {

    private final FundRepository fundRepository;

    public FundInternalService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public Fund save(final Fund fund) {
        return fundRepository.save(fund);
    }

    public List<StatisticsDto> findStatisticsDto() {
        List<Object[]> statistics = fundRepository.findStatistics();
        DtoMapper dtoMapper = new DtoMapper();
        return dtoMapper.mapToStatisticsDto(statistics);
    }
}
