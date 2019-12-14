package com.example.demo.utils;

import com.example.demo.Institution;
import com.example.demo.exception.InvalidNumberException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecordParser {
    private static final String EMPTY_NUMBER = "0";
    private static final String BLANK_DELIMITER = " ";
    private static final String EMPTY_DELIMITER = "";
    private static final String COMMA_DELIMITER = ",";
    private static final String NUMBER_REGEX = "^[0-9]*$";
    private static final int COLUMN_MAX_SIZE = 11;
    private static final int INSTITUTE_MIN_COLUMN_INDEX = 2;
    private static final int RECORD_MIN_COLUMN_INDEX = 0;

    public static List<String> parseFirstRecord(final List<String> record) {
        return record.subList(INSTITUTE_MIN_COLUMN_INDEX, COLUMN_MAX_SIZE)
            .stream()
            .map(Institution::checkValidInstitution)
            .collect(Collectors.toList());
    }

    public static List<String> parseRecord(final List<String> record) {
        return record.subList(RECORD_MIN_COLUMN_INDEX, COLUMN_MAX_SIZE)
            .stream()
            .map(RecordParser::parseComma)
            .map(RecordParser::parseBlankToZero)
            .map(RecordParser::checkNumber)
            .collect(Collectors.toList());
    }

    public static String parseComma(String column) {
        return column.replace(COMMA_DELIMITER, EMPTY_DELIMITER);
    }

    private static String parseBlankToZero(String column) {
        column = column.replaceAll(BLANK_DELIMITER, EMPTY_DELIMITER);
        if (column.isEmpty()) {
            column = EMPTY_NUMBER;
        }
        return column;
    }

    private static String checkNumber(String column) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(column);
        if (matcher.find()) {
            return column;
        }
        throw new InvalidNumberException();
    }
}
