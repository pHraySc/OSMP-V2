package com.asiainfo.alarm.service;

import com.asiainfo.alarm.model.*;

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
     * 根据源表编码获取源表信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表信息
     */
    CocSourceTable getSourceTableInfoByCode(String sourceTableCode);

    /**
     * 获取源表扩展信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表扩展信息
     */
    CocSourceTableExt getSourceTableExtInfo(String sourceTableCode);

    /**
     * 更新源表扩展信息
     *
     * @param cocSourceTableExt 源表扩展信息
     */
    void updateSourceTableExtInfo(CocSourceTableExt cocSourceTableExt);

    /**
     * 获取标签信息总记录数
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @return
     */
    int queryLabelNum(String labelName, int dataCycle);

    /**
     * 获取标签信息
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @param page      分页信息
     * @return
     */
    List<CocLabel> queryLabelInfo(int dataCycle, String labelName, Page page, String dOpTime, String dDataDate, String mOpTime, String mDataDate);

    /**
     * 查询标签波动情况-计算具体数量
     *
     * @param cocLabel
     * @return 波动具体数量
     */
    long cusNumWaved(CocLabel cocLabel, String opTime, String dataDate);

    /**
     * 查询标签波动-计算环比值
     *
     * @param cocLabel
     * @return 波动百分比
     */
    float calculateMoM(CocLabel cocLabel, CocLabelExt cocLabelExt, String opTime, String dataDate);

    /**
     * 查询ci_label_stat_dm_${opTime}中是否有要查询数据日期的标签
     *
     * @param cocLabel
     * @param opTime
     * @param dataDate
     * @return true/false
     */
    int doPreCusNumExist(CocLabel cocLabel, String opTime, String dataDate);

    /**
     * 判断 ci_label_stat_dm_${opTime} 是否存在
     *
     * @param opTime
     * @return
     */
    boolean doPreCusNumTabExist(int dataCycle, String opTime);

    /**
     * 根据labelId查询标签详情
     *
     * @param labelId
     * @return
     */
    Map queryLabelDetailInfoByLabelId(long labelId);

    int updateLabelInfo(long labelId, String labelCaliber);
}
