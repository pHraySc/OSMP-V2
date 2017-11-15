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

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final LocalTime nowTime = LocalTime.now();

    public static final LocalDate nowDate = LocalDate.now();

    public static final LocalTime tclShouldTime = LocalTime.of(10,0,0);

    public static final LocalDate tclShouldDate = LocalDate.of(nowDate.getYear(),nowDate.getMonth(),3);

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
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusMonths(delayValue);
        return localDate.format(monthFormatter);
    }

    public static String getDayDataDate(int delayValue) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(delayValue);
        return localDate.format(dayFormatter);
    }

    public static boolean isAfterUpdateTime(int dataCycle, String updateTime) {
        boolean isAfter = Boolean.FALSE;
        if (dataCycle == 1) {
            LocalTime localTime = LocalTime.parse(updateTime, timeFormatter);
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);
            if (localDateTime.isAfter(LocalDateTime.now())) {
                isAfter = Boolean.TRUE;
            }
        } else {
            LocalDate now = LocalDate.now();
            LocalDate localDate = LocalDate.of(now.getYear(), now.getMonth(), Integer.valueOf(updateTime));
            if (localDate.isAfter(now)) {
                isAfter = Boolean.TRUE;
            }
        }
        return isAfter;
    }

    public static boolean isDelay(int dataCycle, String dataDate, int delayValDay) {
        if (dataCycle == 1) {
            LocalDate localDate = LocalDate.now().minusDays(delayValDay + 1);
            LocalDate labelDataDate = LocalDate.parse(dataDate, dayFormatter);
            if (localDate.isAfter(labelDataDate)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            LocalDate localDate = LocalDate.now().minusMonths(delayValDay + 1);
            LocalDate labelDataDate = LocalDate.parse(dataDate + "01", dayFormatter);
            if (localDate.isAfter(labelDataDate)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getMonthDataDate(1));
        System.out.println(DateUtil.getDayDataDate(1));
        System.out.println(DateUtil.twoDaysAgo + DateUtil.threeDaysAgo);
        System.out.println(isDelay(2, "201710", 1));
        System.out.println(nowTime.isAfter(tclShouldTime));
        System.out.println(LocalDate.parse(DateUtil.nowDate.format(DateUtil.monthFormatter) + "01", DateUtil.dayFormatter));
    }
}
