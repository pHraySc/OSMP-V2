package com.asiainfo.omp.model;

/**
 * @Description 接口监控
 * @author yangbin
 * @date 2017.6.20
 *
 */
public class InterfaceInfo {

	//private int id;
	private int status;//接口状态 0加载成功，-1调度失败 2 波动异常 3 进行中 4未开始 99超时
	private String interName;//接口名称
	private String tableName;//表名
	private String beginDate;//开始时间
	private String actualFinDate;//实际完成时间
	private String demainDate;//要求完成时间
	private int sumMum;//总条数
	private int waveNum;//波动数量
	private int timeout;//超时
	private int dataSource;//数据来源1数据仓库2一经库3
	private int notstart;//未开始数量
	private int process;//进行中数量
	private int success;//成功数量
	private int fail;//失败数量
	private int wavestatus;//波动数量
	private int totalnum;//总量
	private String arriveDate;//到达时间
	private String errorDec;//错误描述
	private String updateTime;//更新时间
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInterName() {
		return interName;
	}
	public void setInterName(String interName) {
		this.interName = interName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getActualFinDate() {
		return actualFinDate;
	}
	public void setActualFinDate(String actualFinDate) {
		this.actualFinDate = actualFinDate;
	}
	public String getDemainDate() {
		return demainDate;
	}
	public void setDemainDate(String demainDate) {
		this.demainDate = demainDate;
	}
	public int getSumMum() {
		return sumMum;
	}
	public void setSumMum(int sumMum) {
		this.sumMum = sumMum;
	}
	public int getWaveNum() {
		return waveNum;
	}
	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	public int getNotstart() {
		return notstart;
	}
	public void setNotstart(int notstart) {
		this.notstart = notstart;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getWavestatus() {
		return wavestatus;
	}
	public void setWavestatus(int wavestatus) {
		this.wavestatus = wavestatus;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public String getErrorDec() {
		return errorDec;
	}
	public void setErrorDec(String errorDec) {
		this.errorDec = errorDec;
	}
	public String getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
