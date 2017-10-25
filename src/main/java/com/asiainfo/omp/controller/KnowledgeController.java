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

import com.asiainfo.omp.model.FaultFLow;
import com.asiainfo.omp.service.AttachmentService;
import com.asiainfo.omp.service.KnowledgeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/knowledgeController")
public class KnowledgeController {

	@Autowired
	KnowledgeService service;
	
	@Autowired
	AttachmentService attService;
	
	private static Logger logger = Logger.getLogger(KnowledgeController.class);
	
	/**
	 * 分页查询列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryList")
	public void queryKnowledgeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int currPage = 0;
		int pageSize=8;//每页8条数据
		if (null != request.getParameter("page")) {
			currPage = Integer.parseInt((request.getParameter("page")));// 当前页码
		}
		int pageNum=(currPage-1)*pageSize;//页码
		String faultFlowName = "";	//故障名称
		if (null != request.getParameter("faultFlowName")) {
			faultFlowName = request.getParameter("faultFlowName").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("faultFlowName", faultFlowName);
		map.put("pageNum", pageNum);
		map.put("pageSize",  pageSize);
		List<FaultFLow> faultList = service.queryKnowledgeInfoList(map);
		om.writeValue(response.getWriter(), faultList);
		
	}
	
	/**
	 * 查询总页数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/queryKnowNum")
	public void queryKnowledgeNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String faultFlowName = "";	//故障名称
		if (null != request.getParameter("faultFlowName")) {
			faultFlowName = request.getParameter("faultFlowName").trim();
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("faultFlowName", faultFlowName);
		List<FaultFLow> faultList = service.queryKnowledgeInfoList(map);
		om.writeValue(response.getWriter(), faultList);
		
	}
	
	/**
	 * 通过故障id查询附件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="queryAttachList")
	public void queryAttachByFaultId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int faultId=0;
		if (null != request.getParameter("faultId")) {
			faultId = Integer.parseInt(request.getParameter("faultId"));
		}
		Map<String, Object> map=new HashMap<String,Object>();
		ObjectMapper om = new ObjectMapper();
		map.put("faultId", faultId);
		List<Map<String,Object>> attachList = service.queryAttachByFaultId(map);
		om.writeValue(response.getWriter(), attachList);
	}
	
	/**
	 * 保存信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="saveFaultInfo")
	public void saveFaultInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String faultname = request.getParameter("faultname").trim();
		String typeName = request.getParameter("typeName").trim();
		String dealmethod = request.getParameter("dealmethod").trim();
		String faultcontent = request.getParameter("faultcontent").trim();
		String addusername = request.getParameter("addusername").trim();
		String attIds = request.getParameter("attIds");
		int faultId = 0;
		if (request.getParameter("faultId") != null && request.getParameter("faultId") != "") {
			faultId = Integer.valueOf(request.getParameter("faultId"));
		}
		ObjectMapper om = new ObjectMapper();
/*		System.out.println(request.getParameter("faultId"));
		if (request.getParameter("faultId") != null && request.getParameter("faultId") != "") {
			 int faultId = Integer.valueOf(request.getParameter("faultId"));
			 try {
				 if (attIds !=null && !attIds.equals("")) {
						String[] attIdArry = attIds.split(",");
						for (int i = 0; i < attIdArry.length; i++) {
							Map<String, Object> map=new HashMap<String,Object>();
							map.put("attId", attIdArry[i]);
							map.put("flowId", faultId);
							attService.updateAttachment(map);//更新附件信息
						}
					}
				 //om.writeValue(response.getWriter(), );
			} catch (Exception e) {
				logger.info("保存附件"+e.getMessage());
			}
		}else{*/
		if (faultId > 0) {
			FaultFLow fault = new FaultFLow();
			fault.setFaultContent(faultcontent);
			fault.setDealMethod(dealmethod);
			fault.setDealPersons(addusername);
			fault.setFaultName(faultname);
			fault.setTypeName(typeName);
			fault.setId(faultId);
			try {
				service.updateFaultInfo(fault);//更新
				om.writeValue(response.getWriter(), faultId);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			FaultFLow fault = new FaultFLow();
			fault.setFaultContent(faultcontent);
			fault.setDealMethod(dealmethod);
			fault.setDealPersons(addusername);
			fault.setFaultName(faultname);
			fault.setTypeName(typeName);
			try {
				service.saveFaultInfo(fault);//保存信息
				int id= fault.getId();//获取id
				System.out.println("faultId====="+id);
				om.writeValue(response.getWriter(), id);
			} catch (Exception e) {
				logger.info("保存失败"+e.getMessage());
			}
		}
		//}
		
		
	}
	
	
	/**
	 * 更新信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="updateFaultInfo")
	public void updateFaultInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String faultname = request.getParameter("faultname").trim();
		String typeName = request.getParameter("typeName").trim();
		String dealmethod = request.getParameter("dealmethod").trim();
		String faultcontent = request.getParameter("faultcontent").trim();
		String addusername = request.getParameter("addusername").trim();
		int faultId = Integer.valueOf(request.getParameter("faultId"));
		String attIds = request.getParameter("attIds");
		FaultFLow fault = new FaultFLow();
		fault.setFaultContent(faultcontent);
		fault.setDealMethod(dealmethod);
		fault.setDealPersons(addusername);
		fault.setFaultName(faultname);
		fault.setTypeName(typeName);
		fault.setId(faultId);
		ObjectMapper om = new ObjectMapper();
		boolean success = false;
		try {
			service.updateFaultInfo(fault);//更新
			faultId= fault.getId();//获取id
			success = true;
			om.writeValue(response.getWriter(), success);
		} catch (Exception e) {
			logger.info("保存失败"+e.getMessage());
			om.writeValue(response.getWriter(), success);
		}
		
	}
	
	/**
	 * 绑定附件信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="bindInfo")
	public void bindInfoAndAttach(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int faultId = Integer.valueOf(request.getParameter("faultId"));
		String attIds = request.getParameter("attIds");
		boolean success = false;
		ObjectMapper om = new ObjectMapper();
		try {
			if (attIds !=null && !attIds.equals("")) {
				String[] attIdArry = attIds.split(",");
				for (int i = 0; i < attIdArry.length; i++) {
					Map<String, Object> map=new HashMap<String,Object>();
					map.put("attId", attIdArry[i]);
					map.put("flowId", faultId);
					attService.updateAttachment(map);//更新附件信息
				}
			}
			success = true;
			om.writeValue(response.getWriter(), success);
		} catch (Exception e) {
			logger.info("附件保存失败"+e.getMessage());
			om.writeValue(response.getWriter(), success);
		}
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="deleteFaultInfo")
	public void deleteKnowledge(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String faultId = request.getParameter("faultId");
		ObjectMapper om = new ObjectMapper();
		//删除故障流程数据
		if (faultId != null && Integer.parseInt(faultId) > 0) {
			try {
				service.deleteFaultInfo(Integer.parseInt(faultId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info(e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
			//删除故障流程对应附件
			try {
				attService.deleteAttByFaultId(Integer.parseInt(faultId));
				om.writeValue(response.getWriter(), true);
			} catch (Exception e) {
				logger.info(e.getMessage());
				om.writeValue(response.getWriter(), false);
			}
		}else{
			om.writeValue(response.getWriter(), false);
		}
	}
}
