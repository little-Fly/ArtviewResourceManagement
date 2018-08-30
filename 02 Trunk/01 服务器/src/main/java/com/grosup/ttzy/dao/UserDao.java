package com.grosup.ttzy.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.GrosupException;

@Repository
public class UserDao extends AbstractDao {
    
    public UserBean getUserInfo(Map<String, Object> params) throws GrosupException{
        return this.getSession().selectOne("com.grosup.ttzy.user.getUserInfo", params);
    }
    
    public void userAdd(UserBean userBean) throws GrosupException{
        this.getSession().insert("com.grosup.ttzy.user.userAdd", userBean);
    }
}
