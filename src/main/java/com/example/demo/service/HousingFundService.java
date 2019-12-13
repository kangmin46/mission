package com.example.demo.service;

import com.example.demo.Entity.Institute;
import com.example.demo.Entity.HousingFund;
import com.example.demo.Record;
import com.example.demo.RecordParser;
import com.example.demo.converter.InstituteConverter;
import com.example.demo.converter.HousingFundConverter;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class HousingFundService {
    private static final Logger logger = LoggerFactory.getLogger(HousingFundService.class);

    private final InstituteInternalService instituteInternalService;
    private final HousingFundInternalService housingFundInternalService;

    public HousingFundService(InstituteInternalService instituteInternalService, HousingFundInternalService housingFundInternalService) {
        this.instituteInternalService = instituteInternalService;
        this.housingFundInternalService = housingFundInternalService;
    }

    public void save() {
        try (CSVReader reader = new CSVReader(new FileReader("/Users/mac/kakao-mission/src/main/resources/사전과제3.csv"))) {
            String[] firstCSVRecord = reader.readNext();
            List<Institute> institutes = saveInstitutes(firstCSVRecord);
            for(String[] csvRecord : reader) {
                saveHousingFund(csvRecord, institutes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHousingFund(final String[] csvRecord, List<Institute> institutes) {
        List<String> parsedRecord = RecordParser.parseRecord(Arrays.asList(csvRecord));
        Record record = new Record(parsedRecord);
        List<HousingFund> housingFunds = HousingFundConverter.toEntities(record, institutes);
        housingFunds.forEach(housingFundInternalService::save);
    }

    public List<Institute> saveInstitutes(final String[] firstRecord) {
        List<String> parsedFirstRecord = RecordParser.parseFirstRecord(Arrays.asList(firstRecord));
        Record record = new Record(parsedFirstRecord);
        List<Institute> institutes = InstituteConverter.toEntities(record);
        institutes.forEach(instituteInternalService::save);
        return institutes;
    }
}
