package com.grosup.ttzy.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.beans.SessionBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.dao.LogDao;
import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.dao.SessionDao;
import com.grosup.ttzy.dao.UserDao;

/**
 * 天天资源圈项目通用工具类
 * @author xuelifei
 */
public class TtzyUtil {
    
    private static final Logger LOGGER = Logger.getLogger(TtzyUtil.class);
    
    private static TtzyUtil ttzyUtil;
    
    private static SessionDao sessionDao;
    
    private static UserDao userDao;
    
    private static LogDao logDao;
    
    public static RoleDao roleDao;
    
    public void init() {
        ttzyUtil = this;  
        TtzyUtil.sessionDao = this.sessionDao;
        TtzyUtil.userDao = this.userDao;
        TtzyUtil.logDao = this.logDao;
        TtzyUtil.roleDao = this.roleDao;
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
    
    public void setRoleDao(RoleDao roleDao) {
        TtzyUtil.roleDao = roleDao;
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
            LOGGER.error("根据openId获取人员信息异常", e);
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
            LOGGER.error("日志记录异常", e);
            throw new GrosupException(-1 ,"日志记录异常", e);
        }
        
    }
    /**
     * 获取uid方法
     * @param request
     * @return
     */
    public static Long getUid(HttpServletRequest request) {
        Long uid = null;
        try {
            //1、从cookie获取
            Cookie cookieUser = getCookie(request, SsoConstant.SSO_GROSUP);
            Cookie cookieValid = getCookie(request, SsoConstant.SSO_GROSUP_VALID);
            if ((null == cookieUser) || (null == cookieValid)) {
              //2、wx小程序方式获取
                uid = getUidBywx(request);
                return uid;
            }
            String userStr = cookieUser.getValue();
            String validStr = cookieValid.getValue();
            if (MD5Util.GetMD5Code(userStr).equalsIgnoreCase(validStr)) {
                String userJson = AESUtil.RevertAESCode(userStr);
                UserBean user = new UserBean(userJson);
                uid = user.getUid();
            }
        } catch (Exception e) {
            LOGGER.error("get uid failed", e);
        }
        return uid;
    }
    
    private static Long getUidBywx(HttpServletRequest request) throws GrosupException {
        LOGGER.info("通过third_session获取Uid...");
        String third_session = request.getHeader("third_session");
        if (ObjectUtil.isNull(third_session)) {
            LOGGER.info("third_session is null...");
            return null;
        }
        SessionBean sessionBean = sessionDao.getOpenIdByThirdSession(third_session);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openId", sessionBean.getOpenId());
        UserBean user = userDao.getUserInfo(params);
        return user.getUid();
    }

    private static UserBean getUserBywx(HttpServletRequest request) throws GrosupException {
        LOGGER.info("通过third_session获取Uid...");
        String third_session = request.getHeader("third_session");
        if (ObjectUtil.isNull(third_session)) {
            LOGGER.info("third_session is null...");
            return null;
        }
        SessionBean sessionBean = sessionDao.getOpenIdByThirdSession(third_session);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openId", sessionBean.getOpenId());
        UserBean user = userDao.getUserInfo(params);
        return user;
    }


    /**
     * 获取uid方法
     * @param request
     * @return
     */
    public static String getUser(HttpServletRequest request) {
        UserBean user = null;
        try {
            //1、从cookie获取
            Cookie cookieUser = getCookie(request, SsoConstant.SSO_GROSUP);
            Cookie cookieValid = getCookie(request, SsoConstant.SSO_GROSUP_VALID);
            if ((null == cookieUser) || (null == cookieValid)) {
                //2、wx小程序方式获取
                user = getUserBywx(request);
                return user.getName();
            }
            String userStr = cookieUser.getValue();
            String validStr = cookieValid.getValue();
            if (MD5Util.GetMD5Code(userStr).equalsIgnoreCase(validStr)) {
                String userJson = AESUtil.RevertAESCode(userStr);
                user = new UserBean(userJson);
            }
        } catch (Exception e) {
            LOGGER.error("get user failed", e);
        }
        LOGGER.info("user value is " + JSONObject.fromObject(user));
        return user.getName();
    }

    private static Cookie getCookie(HttpServletRequest request, String key) {
        if (null == request) {
            return null;
        }

        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (key.equalsIgnoreCase(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}