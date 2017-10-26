package com.asiainfo.alarm.service;

import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.CocSourceTableExt;
import com.asiainfo.alarm.model.Page;

import java.util.List;
import java.util.Map;

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

    List querySourceTabNameByDataCyle(Map<String, Object> sourceTMap);

    int querySourceTabNum(Map<String, Object> sourceTMap);

    int queryLabelNum(Map<String, Object> labelMap);

    List queryLabelInfo(Map<String, Object> labelMap);
}
