package com.example.demo.service;

import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.vo.Record;
import com.example.demo.converter.InstituteConverter;
import com.example.demo.converter.StatisticsConverter;
import com.example.demo.response.FundAverageMinMaxResponse;
import com.example.demo.response.InstituteResponse;
import com.example.demo.response.MaxInstituteResponse;
import com.example.demo.utils.RecordParser;
import com.example.demo.vo.StatisticsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstituteService {
    private final InstituteInternalService instituteInternalService;
    private final FundInternalService fundInternalService;

    public InstituteService(InstituteInternalService instituteInternalService, FundInternalService fundInternalService) {
        this.instituteInternalService = instituteInternalService;
        this.fundInternalService = fundInternalService;
    }

    public List<Institute> save(final String[] header) {
        List<String> parsedFirstRecord = RecordParser.parseFirstRecord(Arrays.asList(header));
        Record record = new Record(parsedFirstRecord);
        List<Institute> institutes = InstituteConverter.toEntities(record);
        institutes.forEach(instituteInternalService::save);
        return institutes;
    }

    @Transactional(readOnly = true)
    public List<InstituteResponse> find() {
        List<Institute> institutes = instituteInternalService.findAll();
        return InstituteConverter.toResponses(institutes);
    }

    @Transactional(readOnly = true)
    public FundAverageMinMaxResponse findAverageMinMax(String instituteName) {
        List<StatisticsDto> statisticsDtos = fundInternalService.findStatisticsDto();
        Institution institution = Institution.of(instituteName);
        List<StatisticsDto> statisticsAboutInstitute = getStatisticsAboutInstitute(statisticsDtos, institution);

        StatisticsDto maxStatisticsDto = getMaxStatisticsDto(statisticsAboutInstitute);
        StatisticsDto minStatisticsDto = getMinStatisticsDto(statisticsAboutInstitute);

        return StatisticsConverter.convertToFundAverageMinMaxResponse(maxStatisticsDto, minStatisticsDto, institution);
    }

    private static StatisticsDto getMinStatisticsDto(List<StatisticsDto> statisticsAboutInstitute) {
        return statisticsAboutInstitute.stream()
            .min(Comparator.comparing(StatisticsDto::getAmount))
            .orElseThrow(NoSuchElementException::new);
    }

    private static StatisticsDto getMaxStatisticsDto(List<StatisticsDto> statisticsAboutInstitute) {
        return statisticsAboutInstitute.stream()
            .max(Comparator.comparing(StatisticsDto::getAmount))
            .orElseThrow(NoSuchElementException::new);
    }

    private List<StatisticsDto> getStatisticsAboutInstitute(List<StatisticsDto> statisticsDtos, Institution institution) {
        return statisticsDtos.stream()
            .filter(statisticsDto -> statisticsDto.getName().equals(institution.getName()))
            .collect(Collectors.toList());
    }

    public MaxInstituteResponse findMaxInstitute() {
        List<StatisticsDto> statisticsDtos = fundInternalService.findStatisticsDto();
        StatisticsDto statisticsDto = statisticsDtos.stream()
            .max(Comparator.comparing(StatisticsDto::getAmount))
            .orElseThrow(NoSuchElementException::new);
        return StatisticsConverter.convertToMaxInstituteResponse(statisticsDto);
    }
}
