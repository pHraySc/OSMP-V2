package com.asiainfo.omp.model;

/**
 * @Description 文档对象
 * @author yangbin
 * @date 2017.6.20
 *
 */
public class FileSystemInfo {
	//private int id;//id
	private String status;//文件状态，0正常1异常
	private String fileName;//表空间名称
	private String remainPercent;//剩余百分比
	private String remainSpace;//剩余大小
	private String threshold;//阈值
	private int totalSpace;//总空间
	private String filePath;//文件路径
	private int dataSource;//数据来源1数据仓库2一经库3
	private int expNum;//异常数量
	private int normalNum;//正常数量
	private String ip;//ip
	private String updateTime;//更新时间
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemainPercent() {
		return remainPercent;
	}
	public void setRemainPercent(String remainPercent) {
		this.remainPercent = remainPercent;
	}
	public String getRemainSpace() {
		return remainSpace;
	}
	public void setRemainSpace(String remainSpace) {
		this.remainSpace = remainSpace;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public int getTotalSpace() {
		return totalSpace;
	}
	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	public int getExpNum() {
		return expNum;
	}
	public void setExpNum(int expNum) {
		this.expNum = expNum;
	}
	public int getNormalNum() {
		return normalNum;
	}
	public void setNormalNum(int normalNum) {
		this.normalNum = normalNum;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
