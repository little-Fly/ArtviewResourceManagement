package com.grosup.ttzy.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.grosup.ttzy.beans.UserBean;
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
    
    public void init() {
        ttzyUtil = this;  
        TtzyUtil.sessionDao = this.sessionDao;
        TtzyUtil.userDao = this.userDao;
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
}
