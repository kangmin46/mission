package com.example.demo.converter;

import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.Record;

import java.util.List;
import java.util.stream.Collectors;

public class InstituteConverter {
    public static List<Institute> toEntities(final Record record) {
        return record.getColumns()
            .stream()
            .map(column -> new Institute(column, Institution.of(column).getCode()))
            .collect(Collectors.toList());
    }
}
