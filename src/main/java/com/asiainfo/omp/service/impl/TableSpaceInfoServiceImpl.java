package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.TableSpaceInfoDao;
import com.asiainfo.omp.model.TableSpaceInfo;
import com.asiainfo.omp.service.TableSpaceInfoService;

@Service
public class TableSpaceInfoServiceImpl implements TableSpaceInfoService {

	@Autowired
	TableSpaceInfoDao tableDao;
	
	@Override
	public List<TableSpaceInfo> queryTableSpaceInfoList(Map<String,Object> map) {
		return tableDao.queryTableSpaceInfoList(map);
	}

	@Override
	public TableSpaceInfo getTableSpaceInfoById(int id) {
		return tableDao.getTableSpaceInfoById(id);
	}


	@Override
	public Map<String, Object> queryAllTableSpaceNum() {
		return tableDao.queryAllTableSpaceNum();
	}
	
	@Override
	public Map<String, Object> queryTableSpaceNum(Map<String, Object> map) {
		return tableDao.queryTableSpaceNum(map);
	}

	@Override
	public List<TableSpaceInfo> queryAllByTableName(Map<String, Object> map) {
		return tableDao.queryAllByTableName(map);
	}

}
