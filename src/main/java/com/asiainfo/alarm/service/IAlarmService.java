package com.asiainfo.alarm.service;

import com.asiainfo.alarm.model.CocLabel;
import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.CocSourceTableExt;
import com.asiainfo.alarm.model.Page;

import java.util.List;

public interface IAlarmService {
    /**
     * 获取源表信息总记录数
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @return 源表信息总记录数
     */
    int getSourceTableInfoCount(int dataCycle, String sourceTableName);

    /**
     * 获取源表信息
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @param page            分页信息
     * @return 源表信息列表
     */
    List<CocSourceTable> getSourceTableInfo(int dataCycle, String sourceTableName, Page page);

    /**
     * 获取源表扩展信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表扩展信息
     */
    CocSourceTableExt getSourceTableExtInfo(String sourceTableCode);

    /**
     * 获取标签信息总记录数
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @return
     */
    int queryLabelNum(String labelName, int dataCycle);

    /**
     * 获取标签信息
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @param page          分页信息
     * @return
     */
    List<CocLabel> queryLabelInfo(int dataCycle, String labelName, Page page, String opTime, String dataDate);
}
