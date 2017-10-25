package com.asiainfo.omp.model;

import java.util.Date;

/**
 * @Description 配置项 
 * @date 2017/6/27
 * @author yangb
 *
 */
public class DataConfigInfo {

	private int id;//id
	private String dataName;//配置数据名称
	private int typeId;//1表空间2接口3文件系统
	private float threshold;//阈值
	private Date createDate;//配置时间
	private Date updateDate;//修改时间
	private int dataId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public float getThreshold() {
		return threshold;
	}
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
