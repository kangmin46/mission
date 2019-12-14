package com.example.demo.service;

import com.example.demo.Entity.Fund;
import com.example.demo.Entity.Institute;
import com.example.demo.Record;
import com.example.demo.converter.StatisticsConverter;
import com.example.demo.utils.RecordParser;
import com.example.demo.converter.FundConverter;
import com.example.demo.response.FundResponse;
import com.example.demo.vo.StatisticsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundService.class);
    private final FundInternalService fundInternalService;

    public FundService(FundInternalService fundInternalService) {
        this.fundInternalService = fundInternalService;
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
        StatisticsConverter statisticsConverter = new StatisticsConverter();
        return statisticsConverter.convertToFundResponse(statisticsDtos);
    }
}
