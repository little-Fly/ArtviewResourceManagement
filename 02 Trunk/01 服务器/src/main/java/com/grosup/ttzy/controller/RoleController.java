package com.grosup.ttzy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.UserRoleBean;
import com.grosup.ttzy.service.RoleService;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.ObjectUtil;

@Controller
@RequestMapping("/wx/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "/batchAddUserRole.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject BatchAddUserRole(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            
            String roleKey = request.getParameter("roleKey");
            if (ObjectUtil.isNull(roleKey)
                    || ObjectUtil.isNull(request.getParameter("uids"))) {
                result.put("code", CodeUtil.ERROR);
                result.put("msg", "request param error");
            }
            String[] uids = request.getParameter("uids").trim().split(",");
            List<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();
            for (String uid : uids) {
                UserRoleBean userRole = new UserRoleBean();
                userRole.setRoleKey(roleKey);
                userRole.setUid(Long.parseLong(uid));
                userRoles.add(userRole);
            }
            if (ObjectUtil.isNull(userRoles)) {
                result.put("code", CodeUtil.ERROR);
                result.put("msg", "param is null");
            }
            roleService.BatchAddUserRole(userRoles);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            result.put("msg", "BatchAddUserRole error");
        }
        return result;
    }
    
    @RequestMapping(path = "/batchdelUserRole.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject BatchdelUserRole(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            String roleKey = request.getParameter("roleKey");
            if (ObjectUtil.isNull(roleKey)
                    || ObjectUtil.isNull(request.getParameter("uids"))) {
                result.put("code", CodeUtil.ERROR);
                result.put("msg", "request param error");
            }
            String[] uids = request.getParameter("uids").trim().split(",");
            List<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();
            for (String uid : uids) {
                UserRoleBean userRole = new UserRoleBean();
                userRole.setRoleKey(roleKey);
                userRole.setUid(Long.parseLong(uid));
                userRoles.add(userRole);
            }
            if (ObjectUtil.isNull(userRoles)) {
                result.put("code", CodeUtil.ERROR);
                result.put("msg", "param is null");
            }
            if (ObjectUtil.isNull(userRoles)) {
                result.put("code", CodeUtil.ERROR);
                result.put("msg", "param is null");
            }
            roleService.BatchdelUserRole(userRoles);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            result.put("msg", "BatchAddUserRole error");
        }
        return result;
    }
}
