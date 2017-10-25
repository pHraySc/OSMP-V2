package com.asiainfo.omp.service;

import java.util.List;
import java.util.Map;

public interface CommonUtilService {

	/**
	 * 查询模块加载类型列表（库）
	 * @return
	 */
	public List<Map<String,Object>> queryTypeByModuleId(Map<String,Object> map);
}
