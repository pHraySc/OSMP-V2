package com.asiainfo.omp.dao.base;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.OsmpBaseInfo;

public interface BaseMonitorDao {
    /**
     * 定时任务获全量接口信息
     */
	//public List<OsmpBaseInfo> getOsmpBaseInfoAll();
	public List<OsmpBaseInfo> queryOsmpBaseInfo(Map<String,Object> map);
	public void insertOmspBaseInfo(OsmpBaseInfo o);
	public int updateOmspBaseInfo(OsmpBaseInfo o);
}
