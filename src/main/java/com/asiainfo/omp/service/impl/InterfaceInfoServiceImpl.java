package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.InterfaceInfoDao;
import com.asiainfo.omp.model.InterfaceInfo;
import com.asiainfo.omp.service.InterfaceInfoService;
@Service
public class InterfaceInfoServiceImpl implements InterfaceInfoService{
	
	@Autowired
	InterfaceInfoDao interfaceDao;

	@Override
	public List<InterfaceInfo> queryInterfaceInfoList(Map<String, Object> map) {
		return interfaceDao.queryInterfaceInfoList(map);
	}

	@Override
	public Map<String, Object> querInterfaceNum(Map<String, Object> map) {
		return interfaceDao.queryInterfacemNum(map);
	}

	@Override
	public Map<String, Object> queryAllInterFaceNum() {
		return interfaceDao.queryAllInterFaceNum();
	}

}
