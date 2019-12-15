package com.example.demo.utils;

public class TimeUtils {
    public static final int MONTH_COUNT = 12;

    public static int calculateLastYearRemainMonth(int size) {
        int lastYearMonth = size % MONTH_COUNT;
        return MONTH_COUNT - lastYearMonth;
    }
}
