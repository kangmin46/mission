package com.example.demo.utils;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    private static final String DATA_RESOURCE_NAME = "사전과제3.csv";
    private final List<String[]> data;

    public CSVDataReader() {
        data = read();
    }

    private static List<String[]> read() {
        ClassPathResource classPathResource = new ClassPathResource(DATA_RESOURCE_NAME);
        List<String[]> csvData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(classPathResource.getInputStream()))) {
            for (String[] record : reader) {
                csvData.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    public String[] getHeader() {
        return data.get(0);
    }

    public List<String[]> getBody() {
        return data.subList(1, data.size());
    }


}
