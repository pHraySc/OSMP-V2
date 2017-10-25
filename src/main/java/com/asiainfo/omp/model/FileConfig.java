package com.asiainfo.omp.model;

import java.sql.Timestamp;
/**
 * @description 文件配置
 * @author yangb
 * @date 2017/8/11
 *
 */
public class FileConfig {

	private int id;
	private String fileIp;
	private String filePath;
	private double threshold;
	private String creator;
	private String createTime;
	
	public String getFileIp() {
		return fileIp;
	}
	public void setFileIp(String fileIp) {
		this.fileIp = fileIp;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
