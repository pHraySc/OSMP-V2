package com.asiainfo.omp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.omp.dao.secondary.FileSystemInfoDao;
import com.asiainfo.omp.model.FileSystemInfo;
import com.asiainfo.omp.service.FileSystemInfoService;

@Service
public class FileSystemInfoServiceImpl implements FileSystemInfoService {

	@Autowired
	FileSystemInfoDao fileDao;
	
	@Override
	public List<FileSystemInfo> queryFileSystemInfoList(Map<String, Object> map) {
		return fileDao.queryFileSystemInfoList(map);
	}

	@Override
	public Map<String, Object> queryFileSystemNum(Map<String, Object> map) {
		return fileDao.queryFileSystemNum(map);
	}

	@Override
	public Map<String, Object> queryAllFileNum() {
		return fileDao.queryAllFileNum();
	}


}
