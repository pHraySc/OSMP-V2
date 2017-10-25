package com.asiainfo.omp.model;

/**
 * @Description 表空间对象
 * @author yangbin
 * @date 2017.6.20
 *
 */
public class TableSpaceInfo {

	//private int id;//id
	private String status;//表空间状态，0正常1异常
	private String tableSpaceName;//表空间名称
	private String actualPercent;//实际百分比
	private String remainSpace;//剩余空间
	private String threshold;//阈值
	private String totalSpace;//总空间
	private int dataSource;//数据来源1数据仓库2一经库3
	private String updateTime;//更新时间
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTableSpaceName() {
		return tableSpaceName;
	}
	public void setTableSpaceName(String tableSpaceName) {
		this.tableSpaceName = tableSpaceName;
	}
	public String getActualPercent() {
		return actualPercent;
	}
	public void setActualPercent(String actualPercent) {
		this.actualPercent = actualPercent;
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
	public String getTotalSpace() {
		return totalSpace;
	}
	public void setTotalSpace(String totalSpace) {
		this.totalSpace = totalSpace;
	}
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	
}
