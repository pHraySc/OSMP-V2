package com.asiainfo.omp.model;

/**
 * @Description 调度
 * @author PC
 * @date 2017/8/1
 *
 */
public class Dispatc {

	//private int id;//id
	private String flowName;//流程名称
	private String nodeName;//节点名称
	private String beginDate;//开始时间
	private String actualDate;//实际时间
	private String demainDate;//规定完成时间
	private int status;//状态 0加载成功，-1调度失败 3 进行中 4未开始 99超时
	private String taskId;//任务ID
/*	private int dataSource;//数据来源，配置数据
	private int notstart;//未开始数量
	private int process;//进行中数量
	private int success;//成功数量
	private int fail;//失败数量
	private int timeout;//超时*/	
	private String errorDec;//错误描述
	private String updateTime;//更新时间

	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getActualDate() {
		return actualDate;
	}
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}
	public String getDemainDate() {
		return demainDate;
	}
	public void setDemainDate(String demainDate) {
		this.demainDate = demainDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorDec() {
		return errorDec;
	}
	public void setErrorDec(String errorDec) {
		this.errorDec = errorDec;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
