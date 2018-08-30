package com.grosup.ttzy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.dao.UserDao;
import com.grosup.ttzy.util.GrosupException;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public UserBean getUserInfo(Map<String, Object> params) throws GrosupException {
        return userDao.getUserInfo(params);
    } 
    
    public void userAdd(UserBean userBean) throws GrosupException {
        userDao.userAdd(userBean);
    }
}
