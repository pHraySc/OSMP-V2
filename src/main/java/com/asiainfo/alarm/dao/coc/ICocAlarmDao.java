package com.asiainfo.alarm.dao.coc;

import com.asiainfo.alarm.model.CocLabel;
import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
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
     * 根据源表编码获取源表信息
     *
     * @param sourceTableCode 源表编码
     * @return 源表信息
     */
    CocSourceTable getSourceTableInfoByCode(String sourceTableCode);

    /**
     * 获取标签信息总记录数
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @return
     */
    int queryLabelNum(@Param("labelName") String labelName, @Param("dataCycle") int dataCycle);

    /**
     * 获取标签信息
     *
     * @param labelName 标签名
     * @param dataCycle 数据周期：0，全部；1，日；2，月
     * @param page      分页信息
     * @param dOpTime   日数据表账期
     * @param dDataDate 日数据日期
     * @param mOpTime   月数据表账期
     * @param mDataDate 月数据日期
     * @return
     */
    List<CocLabel> queryLabelInfo(@Param("dataCycle") int dataCycle, @Param("labelName") String labelName,
                                  @Param("page") Page page, @Param("dOpTime") String dOpTime, @Param("dDataDate") String dDataDate,
                                  @Param("mOpTime") String mOpTime, @Param("mDataDate") String mDataDate);

    /**
     * 获取前一天的用户数与当天的做比较-具体数量的比较
     *
     * @param labelId
     * @return 前一天的该标签Id的用户数
     */
    long queryPreCusNum(@Param("labelId") long labelId, @Param("dataCycle") int dataCycle, @Param("opTime") String opTime, @Param("dataDate") String dataDate);

    /**
     * 查询ci_label_stat_dm_${opTime}中是否有要查询数据日期的标签
     *
     * @param labelId
     * @param opTime
     * @param dataDate
     * @return true/false
     */
    boolean doPreCusNumExist(@Param("labelId") long labelId, @Param("dataCycle") int dataCycle, @Param("opTime") String opTime, @Param("dataDate") String dataDate);

    /**
     * 获取前一天的用户数与当天的做比较-具体数量的比较
     *
     * @param labelId
     * @return opTime相对于dataDate的环比值-具体数值
     */
    long queryRingNum(@Param("labelId") long labelId, @Param("dataCycle") int dataCycle, @Param("opTime") String opTime, @Param("dataDate") String dataDate);

    /**
     * 判断表是否存在
     *
     * @param tabSchema tableSchema
     * @param tabName   tableName
     * @return
     */
    boolean doesTableExist(@Param("tabSchema") String tabSchema, @Param("tabName") String tabName);

    /**
     * 根据labelId查询标签详情
     *
     * @param labelId
     * @return
     */
    Map queryLabelDetailInfoByLabelId(@Param("labelId") long labelId);

    void updateLabelInfo(@Param("labelId") long labelId, @Param("labelCaliber") String labelCaliber);

    boolean doesLabelIdExist(@Param("labelId") long labelId);
}
