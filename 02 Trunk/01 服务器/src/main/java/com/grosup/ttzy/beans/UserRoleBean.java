package com.grosup.ttzy.beans;

public class UserRoleBean {
        
    public UserRoleBean() {

    }

    public UserRoleBean(long uid, String roleKey) {
        this.uid = uid;
        this.roleKey = roleKey;
    }
    
    private long id;
    
    private long uid;
    
    private String roleKey;

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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
    
    
}
