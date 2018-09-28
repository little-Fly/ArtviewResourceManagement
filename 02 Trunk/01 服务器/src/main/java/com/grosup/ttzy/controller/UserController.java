package com.grosup.ttzy.controller;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.service.UserService;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.ObjectUtil;

@RequestMapping("/wx/user")
@Controller
public class UserController {
    
    private static Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    @ResponseBody
    public JSONObject userAdd(@RequestBody UserBean user) {
        JSONObject result = new JSONObject();
        try {
            userService.userAdd(user);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            logger.error("人员注册异常", e);
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
            logger.error("查询待审核人员异常", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/changeUserStatus")
    @ResponseBody
    public JSONObject queryUnCheckedUser(@RequestParam long uid, @RequestParam String nickName, @RequestParam int status, @RequestParam String refuse) {
        JSONObject result = new JSONObject();
        try {
            userService.changeUserStatus(uid, nickName, status, refuse);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            logger.error("审核人员异常", e);
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
            } 
            else {
                for (UserBean user : users) {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("uid", user.getUid());
                    userInfo.put("nickName", user.getNickName());
                    userInfo.put("roles", JSONArray.fromObject(user.getRoles()));
                    data.add(userInfo);
                }
                result.put("code", CodeUtil.SUCCESS);
                result.put("data", data);
            }
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            logger.error("查询人员异常", e);
        }
        return result;
    }
}
