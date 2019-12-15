package com.example.demo.utils;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    private final List<String[]> data;

    public CSVDataReader(String dataResourceName) {
        data = read(dataResourceName);
    }

    private static List<String[]> read(String dataResourceName) {
        ClassPathResource classPathResource = new ClassPathResource(dataResourceName);
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
