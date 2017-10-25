package com.asiainfo.omp.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asiainfo.omp.model.Attachment;
import com.asiainfo.omp.service.AttachmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/fileUploadContoller")
public class FileUploadContoller {

	private static Logger logger = Logger.getLogger(FileUploadContoller.class);
	
	@Autowired
	AttachmentService service;
	
    /**
     * 上传附件
     * @param request
     * @param response
     * @param file
     * @throws Exception
     */
	@RequestMapping(value="/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="file", required=false) MultipartFile file) throws Exception{
	    String fileName = file.getOriginalFilename();
//		String path="E:/upload";//windows
		String path="/home/ocdc/yx_osmp/uploadFile";//linux、unix
		File target = new File(path);
		ObjectMapper om = new ObjectMapper(); 
	    int attId=0;
		if (!target.exists() && !target.isDirectory()) {
			System.out.println(path+"目录不存在，需要创建");
			//创建目录
			target.mkdir();
		}
		try {
			  String type = fileName.substring(fileName.indexOf(".")+1);
			  SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyyMMddHHmmss");
			  String newFlieName = dateFormat.format(new Date()) + "." + type;
			  String filePath = path + "/" + newFlieName;
			  //saveFile(filePath, file);
			  file.transferTo(new File(filePath));
			  Attachment attach = new Attachment();
			  attach.setAttName(fileName);
			  attach.setAttPath(filePath);
			  saveAttachment(attach);
			  attId = attach.getId();
		} catch (Exception e) {
			logger.info("附件上传失败" + e.getMessage());
		}
		om.writeValue(response.getWriter(), attId);
	}
	
	
	public  void saveFile(String savePath, File upload) {
		try {
			InputStream in = null;
			OutputStream out = null;
			in = new FileInputStream(upload);
			out = new FileOutputStream(savePath);
			int readed = 0;
			byte[] buffer = new byte[1024];
		while ((readed = in.read(buffer, 0, 1024)) != -1) {
			out.write(buffer, 0, readed);
		}
			out.flush();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	/**
	 * 保存附件
	 * @param attach
	 */
	public void saveAttachment(Attachment attach){
		service.saveAttachmentInfo(attach);
	}
	
	/**
	 * 删除附件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAtt")
	public void deleteAttachment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ObjectMapper om = new ObjectMapper(); 
	    boolean flag= false;
	    int attId=0;
	    if (null != request.getParameter("attId")) {
	    	attId = Integer.parseInt(request.getParameter("attId"));
		}
	    if (attId > 0) {
	    	try {
	    		service.deleteAttachmentInfo(attId);
	    		flag = true;
	    	} catch (Exception e) {
	    		logger.info(e.getMessage());
	    	}
		}
	    om.writeValue(response.getWriter(), flag);
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="filedownload")
	public void downLoad(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.reset();
		String path ="";
		String fileName = "";
		if (null != request.getParameter("url")) {
			path = request.getParameter("url");
		}
		if (null != request.getParameter("fileName")) {
			fileName = request.getParameter("fileName");
		}
		
		File file = new File(path);
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的文件已删除！");
			return;
		}
		//设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType("application/octet-stream");
		FileInputStream in = new FileInputStream(path);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区当中
		while((len=in.read(buffer))>0){
		//输出缓冲区的内容到浏览器，实现文件下载
		out.write(buffer, 0, len);
		}
		//关闭文件输入流
		in.close();
		//关闭输出流
		out.flush();
		out.close();
	}
}
