package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.ConfigManagerDao;
import com.asiainfo.omp.model.DispatcConfig;
import com.asiainfo.omp.model.FileConfig;
import com.asiainfo.omp.model.InterFaceConfig;
import com.asiainfo.omp.model.TableSpaceConfig;
import com.asiainfo.omp.service.ConfigManagerService;
@Service
public class ConfigManagerServiceImpl implements ConfigManagerService {

	@Autowired
	ConfigManagerDao dao;

	@Override
	public void updateFileConfigInfo(FileConfig fileConfig) {
		dao.updateFileConfigInfo(fileConfig);
	}

	@Override
	public void saveDispatcConfigInfo(DispatcConfig dispatcConfig) {
		dao.saveDispatcConfigInfo(dispatcConfig);
	}

	@Override
	public void updateDispatcConfigInfo(DispatcConfig dispatcConfig) {
		dao.updateDispatcConfigInfo(dispatcConfig);
	}

	@Override
	public void saveInterfaceConfigInfo(InterFaceConfig interFaceConfig) {
		dao.saveInterfaceConfigInfo(interFaceConfig);
	}

	@Override
	public void updateInterfaceConfigInfo(InterFaceConfig interFaceConfig) {
		dao.updateInterfaceConfigInfo(interFaceConfig);
	}

	@Override
	public void saveTableSpaceConfigInfo(TableSpaceConfig tableSpaceConfig) {
		dao.saveTableSpaceConfigInfo(tableSpaceConfig);
	}

	@Override
	public void updateTableSpaceConfigInfo(TableSpaceConfig tableSpaceConfig) {
		dao.updateTableSpaceConfigInfo(tableSpaceConfig);
	}

	@Override
	public void deleteFileConfigInfo(int fileIp) {
		dao.deleteFileConfigInfo(fileIp);
	}

	@Override
	public void deleteDispatcConfigInfo(int dispatcId) {
		dao.deleteDispatcConfigInfo(dispatcId);
	}

	@Override
	public void deleteInterfaceConfigInfo(int interfaceId) {
		dao.deleteInterfaceConfigInfo(interfaceId);
	}

	@Override
	public void deleteTableSpaceConfigInfo(int tableSpaceId) {
		dao.deleteTableSpaceConfigInfo(tableSpaceId);
	}

	@Override
	public List<FileConfig> queryFileConfigList(Map<String, Object> map) {
		return dao.queryFileConfigList(map);
	}

	@Override
	public List<TableSpaceConfig> queryTableSpaceConfigList(Map<String, Object> map) {
		return dao.queryTableSpaceConfigList(map);
	}

	@Override
	public List<DispatcConfig> queryDispatcConfigList(Map<String, Object> map) {
		return dao.queryDispatcConfigList(map);
	}

	@Override
	public List<InterFaceConfig> queryInterFaceConfigList(Map<String, Object> map) {
		return dao.queryInterFaceConfigList(map);
	}

	@Override
	public List<FileConfig> checkFileExit(Map<String, Object> map) {
		return dao.checkFileExit(map);
	}

	@Override
	public List<TableSpaceConfig> checkTableSpaceExit(Map<String, Object> map) {
		return dao.checkTableSpaceExit(map);
	}

	@Override
	public List<DispatcConfig> checkDispatcExit(Map<String, Object> map) {
		return dao.checkDispatcExit(map);
	}

	@Override
	public List<InterFaceConfig> checkInterFaceExit(Map<String, Object> map) {
		return dao.checkInterFaceExit(map);
	}

	@Override
	public void saveFileConfigInfo(FileConfig fileConfig) {
		dao.saveFileConfigInfo(fileConfig);
	}
	



}
