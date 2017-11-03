package com.asiainfo.omp.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
@Controller
public class OsmpController extends BaseController{
	private static Logger logger = Logger.getLogger(OsmpController.class);

    @RequestMapping("")  
    public String web(ModelMap model){  
        model.put("time",new Date());  
        model.put("message","freemarker test!");
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        for(int i = 0;i<10;i++){
        	Map<String,String> map= new HashMap<String,String>();
        	map.put(String.valueOf(i),"aaaa"+i);
        	list.add(map);
        }
        model.addAttribute("list", list);
        logger.debug("This is a debug message");  
        logger.info("This is an info message");  
        logger.warn("This is a warn message");  
        logger.error("This is an error message");  
        return "baseMonitor_CHARTS";//返回的内容就是templetes下面文件的名称  
    }
    //首页跳转图表页面
    @RequestMapping("/baseMonitor_CHARTS")
    public String baseMonitor_CHARTS(ModelMap model){
    	model.put("time", new Date());
    	model.put("message", "freemarker test!");
    	return "baseMonitor_CHARTS";
    }
    
    //首页跳转彩信保障页面
    @RequestMapping("/MMSsecurity")
    public String MMSsecurity(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "MMSsecurity";
    }
    //跳转基础监控_表空间监控
    @RequestMapping("/baseMonitor_TABLESPACE")
    public String baseMonitor_TABLESPACE(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "baseMonitor_TABLESPACE";
    }
    //跳转基础监控_文件系统监控
    @RequestMapping("/baseMonitor_FILE")
    public String baseMonitor_FILE(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "baseMonitor_FILE";
    }
    //跳转基础监控_调度系统监控
    @RequestMapping("/baseMonitor_DISPATC")
    public String baseMonitor_DISPATC(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "baseMonitor_DISPATC";
    }
    //跳转基础监控页面
    @RequestMapping("/baseMonitor")
    public String baseMonitor(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "baseMonitor";
    }
    //跳转应用监控页面
    @RequestMapping("/appMonitor")
    public String appMonitor(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "appMonitor";
    }
    //跳转专题监控页面
    @RequestMapping("/speMonitor")
    public String speMonitor(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "speMonitor";
    }
    //跳转配置模块页面
    @RequestMapping("/confModel")
    public String confModel(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "config_File";
    }
  //跳转知识库页面
    @RequestMapping("/knowledge")
    public String knowledge(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "knowledge";
    }
    //跳转文件配置界面
    @RequestMapping("/config_File")
    public String configFile(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "config_File";
    }
    
    //跳转表空间配置界面
    @RequestMapping("/config_TableSpace")
    public String configTableSapce(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "config_TableSpace";
    }
    
    //跳转调度配置界面
    @RequestMapping("/config_Dispatc")
    public String configDispatc(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "config_Dispatc";
    }
    
    //跳转接口配置界面
    @RequestMapping("/config_InterFace")
    public String configInterFace(ModelMap model){
    	model.put("time",new Date());  
        model.put("message","freemarker test!");
    	return "config_InterFace";
    }

    //跳转标签库监控页面
    @RequestMapping("/cocAlarm")
    public String cocAlarm(ModelMap model){
        model.put("time",new Date());
        model.put("message","freemarker test!");
        return "cocAlarm";
    }

    

    //跳转标签库监控界面-默认汇总页面
    @RequestMapping("/labelMonitor")
    public String labelMonitor(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor";
    }

    //跳转标签库监控界面-源表
    @RequestMapping("/labelMonitor_SOURCE")
    public String labelMonitor_SOURCE(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_SOURCE";
    }

    //跳转标签库监控界面-标签
    @RequestMapping("/labelMonitor_TAG")
    public String labelMonitor_TAG(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_TAG";
    }

}
