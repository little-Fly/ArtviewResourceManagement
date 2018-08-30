package com.grosup.ttzy.controller;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.service.UserService;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;

@RequestMapping("/wx/user")
public class UserController {
    
    private static Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public JSONObject studentAdd(UserBean user) {
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
}
