package com.asiainfo.alarm.model;

/**
 * 源表数据状态
 */
public class TableDataStatus {
    /**
     * 未知——未录入扩展信息，无法判断
     */
    public static final int UNKNOWN = 0;
    /**
     * 正常——数据正常更新
     */
    public static final int NORMAL = 1;
    /**
     * 延迟——数据未在规定时间前更新
     */
    public static final int DELAY = 2;
}
