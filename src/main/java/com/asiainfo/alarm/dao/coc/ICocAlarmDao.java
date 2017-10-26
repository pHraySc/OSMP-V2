package com.asiainfo.alarm.dao.coc;

import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICocAlarmDao {
    /**
     * 获取源表信息总记录数
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @return 源表信息总记录数
     */
    int getSourceTableInfoCount(@Param("dataCycle") int dataCycle, @Param("sourceTableName") String sourceTableName);

    /**
     * 获取源表信息
     *
     * @param dataCycle       数据周期：0，全部；1，日；2，月
     * @param sourceTableName 源表表名
     * @param page            分页信息
     * @return 源表信息列表
     */
    List<CocSourceTable> getSourceTableInfo(@Param("dataCycle") int dataCycle, @Param("sourceTableName") String sourceTableName, @Param("page") Page page);

    /**
     * @param sourceTMap 根据周期从指标层配置表中直接取出源表名
     */
    List querySourceTabNameByDataCyle(Map<String, Object> sourceTMap);

    int querySourceTabNum(Map<String, Object> sourceTMap);

    int queryLabelNum(Map<String, Object> labelMap);

    List queryLabelInfo(Map<String, Object> labelMap);
}
