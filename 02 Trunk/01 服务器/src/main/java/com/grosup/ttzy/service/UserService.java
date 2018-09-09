package com.grosup.ttzy.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.dao.UserDao;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.TtzyUtil;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public UserBean getUserInfo(Map<String, Object> params) throws GrosupException {
        return userDao.getUserInfo(params);
    } 
    
    public void userAdd(UserBean userBean) throws GrosupException {
        //注册时间
        Date createTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        userBean.setCreateTime(createTime);
        userBean.setLastTime(createTime);
        long uid = userDao.userAdd(userBean);
        //记录日志
        String remark = userBean.getNickName()+ "于" + sdf.format(createTime) + "注册成功";
        LogBean logBean = TtzyUtil.createLog(uid, createTime, "注册", remark);
        TtzyUtil.saveLog(logBean);
    }
}
