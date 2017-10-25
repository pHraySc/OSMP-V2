package com.asiainfo.omp.dao.secondary;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.Dispatc;

public interface DispatcDao {

	/**
	 * 调度列表查询
	 * @return
	 */
	public List<Dispatc> queryDispatcInfoList(Map<String,Object> map);
	
	/**
	 * 查询数量
	 * @return
	 */
	public Map<String,Object> queryDispatcNum(Map<String, Object> map); 
	/**
	 * 查询所有调度数量
	 * @return
	 */
	public Map<String,Object> queryAllDispatcNum();
	
}
