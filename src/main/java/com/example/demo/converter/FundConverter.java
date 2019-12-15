package com.example.demo.converter;

import com.example.demo.Entity.Fund;
import com.example.demo.Entity.Institute;
import com.example.demo.Record;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FundConverter {
    public static final int YEAR_INDEX = 0;
    public static final int MONTH_INDEX = 1;
    public static final int AMOUNT_START_INDEX = 2;
    public static final int AMOUNT_END_INDEX = 11;

    public static List<Fund> toEntities(final Record record, final List<Institute> institutes) {
        List<Integer> parsedRecord = record.getColumns().stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        Integer year = parsedRecord.get(YEAR_INDEX);
        Integer month = parsedRecord.get(MONTH_INDEX);
        return IntStream.range(AMOUNT_START_INDEX, AMOUNT_END_INDEX)
            .mapToObj(i -> new Fund(year, month, parsedRecord.get(i), institutes.get(i - AMOUNT_START_INDEX)))
            .collect(Collectors.toList());
    }
}
