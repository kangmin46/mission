package com.example.demo.utils;

import com.example.demo.exception.InvalidInstitutionNameException;
import com.example.demo.exception.InvalidNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecordParserTest {

    @Test
    @DisplayName("첫번째 줄 레코드 파싱")
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
    @DisplayName("등록되지 않은 기관이 입력으로 올 때 에러 테스트")
    void invalid_institutionName() {
        assertThrows(InvalidInstitutionNameException.class, () -> RecordParser.parseFirstRecord(Arrays.asList("연도", "월", "주택1)(억원)",
            "국민(억원)", "우리(억원)", "신한(억원)", "한국시티은행(억원)", "하나은행(억원)",
            "농협은행/수협은행", "외환은행", "기타은행")));
    }

    @Test
    @DisplayName("입력 데이터가 올바르지 않을 때")
    void invalid_number() {
        assertThrows(InvalidNumberException.class,
            () -> RecordParser.parseRecord(Arrays.asList("1", "2", "3", "4", "5", "6", "sas", "8", "9", "10", "11")));
    }

    @Test
    @DisplayName("입력 데이터가 공백일 때")
    void number_has_one_blank() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1", "2", "3", " ", "5", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1", "2", "3", "0", "5", "6", "7", "8", "9", "10", "11");
    }

    @Test
    @DisplayName("쉼표가 파싱 되는 지 테스트")
    void number_has_comma() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1,234", "2,334", "1,223,444", "4", "5,342", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1234", "2334", "1223444", "4", "5342", "6", "7", "8", "9", "10", "11");
    }

    @Test
    @DisplayName("공백이 들어왔을 때 테스트")
    void number_is_empty() {
        assertThat(RecordParser.parseRecord(Arrays.asList("1", "2", "3", "", "5", "6", "7", "8", "9", "10", "11")))
            .hasSize(11)
            .containsExactly("1", "2", "3", "0", "5", "6", "7", "8", "9", "10", "11");
    }
}
