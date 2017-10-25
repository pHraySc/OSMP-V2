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

import com.asiainfo.omp.model.TableSpaceInfo;
import com.asiainfo.omp.service.TableSpaceInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/tableSapceController")
public class TableSpaceInfoController {
	
	@Autowired
	TableSpaceInfoService tableSpaceService;
	
	private static Logger logger = Logger.getLogger(TableSpaceInfoController.class);
	
	@RequestMapping(value="/queryList")
	public void queryTableSpaceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页10条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String tableSpaceName = "";	//表空间名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		if (null != request.getParameter("tableSpaceName")) {
			tableSpaceName = request.getParameter("tableSpaceName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("tableSpaceName", tableSpaceName);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		try {
			List<TableSpaceInfo> tableList = tableSpaceService.queryTableSpaceInfoList(map);
			if (null != tableList) {
				om.writeValue(response.getWriter(), tableList);
			}
		} catch (Exception e) {
			logger.info("获取表空间列表出错"+e.getMessage());
		}
		
	}
	
	@RequestMapping(value="/queryOdsInterNum")
	public void queryOdsInterNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableSpaceName = "";	//表空间名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		if (null != request.getParameter("tableSpaceName")) {
			tableSpaceName = request.getParameter("tableSpaceName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("tableSpaceName", tableSpaceName);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		try {
		    Map<String,Object> tableMap = tableSpaceService.queryTableSpaceNum(map);//查询数量
			om.writeValue(response.getWriter(), tableMap);
		} catch (Exception e) {
			logger.info("获取表空间数量出错"+e.getMessage());
		}
		
	}
	
	public void queryDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = 0;
		ObjectMapper om = new ObjectMapper();
		try {
			if (null != request.getParameter("id")) {
				id = Integer.valueOf(request.getParameter("id"));
				TableSpaceInfo info = tableSpaceService.getTableSpaceInfoById(id);
				if (info != null) {
					om.writeValue(response.getWriter(), info);
				}
			}
		} catch (Exception e) {
			logger.info("获取表空间信息出错"+e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/queryInfoList")
	public void queryAllByTableName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tableSpaceName = "";	//表空间名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		if (null != request.getParameter("tableSpaceName")) {
			tableSpaceName = request.getParameter("tableSpaceName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("tableSpaceName", tableSpaceName);
		map.put("dataSource", dataSouceId);
		try {
			List<TableSpaceInfo> tableList = tableSpaceService.queryAllByTableName(map);//查询全部表空间
			if (null != tableList) {
				om.writeValue(response.getWriter(), tableList);
			}
		} catch (Exception e) {
			logger.info("获取表空间图标信息出错"+e.getMessage());
		}
		
	}
	

}
