package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.DispatcDao;
import com.asiainfo.omp.model.Dispatc;
import com.asiainfo.omp.service.DispatcService;
@Service
public class DispatcServiceImpl implements DispatcService {

	@Autowired
	DispatcDao dao;
	
	@Override
	public List<Dispatc> queryDispatcInfoList(Map<String, Object> map) {
		return dao.queryDispatcInfoList(map);
	}

	@Override
	public Map<String, Object> queryDispatcNum(Map<String, Object> map) {
		return dao.queryDispatcNum(map);
	}

	@Override
	public Map<String, Object> queryAllDispatcNum() {
		return dao.queryAllDispatcNum();
	}

}
