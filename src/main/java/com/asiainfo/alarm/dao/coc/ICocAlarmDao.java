package com.asiainfo.alarm.dao.coc;

import com.asiainfo.alarm.model.CocLabel;
import com.asiainfo.alarm.model.CocSourceTable;
import com.asiainfo.alarm.model.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 获取标签信息总记录数
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @return
     */
    int queryLabelNum(@Param("labelName") String labelName,@Param("dataCycle") int dataCycle);

    /**
     * 获取标签信息
     *
     * @param labelName     标签名
     * @param dataCycle     数据周期：0，全部；1，日；2，月
     * @param page          分页信息
     * @param opTime        数据表账期
     * @param dataDate      数据日期
     * @return
     */
    List<CocLabel> queryLabelInfo(@Param("dataCycle") int dataCycle,@Param("labelName") String labelName,
                                  @Param("page") Page page,@Param("opTime") String opTime, @Param("dataDate") String dataDate);
}
