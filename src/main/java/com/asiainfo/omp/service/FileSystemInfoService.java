package com.asiainfo.omp.service;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.FileSystemInfo;

public interface FileSystemInfoService {

	/**
	 * 文件系统监控列表查询
	 * @param map 
	 * @return
	 */
	public List<FileSystemInfo> queryFileSystemInfoList(Map<String, Object> map);
	/**
	 * 查询数量
	 * @return
	 */
	public Map<String,Object> queryFileSystemNum(Map<String, Object> map);
	/**
	 * 查询所有文件数量
	 * @return
	 */
	public Map<String,Object> queryAllFileNum();
	
	
}
