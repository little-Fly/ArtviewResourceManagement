package com.grosup.ttzy.util;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.dao.LogDao;
import com.grosup.ttzy.dao.SessionDao;
import com.grosup.ttzy.dao.UserDao;

/**
 * 天天资源圈项目通用工具类
 * @author xuelifei
 */
public class TtzyUtil {
    
    private static Logger logger = Logger.getLogger(TtzyUtil.class);
    
    private static TtzyUtil ttzyUtil;
    
    private static SessionDao sessionDao;
    
    private static UserDao userDao;
    
    private static LogDao logDao;
    
    public void init() {
        ttzyUtil = this;  
        TtzyUtil.sessionDao = this.sessionDao;
        TtzyUtil.userDao = this.userDao;
        TtzyUtil.logDao = this.logDao;
    }
    
    public void setSessionDao(SessionDao sessionDao) {
        TtzyUtil.sessionDao = sessionDao;
    }
    
    public void setUserDao(UserDao userDao) {
        TtzyUtil.userDao = userDao;
    }
    
    public void setLogDao(LogDao logDao) {
        TtzyUtil.logDao = logDao;
    }
    
    /**
     * 查询third_session记录验证登录状态
     * @param third_session
     * @return
     */
    public static boolean checkThirdSession(String third_session) {
        return sessionDao.checkThirdSession(third_session);
    }
    
    public static UserBean getUserInfo(Map<String, Object> params) {
        UserBean userBean = null;
        try {
            userBean = userDao.getUserInfo(params);
        } catch (GrosupException e) {
            logger.error("根据openId获取人员信息异常", e);
        }
        return userBean;
    }
    /**
     * 创建日志
     * @param uid
     * @param logTime
     * @param operation
     * @param remark
     * @return
     */
    public static LogBean createLog(long uid, Date logTime, String operation, String remark) {
        return new LogBean(uid, logTime, operation, remark);
    }
    
    public static void saveLog(LogBean logBean) throws GrosupException {
        try {
            logDao.logAdd(logBean);
        } catch (GrosupException e) {
            logger.error("日志记录异常", e);
            throw new GrosupException(-1 ,"日志记录异常", e);
        }
        
    }
}
