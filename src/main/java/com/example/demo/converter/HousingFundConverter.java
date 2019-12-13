package com.example.demo.converter;

import com.example.demo.Entity.Institute;
import com.example.demo.Entity.HousingFund;
import com.example.demo.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HousingFundConverter {
    private static final Logger logger = LoggerFactory.getLogger(HousingFundConverter.class);
    public static List<HousingFund> toEntities(final Record record, final List<Institute> institutes) {
        List<Integer> collect = record.getColumns().stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        Integer year = collect.get(0);
        Integer month = collect.get(1);
        logger.debug(">>> {}", institutes.size());
        return IntStream.range(2, 10)
            .mapToObj(i -> new HousingFund(year, month, collect.get(i), institutes.get(i - 2)))
            .collect(Collectors.toList());
    }
}
