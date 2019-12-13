package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RecordParserTest {

    @Test
    void parseFirstRecordTest() {
        List<String> parsedRecord = RecordParser.parseFirstRecord(Arrays.asList("연도","월","주택도시기금1)(억원)","국민은행(억원)","우리은행(억원)","신한은행(억원)","한국시티은행(억원)","하나은행(억원)","농협은행/수협은행","외환은행","기타은"));
        assertThat(parsedRecord).hasSize(9).containsExactly(
            "주택도시기금","국민은행", "우리은행", "신한은행", "한국시티은행",
            "하나은행", "농협은행/수협은행",
            "외환은행","기타은"
        );
    }

}
