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
     * 标签使用次数统计
     */
    private long useTimes;

    /**
     * 标签用户数
     */
    private long customNum;

    /**
     * 对应源表名
     */
    private String srcTabName;

    /**
     * 对应源表code
     */
    private String srcTabCode;
    /**
     * 对应源表字段
     */
    private String srcTabColName;

    /**
     * 标签状态：1.正常 2.延迟异常 3.波动异常
     */
    private int status;

    /**
     * 标签异常信息
     */
    private String errMsg;

    /**
     * 标签扩展信息
     */
    private CocLabelExt cocLabelExt;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

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

    public long getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(long useTimes) {
        this.useTimes = useTimes;
    }

    public long getCustomNum() {
        return customNum;
    }

    public void setCustomNum(long customNum) {
        this.customNum = customNum;
    }

    public String getSrcTabName() {
        return srcTabName;
    }

    public void setSrcTabName(String srcTabName) {
        this.srcTabName = srcTabName;
    }

    public String getSrcTabColName() {
        return srcTabColName;
    }

    public void setSrcTabColName(String srcTabColName) {
        this.srcTabColName = srcTabColName;
    }

    public CocLabelExt getCocLabelExt() {
        return cocLabelExt;
    }

    public void setCocLabelExt(CocLabelExt cocLabelExt) {
        this.cocLabelExt = cocLabelExt;
    }
}
