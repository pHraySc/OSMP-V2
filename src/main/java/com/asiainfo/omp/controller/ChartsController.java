package com.asiainfo.omp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiainfo.omp.service.DispatcService;
import com.asiainfo.omp.service.FileSystemInfoService;
import com.asiainfo.omp.service.InterfaceInfoService;
import com.asiainfo.omp.service.TableSpaceInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/chartsController")
public class ChartsController {

	@Autowired
	FileSystemInfoService fileService;
	@Autowired
	DispatcService dispatcService;
	@Autowired
	InterfaceInfoService interfaceService;
	@Autowired
	TableSpaceInfoService tableSpaceService;
	
	/**
	 * 文件系统数量
	 */
	@RequestMapping(value="/queryFileNum")
	public void queryFileNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ObjectMapper om = new ObjectMapper();
		JSONArray arry = new JSONArray();
		List<String> legend = new ArrayList<String>();
		JSONObject json = new JSONObject();
		Map<String,Object> fileMap = fileService.queryAllFileNum();
		for(String name : fileMap.keySet()){
			if (name.equals("totalnum")) {
				name = "总量" + " "+ fileMap.get(name);
			}else if (name.equals("normalNum")) {
				name = "正常" + " "+ fileMap.get(name);
			}else if (name.equals("expNum")) {
				name = "异常" + " "+ fileMap.get(name);
			}else if (name.equals("unknow")) {
				name = "未知" + " "+ fileMap.get(name);
			}
			legend.add(name);
		}
		json.put("legend", legend);
		json.put("series", fileMap);
		//json.put("series", JSONObject.fromObject(fileMap));
		//arry.put(json);
		om.writeValue(response.getWriter(), json);
		//response.getWriter().write(arry.toString());
	}
	
	/**
	 * 调度数量
	 */
	@RequestMapping(value="/queryDispatcNum")
	public void queryDispatcNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> fileMap = dispatcService.queryAllDispatcNum();
		om.writeValue(response.getWriter(), fileMap);
		
	}
	/**
	 * 接口数量
	 */
	@RequestMapping(value="/queryInterFaceNum")
	public void queryInterfaceNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> fileMap = interfaceService.queryAllInterFaceNum();
		om.writeValue(response.getWriter(), fileMap);
		
	}
	/**
	 * 表空间数量
	 */
	@RequestMapping(value="/queryTableSpaceNum")
	public void queryTableSpaceNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> fileMap = tableSpaceService.queryAllTableSpaceNum();
		om.writeValue(response.getWriter(), fileMap);
	}
}
