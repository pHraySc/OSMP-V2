package com.asiainfo.alarm.service.impl;

import com.asiainfo.alarm.dao.bass.IAlarmDao;
import com.asiainfo.alarm.dao.coc.ICocAlarmDao;
import com.asiainfo.alarm.model.CocLabel;
import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.CocSourceTableExt;
import com.asiainfo.alarm.model.Page;
import com.asiainfo.alarm.service.IAlarmService;
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
     * 获取标签信息总记录数
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @return
     */
    @Override
    public int queryLabelNum(String labelName, int dataCycle) {
        return cocAlarmDao.queryLabelNum(labelName, dataCycle);
    }

    /**
     * 获取标签信息
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @param page          分页信息
     * @return
     */
    @Override
    public List<CocLabel> queryLabelInfo(int dataCycle, String labelName, Page page, String opTime, String dataDate) {
        return cocAlarmDao.queryLabelInfo(dataCycle, labelName, page, opTime, dataDate);
    }

    /**
     * 查询标签波动情况-计算具体数量
     *
     * @param cocLabel
     * @return 波动具体数量
     */
    @Override
    public long cusNumWaved(CocLabel cocLabel) {
        return 0;
    }

    /**
     *  查询标签波动-计算环比值
     *
     * @param cocLabel
     * @return 波动百分比
     */
    @Override
    public float calculateMoM(CocLabel cocLabel) {
        return 0;
    }
}
