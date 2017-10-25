package com.asiainfo.omp.model;

import java.sql.Timestamp;

public class DispatcConfig {
	

	private int id;
	private int dataSource;
	private String creator;
	private String createTime;
    private String demainTime;
    private String nodeId;
    private String isDay;
    private String dispatcType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getIsDay() {
		return isDay;
	}
	public void setIsDay(String isDay) {
		this.isDay = isDay;
	}
	public String getDispatcType() {
		return dispatcType;
	}
	public void setDispatcType(String dispatcType) {
		this.dispatcType = dispatcType;
	}
	public String getDemainTime() {
		return demainTime;
	}
	public void setDemainTime(String demainTime) {
		this.demainTime = demainTime;
	}
    
}
