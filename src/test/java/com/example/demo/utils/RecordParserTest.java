package com.example.demo.utils;

import com.example.demo.exception.InvalidInstitutionNameException;
import com.example.demo.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecordParserTest {

    @Test
    void parseFirstRecordTest() {
        List<String> parsedRecord = RecordParser.parseFirstRecord(Arrays.asList("연도", "월", "주택도시기금1)(억원)",
            "국민은행(억원)", "우리은행(억원)", "신한은행(억원)", "한국시티은행(억원)", "하나은행(억원)",
            "농협은행/수협은행", "외환은행", "기타은행"));
        assertThat(parsedRecord).hasSize(9).containsExactly(
            "주택도시기금", "국민은행", "우리은행", "신한은행", "한국시티은행",
            "하나은행", "농협은행/수협은행",
            "외환은행", "기타은행"
        );
    }

    @Test
    void invalid_institutionName() {
        assertThrows(InvalidInstitutionNameException.class, () -> RecordParser.parseFirstRecord(Arrays.asList("연도", "월", "주택1)(억원)",
            "국민(억원)", "우리(억원)", "신한(억원)", "한국시티은행(억원)", "하나은행(억원)",
            "농협은행/수협은행", "외환은행", "기타은행")));
    }

    @Test
    void invalid_number() {
        assertThrows(InvalidNumberException.class,
            () -> RecordParser.parseRecord(Arrays.asList("1", "2", "3", "4", "5", "6", "sas", "8", "9", "10", "11")));
    }

    @Test
    void number_has_one_blank() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1", "2", "3", " ", "5", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1", "2", "3", "0", "5", "6", "7", "8", "9", "10", "11");
    }

    @Test
    void number_has_comma() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1,234", "2,334", "1,223,444", "4", "5,342", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1234", "2334", "1223444", "4", "5342", "6", "7", "8", "9", "10", "11");
    }

    @Test
    void number_is_empty() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1", "2", "3", "", "5", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1", "2", "3", "0", "5", "6", "7", "8", "9", "10", "11");
    }
}
