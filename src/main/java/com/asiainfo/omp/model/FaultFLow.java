package com.asiainfo.omp.model;

/**
 * @Description 故障流程
 * @date 2017/6/27
 * @author yangb
 *
 */
public class FaultFLow {

	private int id;//id
	private String faultName;//故障名称
	private String typeName;//故障类别
	private String faultContent;//故障内容
	private String dealMethod;//故障处理方式
	private String dealPersons;//处理人
	private String lastDealDate;//最后处理时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFaultName() {
		return faultName;
	}
	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFaultContent() {
		return faultContent;
	}
	public void setFaultContent(String faultContent) {
		this.faultContent = faultContent;
	}
	public String getDealMethod() {
		return dealMethod;
	}
	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}
	public String getDealPersons() {
		return dealPersons;
	}
	public void setDealPersons(String dealPersons) {
		this.dealPersons = dealPersons;
	}
	public String getLastDealDate() {
		return lastDealDate;
	}
	public void setLastDealDate(String lastDealDate) {
		this.lastDealDate = lastDealDate;
	}
}
