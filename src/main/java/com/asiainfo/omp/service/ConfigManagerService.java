package com.asiainfo.omp.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.DispatcConfig;
import com.asiainfo.omp.model.FileConfig;
import com.asiainfo.omp.model.InterFaceConfig;
import com.asiainfo.omp.model.TableSpaceConfig;

public interface ConfigManagerService {
	public void saveFileConfigInfo(FileConfig fileConfig);

	public void updateFileConfigInfo(FileConfig fileConfig);
	
	public void saveDispatcConfigInfo(DispatcConfig dispatcConfig);
	
	public void updateDispatcConfigInfo(DispatcConfig dispatcConfig);
	
	public void saveInterfaceConfigInfo(InterFaceConfig interFaceConfig);
	
	public void updateInterfaceConfigInfo(InterFaceConfig interFaceConfig);
	
	public void saveTableSpaceConfigInfo(TableSpaceConfig tableSpaceConfig);
	
	public void updateTableSpaceConfigInfo(TableSpaceConfig tableSpaceConfig);
	
	public void deleteFileConfigInfo(int fileIp);
	
	public void deleteDispatcConfigInfo(int dispatcId);
	
	public void deleteInterfaceConfigInfo(int interfaceId);
	
	public void deleteTableSpaceConfigInfo(int tableSpaceId);
	
	public List<FileConfig> queryFileConfigList(Map<String,Object> map); 
	
	public List<TableSpaceConfig> queryTableSpaceConfigList(Map<String,Object> map); 
	
	public List<DispatcConfig> queryDispatcConfigList(Map<String,Object> map); 
	
	public List<InterFaceConfig> queryInterFaceConfigList(Map<String,Object> map); 
	
	public List<FileConfig> checkFileExit(Map<String,Object> map);
	
	public List<TableSpaceConfig> checkTableSpaceExit(Map<String,Object> map);
	
	public List<DispatcConfig> checkDispatcExit(Map<String,Object> map);
	
	public List<InterFaceConfig> checkInterFaceExit(Map<String,Object> map);
}
