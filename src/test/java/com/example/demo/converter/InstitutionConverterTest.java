package com.example.demo.converter;

import com.example.demo.Entity.Institute;
import com.example.demo.Institution;
import com.example.demo.Record;
import com.example.demo.response.InstituteResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InstitutionConverterTest {

    @Test
    void to_emtities_test() {
        Record record = new Record(Arrays.asList("주택도시기금", "국민은행", "우리은행", "신한은행", "한국시티은행", "하나은행", "농협은행/수협은행", "외환은행", "기타은행"));
        List<Institute> institutes = InstituteConverter.toEntities(record);

        assertThat(institutes).hasSize(9)
            .containsExactly(
                new Institute("주택도시기금", Institution.HOUSING_CITY_FUND.getCode()),
                new Institute("국민은행", Institution.KOOKMIN.getCode()),
                new Institute("우리은행", Institution.WOORI.getCode()),
                new Institute("신한은행", Institution.SHINHAN.getCode()),
                new Institute("한국시티은행", Institution.HANKOOK_CITY.getCode()),
                new Institute("하나은행", Institution.HANNA.getCode()),
                new Institute("농협은행/수협은행", Institution.NONGHYUB_SOOHYUN.getCode()),
                new Institute("외환은행", Institution.WAEWHAN.getCode()),
                new Institute("기타은행", Institution.ETC.getCode())
            );
    }

    @Test
    void to_responses() {
        List<InstituteResponse> instituteResponses = InstituteConverter.toResponses(
            Arrays.asList(
                new Institute("주택도시기금", Institution.HOUSING_CITY_FUND.getCode()),
                new Institute("국민은행", Institution.KOOKMIN.getCode())));

        assertThat(instituteResponses.get(0).getCode()).isEqualTo(Institution.HOUSING_CITY_FUND.getCode());
        assertThat(instituteResponses.get(1).getCode()).isEqualTo(Institution.KOOKMIN.getCode());
    }
}
