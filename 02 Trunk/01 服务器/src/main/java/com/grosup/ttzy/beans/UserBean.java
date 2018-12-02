package com.grosup.ttzy.beans;

import java.util.Date;
import java.util.List;

import com.grosup.ttzy.util.JSONBuilder;

import net.sf.json.JSONObject;

public class UserBean {
    
    public UserBean() {
        
    }

    public UserBean(String str) {
        JSONObject obj = JSONObject.fromObject(str);
        this.uid = obj.optLong("uid");
        this.openId = obj.optString("openId");
        this.nickName = obj.optString("nickName");
    }
    
    private long uid;
    
    private String openId;
    
    private String unionId;
    
    private String nickName;
    
    private Date createTime;
    
    private Date lastTime;
    
    private int status;
    
    private List<RoleBean> roles;
    
    private long lastValidTime = System.currentTimeMillis();
    
    public long getLastValidTime() {
        return lastValidTime;
    }

    public void setLastValidTime(long lastValidTime) {
        this.lastValidTime = lastValidTime;
    }


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

    public List<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBean> roles) {
        this.roles = roles;
    }
    
    
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String toString() {
        return JSONBuilder.build().put("uid", this.uid)
                .put("openId", this.openId).put("nickName", this.nickName)
                .put("createTime", this.createTime).put("lastTime", this.lastTime)
                .put("status", this.status)
                .put("lastValidTime", this.lastValidTime).put("roles", this.roles).toString();
    }

}
