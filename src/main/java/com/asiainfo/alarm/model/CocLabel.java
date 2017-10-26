package com.asiainfo.alarm.model;

/**
 * Created by Sccc on 2017/10/26.
 */
public class CocLabel {

    /**
     * 标签ID(关联扩展信息)
     */
    private long labelId;

    /**
     * 标签名
     */
    private String labelName;

    /**
     * 数据周期： 1.日 ， 2.月
     */
    private int dataCycle;

    /**
     * 数据日期
     */
    private String dataDate;

    /**
     * 标签用户数
     */
    private long customNum;

    /**
     * 标签扩展信息
     */
    private CocLabelExt cocLabelExt;

    public int getDataCycle() {
        return dataCycle;
    }

    public void setDataCycle(int dataCycle) {
        this.dataCycle = dataCycle;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public long getCustomNum() {
        return customNum;
    }

    public void setCustomNum(long customNum) {
        this.customNum = customNum;
    }

    public CocLabelExt getCocLabelExt() {
        return cocLabelExt;
    }

    public void setCocLabelExt(CocLabelExt cocLabelExt) {
        this.cocLabelExt = cocLabelExt;
    }

    public String getLabelName() {

        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
