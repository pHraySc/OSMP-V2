package com.asiainfo.omp.dao.secondary;

import java.util.List;
import java.util.Map;

import com.asiainfo.omp.model.FileSystemInfo;

public interface FileSystemInfoDao {

	/**
	 * 文件系统监控列表查询
	 * @return
	 */
	public List<FileSystemInfo> queryFileSystemInfoList(Map<String, Object> map);
	/**
	 * 查询文件系统数量
	 * @return
	 */
	public Map<String, Object> queryFileSystemNum(Map<String, Object> map);
	
	/**
	 * 查询所有文件数量
	 * @return
	 */
	public Map<String,Object> queryAllFileNum();
	
}
