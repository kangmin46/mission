package com.example.demo;

import com.example.demo.Entity.HousingFund;
import com.example.demo.Entity.Institute;
import com.example.demo.converter.HousingFundConverter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HousingFundConverterTest {

    @Test
    void name() {
        List<Institute> institutes = Arrays.stream(Institution.values())
            .map(institution -> new Institute(institution.getName(), institution.getCode()))
            .collect(Collectors.toList());
        Record record = new Record(Arrays.asList("2005", "1", "1019", "846", "82", "95", "30", "157", "57", "80", "99"));

        List<HousingFund> housingFunds = HousingFundConverter
            .toEntities(record, institutes);
        assertThat(housingFunds).hasSize(9);
//            .containsExactly(
//                new HousingFund(2005, 1, 1009, institutes.get(0)),
//                new HousingFund(2005, 1, 846, institutes.get(1)),
//                new HousingFund(2005, 1, 82, institutes.get(2)),
//                new HousingFund(2005, 1, 95, institutes.get(3)),
//                new HousingFund(2005, 1, 30, institutes.get(4)),
//                new HousingFund(2005, 1, 157, institutes.get(5)),
//                new HousingFund(2005, 1, 57, institutes.get(6)),
//                new HousingFund(2005, 1, 80, institutes.get(7)),
//                new HousingFund(2005, 1, 99, institutes.get(8))
//            );
    }
}
