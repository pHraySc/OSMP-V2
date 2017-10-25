package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.base.BaseMonitorDao;
import com.asiainfo.omp.model.OsmpBaseInfo;
import com.asiainfo.omp.service.BaseMonitorService;

@Service
public class BaseMonitorServiceImpl implements BaseMonitorService {
    @Autowired
    BaseMonitorDao bmd;
	@Override
	public List<OsmpBaseInfo> getOsmpBaseInfoAll() {
		// TODO Auto-generated method stub
		//return bmd.getOsmpBaseInfoAll();
		return null;
	}
	@Override
	public void insertOmspBaseInfo(OsmpBaseInfo o) {
		// TODO Auto-generated method stub
		bmd.insertOmspBaseInfo(o);
	}
	@Override
	public int updateOmspBaseInfo(OsmpBaseInfo o) {
		// TODO Auto-generated method stub
		return bmd.updateOmspBaseInfo(o);
	}
	@Override
	public List<OsmpBaseInfo> queryOsmpBaseInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bmd.queryOsmpBaseInfo(map);
	}

}
