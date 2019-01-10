package com.grosup.ttzy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.SessionBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.beans.UserRoleBean;
import com.grosup.ttzy.impl.RoleImpl;
import com.grosup.ttzy.service.RoleService;
import com.grosup.ttzy.service.SessionService;
import com.grosup.ttzy.service.UserService;
import com.grosup.ttzy.util.AesCbcUtil;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.ObjectUtil;

@RequestMapping("/wx/user")
@Controller
public class UserController {
    
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleImpl roleImpl;
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    @ResponseBody
    @Transactional
    public JSONObject userAdd(HttpServletRequest request) {
        LOGGER.info("Begin user add...");
        JSONObject result = new JSONObject();
        try {
            String third_session = request.getHeader("third_session");
            String nickName = request.getParameter("nickName");
            String gender = request.getParameter("gender");
            String reason = request.getParameter("reason");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String iv = request.getParameter("iv");
            String encryptedData = request.getParameter("encryptedData");
            UserBean user = new UserBean();
            user.setNickName(nickName);
            user.setGender(Integer.parseInt(gender));
            user.setPhone(phone);
            user.setReason(reason);
            user.setName(name);
            SessionBean sessionBean = sessionService.getOpenIdByThirdSession(third_session);
            String ret = AesCbcUtil.decrypt(encryptedData, sessionBean.getSession_key(), iv,
                    "UTF-8");
            if (ObjectUtil.isNull(ret)) {
                LOGGER.error("解密用户信息失败....");
                throw new Exception();
            }
            //注册之前先删除
            userService.userDel(sessionBean.getOpenId());

            JSONObject userInfoJSON = JSONObject.fromObject(ret);
            LOGGER.info("ret value = " + userInfoJSON.toString());
            String unionId = (String) userInfoJSON.get("unionId");
            String openId = (String) userInfoJSON.get("openId");
            user.setUnionId(unionId);
            user.setOpenId(openId);
            LOGGER.info("user value = " + user.toString());
            long uid = userService.userAdd(user);
            //设置角色为游客
            List<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();
            UserRoleBean userRoleBean = new UserRoleBean();
            userRoleBean.setUid(uid);
            userRoleBean.setRoleKey("visitor");
            userRoles.add(userRoleBean);
            roleService.BatchAddUserRole(userRoles);
            
            result.put("code", CodeUtil.SUCCESS);
        } catch (Exception e) {
            result.put("code", CodeUtil.ERROR);
            result.put("message", "系统错误");
            LOGGER.error("人员注册异常", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/queryUnchecked")
    @ResponseBody
    public JSONObject queryUnCheckedUser() {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            List<UserBean> uncheckUsers = userService.queryUnCheckedUser();
            if (ObjectUtil.isNull(uncheckUsers) || uncheckUsers.size() == 0) {
                result.put("code", CodeUtil.NODATA);
            } else {
                for (UserBean userBean : uncheckUsers) {
                    JSONObject user = new JSONObject();
                    user.put("nickName", userBean.getNickName());
                    user.put("uid", userBean.getUid());
                    data.add(user);
                }
                result.put("code", CodeUtil.SUCCESS);
                result.put("data", data);
            }
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("查询待审核人员异常", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/changeUserStatus")
    @ResponseBody
    public JSONObject changeUserStatus(@RequestParam long uid, @RequestParam String nickName, @RequestParam int status, @RequestParam String refuse) {
        JSONObject result = new JSONObject();
        try {
            userService.changeUserStatus(uid, nickName, status, refuse);
            if (status == 1) {
                List<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();
                UserRoleBean userRoleBean = new UserRoleBean();
                userRoleBean.setUid(uid);
                userRoleBean.setRoleKey("visitor");
                userRoles.add(userRoleBean);
                roleService.BatchdelUserRole(userRoles);
            }
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("审核人员异常", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/queryUsers")
    @ResponseBody
    public JSONObject queryUsers() {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            List<UserBean> users = userService.queryUsers();
            if (ObjectUtil.isNull(users) || users.size() == 0) {
                result.put("code", CodeUtil.NODATA);
                return result;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                for (UserBean user : users) {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("uid", user.getUid());
                    userInfo.put("nickName", user.getNickName());
                    userInfo.put("name", user.getName());
                    userInfo.put("phone", user.getPhone());
                    userInfo.put("gender", user.getGender() == 1? "男":"女");
                    userInfo.put("reason", user.getReason());
                    userInfo.put("roles", JSONArray.fromObject(user.getRoles()));
                    userInfo.put("createTime", sdf.format(user.getCreateTime()));
                    switch (user.getStatus()) {
                        case 0:
                            userInfo.put("userStatus", "待审核");
                            break;
                               
                        case 1:
                            userInfo.put("userStatus", "已审核");
                            break;
                        case 2:
                            userInfo.put("userStatus", "被拒绝");
                            break;    
                        default:
                            break;
                    }
                    data.add(userInfo);
                }
                result.put("code", CodeUtil.SUCCESS);
                result.put("data", data);
            }
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("查询人员异常", e);
        }
        return result;
    }
    /**
     * 查询角色下所有人员信息
     * @param roleKey
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUsersByRole")
    @ResponseBody
    public JSONObject getUsersByRole(@RequestParam String roleKey) {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            List<UserBean> users = userService.getUsersByRole(roleKey);
            if (ObjectUtil.isNull(users) || users.size() == 0) {
                result.put("code", CodeUtil.NODATA);
                return result;
            } 
            for (UserBean user : users) {
                JSONObject userInfo = new JSONObject();
                userInfo.put("uid", user.getUid());
                userInfo.put("nickName", user.getNickName());
                data.add(userInfo);
            }
            result.put("code", CodeUtil.SUCCESS);
            result.put("data", data);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("查询人员异常", e);
        }
        return result;
    }
    
    /**
     * 查询未在该角色下所有人员信息
     * @param roleKey
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUnUsersByRole")
    @ResponseBody
    public JSONObject getUnUsersByRole(@RequestParam String roleKey) {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            List<UserBean> users = userService.getUnUsersByRole(roleKey);
            if (ObjectUtil.isNull(users) || users.size() == 0) {
                result.put("code", CodeUtil.NODATA);
                return result;
            } 
            for (UserBean user : users) {
                JSONObject userInfo = new JSONObject();
                userInfo.put("uid", user.getUid());
                userInfo.put("nickName", user.getNickName());
                userInfo.put("name", user.getName());
                data.add(userInfo);
            }
            result.put("code", CodeUtil.SUCCESS);
            result.put("data", data);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("查询人员异常", e);
        }
        return result;
    }
    
    /**
     * 清空权限
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/clear.do")
    @ResponseBody
    public JSONObject clearUser(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            LOGGER.info("uid = " + request.getParameter("uid") + ", name = " + request.getParameter("name"));
            Long uid = Long.parseLong(request.getParameter("uid"));
            String name = request.getParameter("name");
            List<UserRoleBean> users = new ArrayList<UserRoleBean>();
            users.add(new UserRoleBean(uid, "root"));
            users.add(new UserRoleBean(uid, "admin"));
            users.add(new UserRoleBean(uid, "checker"));
            users.add(new UserRoleBean(uid, "writer"));
            users.add(new UserRoleBean(uid, "common"));
            roleImpl.BatchdelUserRole(users);
            
            List<UserRoleBean> visitorUser = new ArrayList<UserRoleBean>();
            visitorUser.add(new UserRoleBean(uid, "visitor"));
            roleImpl.BatchAddUserRole(visitorUser);
            userService.changeUserStatus(uid, name, 2, "人员离职");
            
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            LOGGER.error("清空权限异常", e);
        }
        return result;
    }
}