package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.KnowledgeDao;
import com.asiainfo.omp.model.FaultFLow;
import com.asiainfo.omp.service.KnowledgeService;
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

	@Autowired
	KnowledgeDao dao;
	
	@Override
	public List<FaultFLow> queryKnowledgeInfoList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.queryKnowledgeInfoList(map);
	}

	@Override
	public List<Map<String, Object>> queryAttachByFaultId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.queryAttachByFaultId(map);
	}

	@Override
	public void saveFaultInfo(FaultFLow fault) {
		 dao.saveFaultInfo(fault);
		
	}

	@Override
	public void updateFaultInfo(FaultFLow fault) {
		dao.updateFaultInfo(fault);
		
	}

	@Override
	public void deleteFaultInfo(int faultId) {
		dao.deleteFaultInfo(faultId);
	}

}
