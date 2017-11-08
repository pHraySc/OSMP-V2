package com.asiainfo.alarm.service.impl;

import com.asiainfo.alarm.dao.bass.IAlarmDao;
import com.asiainfo.alarm.dao.coc.ICocAlarmDao;
import com.asiainfo.alarm.model.*;
import com.asiainfo.alarm.service.IAlarmService;
import com.asiainfo.alarm.util.labelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements IAlarmService {
    @Autowired
    private IAlarmDao alarmDao;
    @Autowired
    private ICocAlarmDao cocAlarmDao;

    /**
     * 获取源表信息总记录数
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @return 源表信息总记录数
     */
    @Override
    public int getSourceTableInfoCount(int dataCycle, String sourceTableName) {
        return cocAlarmDao.getSourceTableInfoCount(dataCycle, sourceTableName);
    }

    /**
     * 获取源表信息
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @param page            分页信息
     * @return 源表信息列表
     */
    @Override
    public List<CocSourceTable> getSourceTableInfo(int dataCycle, String sourceTableName, Page page) {
        return cocAlarmDao.getSourceTableInfo(dataCycle, sourceTableName, page);
    }

    /**
     * 根据源表编码获取源表信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表信息
     */
    @Override
    public CocSourceTable getSourceTableInfoByCode(String sourceTableCode) {
        return cocAlarmDao.getSourceTableInfoByCode(sourceTableCode);
    }

    /**
     * 获取源表扩展信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表扩展信息
     */
    @Override
    public CocSourceTableExt getSourceTableExtInfo(String sourceTableCode) {
        return alarmDao.getSourceTableExtInfo(sourceTableCode);
    }

    /**
     * 更新源表扩展信息
     *
     * @param cocSourceTableExt 源表扩展信息
     */
    @Override
    public void updateSourceTableExtInfo(CocSourceTableExt cocSourceTableExt) {
        alarmDao.updateSourceTableExtInfo(cocSourceTableExt);
    }

    /**
     * 获取标签信息总记录数
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @return
     */
    @Override
    public int queryLabelNum(String labelName, int dataCycle) {
        return cocAlarmDao.queryLabelNum(labelName, dataCycle);
    }

    /**
     * 获取标签信息
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @param page      分页信息
     * @return
     */
    @Override
    public List<CocLabel> queryLabelInfo(int dataCycle, String labelName, Page page,
                                         String dOpTime, String dDataDate,
                                         String mOpTime, String mDataDate) {
        return cocAlarmDao.queryLabelInfo(dataCycle, labelName, page, dOpTime, dDataDate, mOpTime, mDataDate);
    }

    /**
     * 查询标签波动情况-计算具体数量
     * 有以下几种情况：
     * 1、2天和3天前的数据都不存在，无法计算环比，返回-1，数据延迟，外层方法可以判断
     * 2、2天前数据不存在，3天前存在，该情况下，有可能是2天前数据还未跑出，有可能是数据出现问题。==>可以直接计算，会归到波动异常中
     * 3、2天前数据存在，3天前数据不存在，3天前数据出现问题，无法计算环比
     *
     * @param cocLabel
     * @return 波动具体数量
     */
    @Override
    public long cusNumWaved(CocLabel cocLabel, String opTime, String dataDate) {
        return cocAlarmDao.queryRingNum(cocLabel.getLabelId(), cocLabel.getDataCycle(), opTime, dataDate);
    }

    /**
     * 查询ci_label_stat_dm_${opTime}中是否有要查询数据日期的标签
     *
     * @param cocLabel
     * @param opTime
     * @param dataDate
     * @return true/false
     */
    @Override
    public boolean doPreCusNumExist(CocLabel cocLabel, String opTime, String dataDate) {

        if (this.doPreCusNumTabExist(cocLabel.getDataCycle(), opTime)) {

            if (cocAlarmDao.doPreCusNumExist(cocLabel.getLabelId(), cocLabel.getDataCycle(), opTime, dataDate))

                return Boolean.TRUE;

            else return Boolean.FALSE;

        } else {

            return Boolean.FALSE;
        }
    }

    /**
     * 判断 ci_label_stat_dm_${opTime} 是否存在
     *
     * @param opTime
     * @return
     */
    @Override
    public boolean doPreCusNumTabExist(int dataCycle, String opTime) {

        StringBuffer tabN = new StringBuffer();
        String tabName;

        if (dataCycle == 1) {

            tabName = tabN.append("ci_label_stat_dm_").append(opTime).toString();
        } else {

            tabName = tabN.append("ci_label_stat_mm_").append(opTime).toString();
        }

        return cocAlarmDao.doesTableExist(labelUtil.labelSchema, tabName);
    }

    /**
     * 查询标签波动-计算环比值
     *
     * @param cocLabel
     * @return 波动百分比
     */
    @Override
    public float calculateMoM(CocLabel cocLabel, CocLabelExt cocLabelExt, String opTime, String dataDate) {
        float moM;
        long previousNum;
        previousNum = cocAlarmDao.queryPreCusNum(cocLabel.getLabelId(), cocLabel.getDataCycle(), opTime, dataDate);
        long ringNum = Math.abs(cocLabelExt.getWavedCustomNum());
        if (previousNum != 0) {
            moM = (ringNum * 1.0f) / previousNum * 100;
            return moM;
        } else {
            return -1.00f;
        }
    }


}
