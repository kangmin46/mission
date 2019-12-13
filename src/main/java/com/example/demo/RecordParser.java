package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

public class RecordParser {

    public static List<String> parseFirstRecord(final List<String> record) {
        return record.stream()
            .filter(column -> !column.equals(""))
            .map(column -> column.replace("1)",""))
            .map(column -> column.replace("(억원)", ""))
            .collect(Collectors.toList())
            .subList(2, 11);
    }

    public static List<String> parseRecord(final List<String> record) {
        return record.stream()
            .filter(column -> !column.equals(""))
            .map(column -> column.replace(",", ""))
            .collect(Collectors.toList());
    }
}
