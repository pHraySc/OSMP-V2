package com.asiainfo.omp.model;

/**
 * @Description 附件 
 * @date 2017/6/27
 * @author yangb
 *
 */
public class Attachment {
	
	private int id;//附件id
	private String attName;//附件名称
	private String attPath;//附件路径
	private int faultFlowId;//故障id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	public String getAttPath() {
		return attPath;
	}
	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}
	public int getFaultFlowId() {
		return faultFlowId;
	}
	public void setFaultFlowId(int faultFlowId) {
		this.faultFlowId = faultFlowId;
	}
	
	
	

}
