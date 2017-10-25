package com.asiainfo.omp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.omp.model.InterfaceInfo;
import com.asiainfo.omp.service.InterfaceInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/interfaceInfoController")
public class InterfaceInfoController {
	
	@Autowired
	InterfaceInfoService interfaceInfoService;
	
	private static Logger logger = Logger.getLogger(InterfaceInfoController.class);
	
	/**
	 * 分页查询列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
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
		String interName = "";	//接口名称
		String dataSouceId = "";//数据库Id
		String interType = "";//0全部，1日接口，2月接口
		String isImport = "";//是否重要接口
		String status = "";//状态
		if (null != request.getParameter("interName")) {
			interName = request.getParameter("interName").trim();
			
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("interType")) {
			interType = request.getParameter("interType").trim();
		}
		if (null != request.getParameter("isImport")) {
			isImport = request.getParameter("isImport").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status");
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("interName", interName);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		map.put("dataSource", dataSouceId);
		map.put("interType", interType);
		map.put("isImport", isImport);
		map.put("status", status);
		List<InterfaceInfo> tableList = interfaceInfoService.queryInterfaceInfoList(map);
		if (null != tableList) {
			om.writeValue(response.getWriter(), tableList);
		}
		
	}
	
	/**
	 * 接口状态数量查询
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryIntermNum")
	public void queryOdsInterNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String interName = "";	//表空间名称
		String dataSouceId = "";//数据库Id
		String interType = "";//0全部，1日接口，2月接口
		String isImport = "";//是否重要接口
		String status = "";//状态
		if (null != request.getParameter("interName")) {
			interName = request.getParameter("interName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("interType")) {
			interType = request.getParameter("interType").trim();
		}
		if (null != request.getParameter("isImport")) {
			isImport = request.getParameter("isImport").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status");
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("interName", interName);
		map.put("dataSource", dataSouceId);
		map.put("interType", interType);
		map.put("isImport", isImport);
		map.put("status", status);
		Map<String,Object> interMap = interfaceInfoService.querInterfaceNum(map);
		om.writeValue(response.getWriter(), interMap);
	}
}
