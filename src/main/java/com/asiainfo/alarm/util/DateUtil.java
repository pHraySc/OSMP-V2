package com.asiainfo.alarm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String twoDaysAgo = DateUtil.getDayDataDate(2);     //两天前

    public static final String threeDaysAgo = DateUtil.getDayDataDate(3);   //三天前


    public static final String fourDaysAgo = DateUtil.getDayDataDate(4);   //三天前


    public static final String oneMonthAgo = DateUtil.getMonthDataDate(1);  //一个月前

    public static final String twoMonthAgo = DateUtil.getMonthDataDate(2);  //两个月前

    /**
     * delayValue 时效性参数：1、两天    2、一个月
     */

    public static final int delayValDay = 2;

    public static final int delayValMonth = 1;

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

    public static boolean isDelay(String dataDate, int delayValDay) {
        LocalDate localDate = LocalDate.parse(LocalDate.now().minusDays(delayValDay + 1).format(dayFormatter), dayFormatter);
        LocalDate labelDataDate = LocalDate.parse(dataDate, dayFormatter);
        if (localDate.isAfter(labelDataDate)) {
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getMonthDataDate(1));
        System.out.println(DateUtil.getDayDataDate(1));
        System.out.println(DateUtil.twoDaysAgo + DateUtil.threeDaysAgo);
        System.out.println(isDelay("20171029", 2));
    }
}
