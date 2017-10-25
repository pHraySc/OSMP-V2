package com.asiainfo.omp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.omp.model.FileSystemInfo;
import com.asiainfo.omp.service.FileSystemInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/fileSystemController")
public class FileSystemInfoController {

	@Autowired
	FileSystemInfoService fileSystemService;
	
	@RequestMapping(value="/queryList")
	public void queryTableSpaceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String fileSystemName = "";	//文件系统名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		if (null != request.getParameter("fileSystemName")) {
			fileSystemName = request.getParameter("fileSystemName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("fileSystemName", fileSystemName);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		List<FileSystemInfo> fileList = fileSystemService.queryFileSystemInfoList(map);
		if (null != fileList) {
			om.writeValue(response.getWriter(), fileList);
		}
	}
	
	
	@RequestMapping(value="/queryFileSystemNum")
	public void queryOdsInterNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileSystemName = "";	//文件系统名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		if (null != request.getParameter("fileSystemName")) {
			fileSystemName = request.getParameter("fileSystemName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("fileSystemName", fileSystemName);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		Map<String,Object> fileMap = fileSystemService.queryFileSystemNum(map);
	    om.writeValue(response.getWriter(), fileMap);
	}
}
