package com.asiainfo.alarm.model;

/**
 * 标签库源表扩展信息
 */
public class CocSourceTableExt {
    /**
     * 源表编码
     */
    private String sourceTableCode;
    /**
     * 源表生成时间
     */
    private String updateTime;
    /**
     * 数据提供方：厂商或数据部等
     */
    private String producer;
    /**
     * 数据时效性：1，t-1；2，t-2'
     */
    private int delayValue;
    /**
     * 1：调度平台；2：外部接口；3：独立程序（jar、shell等）
     */
    private int produceType;
    /**
     * 调度号
     */
    private String taskCode;
    /**
     * 接口号
     */
    private String interfaceCode;
    /**
     * 接口机IP
     */
    private String interfaceServerIp;
    /**
     * 接口文件路径
     */
    private String interfaceFilePath;
    /**
     * 独立程序部署主机IP
     */
    private String executorServerIp;
    /**
     * 独立程序部署路径
     */
    private String executorFilePath;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactTel;
    /**
     * 联系人电子邮箱
     */
    private String contactEmail;
    /**
     * 备注
     */
    private String comment;

    public String getSourceTableCode() {
        return sourceTableCode;
    }

    public void setSourceTableCode(String sourceTableCode) {
        this.sourceTableCode = sourceTableCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getDelayValue() {
        return delayValue;
    }

    public void setDelayValue(int delayValue) {
        this.delayValue = delayValue;
    }

    public int getProduceType() {
        return produceType;
    }

    public void setProduceType(int produceType) {
        this.produceType = produceType;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getInterfaceServerIp() {
        return interfaceServerIp;
    }

    public void setInterfaceServerIp(String interfaceServerIp) {
        this.interfaceServerIp = interfaceServerIp;
    }

    public String getInterfaceFilePath() {
        return interfaceFilePath;
    }

    public void setInterfaceFilePath(String interfaceFilePath) {
        this.interfaceFilePath = interfaceFilePath;
    }

    public String getExecutorServerIp() {
        return executorServerIp;
    }

    public void setExecutorServerIp(String executorServerIp) {
        this.executorServerIp = executorServerIp;
    }

    public String getExecutorFilePath() {
        return executorFilePath;
    }

    public void setExecutorFilePath(String executorFilePath) {
        this.executorFilePath = executorFilePath;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
