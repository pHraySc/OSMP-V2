package com.asiainfo.alarm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    private static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getMonthDataDate(int delayValue) {
        LocalDate dateTime = LocalDate.now();
        dateTime = dateTime.minusMonths(delayValue);
        return dateTime.format(monthFormatter);
    }

    public static String getDayDataDate(int delayValue) {
        LocalDate dateTime = LocalDate.now();
        dateTime = dateTime.minusDays(delayValue);
        return dateTime.format(dayFormatter);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getMonthDataDate(1));
        System.out.println(DateUtil.getDayDataDate(1));
    }
}
