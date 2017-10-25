package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.CommonUtilDao;
import com.asiainfo.omp.service.CommonUtilService;

@Service
public class CommonUtilServiceImpl implements CommonUtilService {
	
	@Autowired
	CommonUtilDao dao;

	@Override
	public List<Map<String, Object>> queryTypeByModuleId(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.queryTypeByModuleId(map);
	}

}
