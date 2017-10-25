package com.asiainfo.alarm.service.impl;

import com.asiainfo.alarm.dao.bass.IAlarmDao;
import com.asiainfo.alarm.dao.coc.ICocAlarmDao;
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
}
