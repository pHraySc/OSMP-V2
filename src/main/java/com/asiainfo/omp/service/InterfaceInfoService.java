package com.asiainfo.omp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.asiainfo.omp.model.InterfaceInfo;

public interface InterfaceInfoService {

	/**
	 * 接口列表查询
	 * @return
	 */
	public List<InterfaceInfo> queryInterfaceInfoList(Map<String,Object> map);
	
	/**
	 * 查询数量
	 * @return
	 */
	public Map<String,Object> querInterfaceNum(Map<String, Object> map); 
	
	/**
	 * 查询所有接口数量
	 * @return
	 */
	public Map<String,Object> queryAllInterFaceNum();
}
