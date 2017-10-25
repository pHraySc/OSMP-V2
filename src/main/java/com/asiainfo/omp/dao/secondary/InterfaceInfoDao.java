package com.asiainfo.omp.dao.secondary;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.InterfaceInfo;

public interface InterfaceInfoDao {
	/**
	 * 接口列表查询
	 * @return
	 */
	public List<InterfaceInfo> queryInterfaceInfoList(Map<String,Object> map);
	
	/**
	 * 查询接口数量
	 * @return
	 */
	public Map<String, Object> queryInterfacemNum(Map<String, Object> map);
	
	/**
	 * 查询所有接口数量
	 * @return
	 */
	public Map<String,Object> queryAllInterFaceNum();
}
