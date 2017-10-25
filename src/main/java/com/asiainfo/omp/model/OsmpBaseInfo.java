package com.asiainfo.omp.model;

import java.util.Map;

public class OsmpBaseInfo {
	private int    id;
	private String interNo;
	private String    status;
	private String startTime;
	private String endTime;
	private long   lastSuccNum;
	private long   succNum;
	private String tableName;
	private String errorMsg;
	private String scheduleTime;
	private String insertTime;
	private double  waveNum;
	private String waveStatus;
	private String timeOut;
	private int  notstart;
	private int  process;
	private int  success;
	private int  fail;
	private int  wavestatus;
	private int  timeout;
	private int totalnum;
	
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
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
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInterNo() {
		return interNo;
	}
	public void setInterNo(String interNo) {
		this.interNo = interNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public long getLastSuccNum() {
		return lastSuccNum;
	}
	public void setLastSuccNum(long lastSuccNum) {
		this.lastSuccNum = lastSuccNum;
	}
	public long getSuccNum() {
		return succNum;
	}
	public void setSuccNum(long succNum) {
		this.succNum = succNum;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public double getWaveNum() {
		return waveNum;
	}
	public void setWaveNum(double waveNum) {
		this.waveNum = waveNum;
	}
	public String getWaveStatus() {
		return waveStatus;
	}
	public void setWaveStatus(String waveStatus) {
		this.waveStatus = waveStatus;
	}
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
    

}
