package com.asiainfo.alarm.dao.bass;

import com.asiainfo.alarm.model.CocSourceTableExt;

public interface IAlarmDao {
    /**
     * 获取源表扩展信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表扩展信息
     */
    CocSourceTableExt getSourceTableExtInfo(String sourceTableCode);
}
