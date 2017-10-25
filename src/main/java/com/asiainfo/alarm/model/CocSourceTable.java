package com.asiainfo.alarm.model;

/**
 * 标签库源表
 */
public class CocSourceTable {
    /**
     * 源表编码
     */
    private String sourceTableCode;
    /**
     * 源表表名
     */
    private String sourceTableName;
    /**
     * 数据周期：1，日；2，月
     */
    private int dataCycle;
    /**
     * 最新源表数据日期
     */
    private String dataDate;
    /**
     * 标签库源表扩展信息
     */
    private CocSourceTableExt cocSourceTableExt;

    public String getSourceTableCode() {
        return sourceTableCode;
    }

    public void setSourceTableCode(String sourceTableCode) {
        this.sourceTableCode = sourceTableCode;
    }

    public String getSourceTableName() {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
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

    public CocSourceTableExt getCocSourceTableExt() {
        return cocSourceTableExt;
    }

    public void setCocSourceTableExt(CocSourceTableExt cocSourceTableExt) {
        this.cocSourceTableExt = cocSourceTableExt;
    }
}
