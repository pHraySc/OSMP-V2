package com.asiainfo.alarm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2017/10/30.
 * 标签工具类
 */
public class labelUtil {

    private static final Logger logger = LoggerFactory.getLogger(labelUtil.class);

    /**
     * 指定波动百分比，超过指定值为波动异常
     */
    public static final float wavedPercent = 10.00f;

    /**
     * 默认schema
     */
    public static final String labelSchema = "sccoc";

    /**
     * ci_label_stat_dm/mm表还未到更新时间10:00
     */
    public static final int ciLabelStatNotArr = -99;

    /**
     * ci_label_stat_dm/mm当前周期的数据暂无
     */
    public static final int ciLabelStatNoCurOptimeData = -98;
}
