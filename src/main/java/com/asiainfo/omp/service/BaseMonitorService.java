package com.asiainfo.omp.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.OsmpBaseInfo;

public interface BaseMonitorService {

	public List<OsmpBaseInfo> getOsmpBaseInfoAll();
	public void insertOmspBaseInfo(OsmpBaseInfo o);
	public int updateOmspBaseInfo(OsmpBaseInfo o);
	public List<OsmpBaseInfo> queryOsmpBaseInfo(Map<String,Object> map);
}
