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

import com.asiainfo.omp.service.CommonUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 公共
 * @author yangb
 *
 */
@Controller
@RequestMapping(value="/commonUtilController")
public class CommonUtilController {
	
	private static Logger logger = Logger.getLogger(CommonUtilController.class);
	
	@Autowired
	CommonUtilService service;

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryTypeByModuleId")
	public void queryTypeByModuleId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int moduleId = Integer.valueOf(request.getParameter("moduleId"));
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("moduleId", moduleId);
		List<Map<String,Object>> typeList= null;
		try {
			typeList = service.queryTypeByModuleId(map);
			om.writeValue(response.getWriter(), typeList);
		} catch (Exception e) {
			logger.info(e.getMessage());
			om.writeValue(response.getWriter(), typeList);
		}
	}
}
