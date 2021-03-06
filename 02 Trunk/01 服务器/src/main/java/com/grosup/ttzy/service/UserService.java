package com.grosup.ttzy.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.beans.ReportBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.dao.LogDao;
import com.grosup.ttzy.dao.UserDao;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.TtzyUtil;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private LogDao logDao;
    
    public UserBean getUserInfo(Map<String, Object> params) throws GrosupException {
        return userDao.getUserInfo(params);
    } 
    
    public long userAdd(UserBean userBean) throws GrosupException {
        //注册时间
        Date createTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        userBean.setCreateTime(createTime);
        userBean.setLastTime(createTime);
        long uid = userDao.userAdd(userBean);
        //记录日志
        String remark = userBean.getNickName()+ "于" + sdf.format(createTime) + "申请注册";
        LogBean logBean = TtzyUtil.createLog(uid, createTime, "注册", remark);
        TtzyUtil.saveLog(logBean);
        return uid;
    }

    public void userUpdate(UserBean userBean) throws GrosupException{
        userDao.userUpdate(userBean);
    }

    public void userDel(String openId) throws GrosupException{
        userDao.userDel(openId);
    }
    
    /**
     * 查询待审核人员
     */
    public List<UserBean> queryUnCheckedUser() throws GrosupException {
        return userDao.queryUnCheckedUser();
    }
    /**
     * 人员审核
     * @throws GrosupException
     */
    public void changeUserStatus(long uid, String nickName, int status, String refuse) throws GrosupException{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("uid", uid);
        param.put("status", status);
        userDao.changeUserStatus(param);
        //记录日志
        Date optTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
        String checkStr = "";
        if (status == 1) {
            checkStr = "通过";
        } else {
            checkStr = "拒绝,理由：" + refuse;
        }
        StringBuilder report = new StringBuilder();
        report.append("你的注册申请被管理员");
        report.append(checkStr);
        ReportBean reportBean = new ReportBean();
        reportBean.setUid(uid);
        reportBean.setReport(String.valueOf(report));
        reportBean.setDate(dateSdf.format(optTime));
        reportBean.setStatus(0);
        logDao.reportAdd(reportBean);
        
        String remark = "超级管理员于" + sdf.format(optTime) + "审核人员" + nickName + checkStr;
        LogBean logBean = TtzyUtil.createLog(uid, optTime, "注册", remark);
        TtzyUtil.saveLog(logBean);
    }
    
    /**
     * 查询所有非超管人员信息
     * @return
     */
    public List<UserBean> queryUsers() throws GrosupException {
        return userDao.queryUsers();
    }
    
    /**
     * 查询角色下所有人员信息
     * @param roleKey
     * @return
     */
    public List<UserBean> getUsersByRole(String roleKey) throws GrosupException{
        return userDao.getUsersByRole(roleKey);
    }
    
    /**
     * 查询未在该角色下所有人员信息
     * @param roleKey
     * @return
     */
    public List<UserBean> getUnUsersByRole(String roleKey) throws GrosupException{
        return userDao.getUnUsersByRole(roleKey);
    }
}