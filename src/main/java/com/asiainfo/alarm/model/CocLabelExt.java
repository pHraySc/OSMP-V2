package com.asiainfo.alarm.model;

/**
 * Created by Sccc on 2017/10/26.
 */
public class CocLabelExt {

    /**
     * 标签ID(关联标签信息)
     */
    private long labelId;

    /**
     * 数据量波动情况：此属性以整型数量波动说明情况
     */
    private long wavedCustomNum;

    /**
     * 环比：按周期进行环比
     * 日：(t-2标签用户数 - t-3标签用户数)/t-3标签用户数 * 100%
     */
    private float MoM;

    /**
     * 对应源表名
     */
    private String srcTabName;

    /**
     * 对应源表字段
     */
    private String srcTabColName;

    /**
     * 标签使用次数统计
     */
    private long useTimes;

    /**
     * 标签更新时间
     */
    private String updateTime;

    /**
     * 标签开始更新时间
     */
    private String startTime;

    /**
     * 标签要求更新时间
     */
    private String scheduleTime;

    /**
     * 标签实际完成时间时间
     */
    private String finishTime;

    /**
     * 源表联系人
     */
    private String official;

    /**
     * 联系人邮箱或手机
     */
    private String emailOrPhone;

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public long getWavedCustomNum() {
        return wavedCustomNum;
    }

    public void setWavedCustomNum(long wavedCustomNum) {
        this.wavedCustomNum = wavedCustomNum;
    }

    public float getMoM() {
        return MoM;
    }

    public void setMoM(float moM) {
        MoM = moM;
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

    public long getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(long useTimes) {
        this.useTimes = useTimes;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }
}
