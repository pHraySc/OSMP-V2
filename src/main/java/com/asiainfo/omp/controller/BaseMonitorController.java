package com.asiainfo.omp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.omp.model.OsmpBaseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
public class BaseMonitorController extends BaseController{
	private static Logger logger = Logger.getLogger(BaseMonitorController.class);
	@RequestMapping("/getBaseMonitorList")
    @ResponseBody
	public List<OsmpBaseInfo> getList(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Map<String,Object> map=new HashMap<String,Object>();
		String page=req.getParameter("page");
		String qryStr=req.getParameter("qryStr");
		page="".equals(page)?"1":page;
		map.put("qryStr",qryStr);
		map.put("startIdx",(Integer.parseInt(page)-1)*10);
		map.put("endIdx", Integer.parseInt(page)*10);
		List<OsmpBaseInfo> list=baseMonitorService.queryOsmpBaseInfo(map);
	    return list;
	}
}
