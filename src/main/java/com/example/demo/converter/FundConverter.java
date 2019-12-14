package com.example.demo.converter;

import com.example.demo.Entity.Fund;
import com.example.demo.Entity.Institute;
import com.example.demo.Record;
import com.example.demo.response.FundResponse;
import com.example.demo.response.MaxInstituteResponse;
import com.example.demo.vo.AmountPerYear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FundConverter {
    private static final Logger logger = LoggerFactory.getLogger(FundConverter.class);
    public static List<Fund> toEntities(final Record record, final List<Institute> institutes) {
        List<Integer> collect = record.getColumns().stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        Integer year = collect.get(0);
        Integer month = collect.get(1);
        logger.debug(">>> {}", institutes.size());
        return IntStream.range(2, 11)
            .mapToObj(i -> new Fund(year, month, collect.get(i), institutes.get(i - 2)))
            .collect(Collectors.toList());
    }
}
