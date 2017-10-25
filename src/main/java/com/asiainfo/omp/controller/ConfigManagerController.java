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

import com.asiainfo.omp.model.DispatcConfig;
import com.asiainfo.omp.model.FileConfig;
import com.asiainfo.omp.model.InterFaceConfig;
import com.asiainfo.omp.model.TableSpaceConfig;
import com.asiainfo.omp.service.ConfigManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description 文件配置Controller
 * @author yangb
 * @date 2017/8/11
 *
 */
@Controller
@RequestMapping("/configManagerController")
public class ConfigManagerController {
	@Autowired
	ConfigManagerService confService;
	
	private static Logger logger = Logger.getLogger(ConfigManagerController.class);
	
	/**
	 * 文件系统配置列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryfileconfigList")
	public void queryFileConfigList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String fileIp = "";	//文件IP
		String filePath = "";//文件路径
		if (null != request.getParameter("fileIp")) {
			fileIp = request.getParameter("fileIp").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("fileIp", fileIp);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		List<FileConfig> fileConfList = confService.queryFileConfigList(map);
		if (null != fileConfList) {
			om.writeValue(response.getWriter(), fileConfList);
		}
	}
	
	/**
	 * 文件系统配置校验重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkFileExit")
	public void checkFileExit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//boolean flag = false;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileIp = "";	//文件IP
		String filePath = "";//文件路径
		if (null != request.getParameter("fileIp")) {
			fileIp = request.getParameter("fileIp").trim();
		}
		if (null != request.getParameter("filePath")) {
			filePath = request.getParameter("filePath").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("fileIp", fileIp);
		map.put("filePath", filePath);
		int field = 0;
		List<FileConfig> fileConfList = confService.checkFileExit(map);
		if (fileConfList.size()>0) {
			field = fileConfList.get(0).getId();
		}
		om.writeValue(response.getWriter(), field);
	}
	
	/**
	 * 文件系统配置新增
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/saveFileConfigInfo")
	public void saveFileConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileIp = request.getParameter("fileIp").trim();
		String filePath = request.getParameter("filePath").trim();
		String threshold = request.getParameter("threshold").trim();
		String creator = request.getParameter("creator").trim();
		ObjectMapper om = new ObjectMapper();
		FileConfig config = new FileConfig();
		config.setFileIp(fileIp);
		config.setCreator(creator);
		config.setFilePath(filePath);
		config.setThreshold(Double.valueOf(threshold));
		int id = 0;
		try {
			confService.saveFileConfigInfo(config);//保存信息
			id= config.getId();//获取id
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), id);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
			}
	}
	
	/**
	 * 文件系统配置更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updateFileConfigInfo")
	public void updateFileConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileIp = request.getParameter("fileIp").trim();
		String filePath = request.getParameter("filePath").trim();
		String threshold = request.getParameter("threshold").trim();
		String creator = request.getParameter("creator").trim();
		String fileId = request.getParameter("fileId").trim();
		ObjectMapper om = new ObjectMapper();
		FileConfig config = new FileConfig();
		config.setId(Integer.parseInt(fileId));
		config.setFileIp(fileIp);
		config.setCreator(creator);
		config.setFilePath(filePath);
		config.setThreshold(Double.valueOf(threshold));
		int id = 0;
		try {
			confService.updateFileConfigInfo(config);//更新信息
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		
	}
	
	/**
	 * 文件系统配置删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteFileInfo")
	public void deleteFileInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileId = request.getParameter("fileId").trim();
		ObjectMapper om = new ObjectMapper();
		if (fileId != null && Integer.parseInt(fileId)>0) {
			try {
				confService.deleteFileConfigInfo(Integer.parseInt(fileId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("删除失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		}else{
			om.writeValue(response.getWriter(), false);
		}
		
	}
	
	
	
	
	/**
	 * 表空间配置列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryTableSpaceConfigList")
	public void queryTableSpaceConfigList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String tableSpaceName = "";	//表空间名称
		if (null != request.getParameter("tableSpaceName")) {
			tableSpaceName = request.getParameter("tableSpaceName").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("tableSpaceName", tableSpaceName);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		List<TableSpaceConfig> tableConfList = confService.queryTableSpaceConfigList(map);
		if (null != tableConfList) {
			om.writeValue(response.getWriter(), tableConfList);
		}
	}
	
	/**
	 * 表空间配置校验重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkTableSpaceExit")
	public void checkTableSpaceExit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//boolean flag = false;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tableSpaceName = "";	//表空间名称
		String dataSource = "";//数据源
		if (null != request.getParameter("tableSpaceName")) {
			tableSpaceName = request.getParameter("tableSpaceName").trim();
		}
		if (null != request.getParameter("dataSource")) {
			dataSource = request.getParameter("dataSource").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("tableSpaceName", tableSpaceName);
		map.put("dataSource", dataSource);
		int field = 0;
		List<TableSpaceConfig> tableConfList = confService.checkTableSpaceExit(map);
		if (tableConfList.size()>0) {
			field = tableConfList.get(0).getId();
		}
		om.writeValue(response.getWriter(), field);
	}
	
	/**
	 * 表空间配置新增
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/saveTableSpaceConfigInfo")
	public void saveTableSpaceConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tableSpaceName = request.getParameter("tableSpaceName").trim();
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String threshold = request.getParameter("threshold").trim();
		String creator = request.getParameter("creator").trim();
		ObjectMapper om = new ObjectMapper();
		TableSpaceConfig config = new TableSpaceConfig();
		config.setTableSpaceName(tableSpaceName);
		config.setDataSource(dataSource);
		config.setCreator(creator);
		config.setThreshold(Double.valueOf(threshold));
		int id = 0;
		try {
			confService.saveTableSpaceConfigInfo(config);//保存信息
			id= config.getId();//获取id
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), id);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
			}
	}
	
	/**
	 * 表空间配置更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updateTableSpaceConfigInfo")
	public void updateTableSpaceConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tableSpaceName = request.getParameter("tableSpaceName").trim();
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String threshold = request.getParameter("threshold").trim();
		String creator = request.getParameter("creator").trim();
		String tableSpaceId = request.getParameter("tableSpaceId").trim();
		ObjectMapper om = new ObjectMapper();
		TableSpaceConfig config = new TableSpaceConfig();
		config.setId(Integer.parseInt(tableSpaceId));
		config.setDataSource(dataSource);
		config.setThreshold(Double.valueOf(threshold));
		config.setTableSpaceName(tableSpaceName);
		config.setCreator(creator);
		int id = 0;
		try {
			confService.updateTableSpaceConfigInfo(config);//更新信息
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		
	}
	
	/**
	 * 表空间配置删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteTableSpaceInfo")
	public void deleteTableSpaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableSpaceId = request.getParameter("tableSpaceId").trim();
		ObjectMapper om = new ObjectMapper();
		if (tableSpaceId != null && Integer.parseInt(tableSpaceId)>0) {
			try {
				confService.deleteTableSpaceConfigInfo(Integer.parseInt(tableSpaceId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("删除失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		}else{
			om.writeValue(response.getWriter(), false);
		}
		
	}
	
	/**
	 * 调度配置列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryDispatcConfigList")
	public void queryDispatcConfigList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String nodeId = "";	//NodeId
		if (null != request.getParameter("nodeId")) {
			nodeId = request.getParameter("nodeId").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("nodeId", nodeId);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		List<DispatcConfig> dispatcConfList = confService.queryDispatcConfigList(map);
		if (null != dispatcConfList) {
			om.writeValue(response.getWriter(), dispatcConfList);
		}
	}
	
	/**
	 * 调度配置校验重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkDispatcExit")
	public void checkDispatcExit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//boolean flag = false;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nodeId = "";	//NodeId
		if (null != request.getParameter("nodeId")) {
			nodeId = request.getParameter("nodeId").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("nodeId", nodeId);
		int field = 0;
		List<DispatcConfig> tableConfList = confService.checkDispatcExit(map);
		if (tableConfList.size()>0) {
			field = tableConfList.get(0).getId();
		}
		om.writeValue(response.getWriter(), field);
	}
	
	/**
	 * 调度配置新增
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/saveDispatcConfigInfo")
	public void saveDispatcConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String isDay = request.getParameter("isDay").trim();
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String creator = request.getParameter("creator").trim();
		String nodeId = request.getParameter("nodeId").trim();
		String dispatcType = request.getParameter("dispatcType").trim();
		String demainTime = request.getParameter("demainTime").trim().toString();
		ObjectMapper om = new ObjectMapper();
		DispatcConfig config = new DispatcConfig();
		config.setCreator(creator);
		config.setDataSource(dataSource);
		config.setDemainTime(demainTime);
		config.setDispatcType(dispatcType);
		config.setIsDay(isDay);
		config.setNodeId(nodeId);
		int id = 0;
		try {
			confService.saveDispatcConfigInfo(config);//保存信息
			id= config.getId();//获取id
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), id);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
			}
	}
	
	/**
	 * 调度配置更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updateDispatcConfigInfo")
	public void updateDispatcConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String isDay = request.getParameter("isDay").trim();
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String creator = request.getParameter("creator").trim();
		String nodeId = request.getParameter("nodeId").trim();
		String dispatcType = request.getParameter("dispatcType").trim();
		String demainTime = request.getParameter("demainTime").trim();
		String dispatcId = request.getParameter("dispatcId").trim();
		ObjectMapper om = new ObjectMapper();
		DispatcConfig config = new DispatcConfig();
		config.setId(Integer.parseInt(dispatcId));
		config.setCreator(creator);
		config.setDataSource(dataSource);
		config.setDemainTime(demainTime);
		config.setDispatcType(dispatcType);
		config.setIsDay(isDay);
		config.setNodeId(nodeId);
		int id = 0;
		try {
			confService.updateDispatcConfigInfo(config);;//更新信息
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		
	}
	
	/**
	 * 调度配置删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteDispatcInfo")
	public void deleteDispatcInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String dispatcId = request.getParameter("dispatcId").trim();
		ObjectMapper om = new ObjectMapper();
		if (dispatcId != null && Integer.parseInt(dispatcId) >0) {
			try {
				confService.deleteDispatcConfigInfo(Integer.parseInt(dispatcId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("删除失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		}else{
			om.writeValue(response.getWriter(), false);
		}
		
	}
	
	
	
	//-----------接口监控配置-------------//
	/**
	 * 接口配置列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryInterFaceConfigList")
	public void queryInterFaceConfigList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String interNo = "";	//interNo
		if (null != request.getParameter("interNo")) {
			interNo = request.getParameter("interNo").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("interNo", interNo);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		List<InterFaceConfig> interConfList = confService.queryInterFaceConfigList(map);
		if (null != interConfList) {
			om.writeValue(response.getWriter(), interConfList);
		}
	}
	
	/**
	 * 接口配置校验重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/checkInterFaceExit")
	public void checkInterFaceExit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//boolean flag = false;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String interNo = "";//NodeId
		String dataSource = "";//dataSource
		if (null != request.getParameter("interNo")) {
			interNo = request.getParameter("interNo").trim();
		}
		if (null != request.getParameter("dataSource")) {
			dataSource = request.getParameter("dataSource").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("interNo", interNo);
		map.put("dataSource", dataSource);
		int field = 0;
		List<InterFaceConfig> tableConfList = confService.checkInterFaceExit(map);
		if (tableConfList.size()>0) {
			field = tableConfList.get(0).getId();
		}
		om.writeValue(response.getWriter(), field);
	}
	
	/**
	 * 接口配置保存
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/saveInterFaceConfigInfo")
	public void saveInterFaceConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String isImport = request.getParameter("isImport").trim();
		String creator = request.getParameter("creator").trim();
		String interNo = request.getParameter("interNo").trim();
		String interType = request.getParameter("interType").trim();
		String demainTime = request.getParameter("demainTime").trim().toString();
		ObjectMapper om = new ObjectMapper();
		InterFaceConfig config = new InterFaceConfig();
		config.setCreator(creator);
		config.setDataSource(dataSource);
		config.setDemainTime(demainTime);
		config.setInterType(interType);
		config.setIsImport(isImport);
		config.setInterNo(interNo);
		int id = 0;
		try {
			confService.saveInterfaceConfigInfo(config);//保存信息
			id= config.getId();//获取id
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), id);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
			}
	}
	
	/**
	 * 接口配置更新
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updateInterFaceConfigInfo")
	public void updateInterFaceConfigInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String isImport = request.getParameter("isImport").trim();
		int dataSource = Integer.parseInt(request.getParameter("dataSource").trim());
		String creator = request.getParameter("creator").trim();
		String interNo = request.getParameter("interNo").trim();
		String interType = request.getParameter("interType").trim();
		String demainTime = request.getParameter("demainTime").trim();
		String interfaceId = request.getParameter("interfaceId").trim();
		ObjectMapper om = new ObjectMapper();
		InterFaceConfig config = new InterFaceConfig();
		config.setCreator(creator);
		config.setDataSource(dataSource);
		config.setDemainTime(demainTime);
		config.setInterType(interType);
		config.setIsImport(isImport);
		config.setInterNo(interNo);
		config.setId(Integer.parseInt(interfaceId));
		int id = 0;
		try {
			confService.updateInterfaceConfigInfo(config);;//更新信息
			System.out.println("faultId====="+id);
			om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		
	}
	
	/**
	 * 接口配置删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteInterFaceInfo")
	public void deleteInterFaceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String interfaceId = request.getParameter("interfaceId").trim();
		ObjectMapper om = new ObjectMapper();
		if (interfaceId != null && Integer.parseInt(interfaceId) >0) {
			try {
				confService.deleteInterfaceConfigInfo(Integer.parseInt(interfaceId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info("删除失败"+e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		}else{
			om.writeValue(response.getWriter(), false);
		}
		
	}
}
