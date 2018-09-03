package com.grosup.ttzy.beans;

import java.util.Date;

import net.sf.json.JSONObject;

public class UserBean {
    
    public UserBean(String str) {
        JSONObject obj = JSONObject.fromObject(str);
        this.uid = obj.optLong("uid");
        this.openId = obj.optString("openId");
        this.nickName = obj.optString("nickName");
        this.roleKey = obj.optString("roleKey");
    }
    
    private long uid;
    
    private String openId;
    
    private String nickName;
    
    private String roleKey;
    
    private Date createTime;
    
    private Date lastTime;
    
    private int status;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
