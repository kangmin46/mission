package com.example.demo.converter;

import com.example.demo.Entity.Fund;
import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.Record;
import com.example.demo.converter.FundConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FundConverterTest {

    @Test
    @DisplayName("Fund 엔티티로 잘 바꿔주는 지 테스트")
    void convertToEntities() {
        List<Institute> institutes = Arrays.stream(Institution.values())
            .map(institution -> new Institute(institution.getName(), institution.getCode()))
            .collect(Collectors.toList());
        Record record = new Record(Arrays.asList("2005", "1", "1019", "846", "82", "95", "30", "157", "57", "80", "99"));

        List<Fund> funds = FundConverter
            .toEntities(record, institutes);
        assertThat(funds).hasSize(9)
            .containsExactly(
                new Fund(2005, 1, 1009, institutes.get(0)),
                new Fund(2005, 1, 846, institutes.get(1)),
                new Fund(2005, 1, 82, institutes.get(2)),
                new Fund(2005, 1, 95, institutes.get(3)),
                new Fund(2005, 1, 30, institutes.get(4)),
                new Fund(2005, 1, 157, institutes.get(5)),
                new Fund(2005, 1, 57, institutes.get(6)),
                new Fund(2005, 1, 80, institutes.get(7)),
                new Fund(2005, 1, 99, institutes.get(8))
            );
    }
}
