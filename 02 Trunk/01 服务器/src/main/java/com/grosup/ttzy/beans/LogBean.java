package com.grosup.ttzy.beans;

import java.util.Date;

public class LogBean {
    
    private long id;
    
    private long uid;
    
    private Date logTime;
    
    private String operation;
    
    public LogBean(long uid, Date logTime, String operation, String remark) {
        super();
        this.uid = uid;
        this.logTime = logTime;
        this.operation = operation;
        this.remark = remark;
    }

    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
