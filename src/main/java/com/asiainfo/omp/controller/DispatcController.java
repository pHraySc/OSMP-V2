package com.asiainfo.omp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.omp.model.Dispatc;
import com.asiainfo.omp.service.DispatcService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/dispatcController")
public class DispatcController {

	@Autowired
	private DispatcService service;
	
	/**
	 * 分页查询列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryList")
	private void queryDispatcList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String taskName = "";	//调度任务名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		String dispatcType ="";//调度类型
		String isDay = "";//是否日月
		if (null != request.getParameter("dispatcType")) {
			dispatcType = request.getParameter("dispatcType").trim();
			
		}
		if (null != request.getParameter("isDay")) {
			isDay = request.getParameter("isDay").trim();
			
		}
		if (null != request.getParameter("dispatcName")) {
			taskName = request.getParameter("dispatcName").trim();
			
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status");
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("taskName", taskName);
		map.put("dispatcType", dispatcType);
		map.put("isDay", isDay);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		List<Dispatc> tableList = service.queryDispatcInfoList(map);
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
	@RequestMapping(value="/queryDisptacmNum")
	public void queryOdsInterNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String taskName = "";	//调度任务名称
		String dataSouceId = "";//数据库Id
		String status = "";//状态
		String dispatcType ="";//调度类型
		String isDay = "";//是否日月
		if (null != request.getParameter("dispatcType")) {
			dispatcType = request.getParameter("dispatcType").trim();
			
		}
		if (null != request.getParameter("isDay")) {
			isDay = request.getParameter("isDay").trim();
			
		}
		if (null != request.getParameter("dispatcName")) {
			taskName = request.getParameter("dispatcName").trim();
		}
		if (null != request.getParameter("dataSouceId")) {
			dataSouceId = request.getParameter("dataSouceId").trim();
		}
		if (null != request.getParameter("status")) {
			status = request.getParameter("status");
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("taskName", taskName);
		map.put("dataSource", dataSouceId);
		map.put("status", status);
		map.put("dispatcType", dispatcType);
		map.put("isDay", isDay);
		Map<String,Object> interMap = service.queryDispatcNum(map);
		om.writeValue(response.getWriter(), interMap);
	}
}
