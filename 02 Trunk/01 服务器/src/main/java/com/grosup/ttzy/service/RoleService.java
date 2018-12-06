package com.grosup.ttzy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.beans.UserRoleBean;
import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.util.GrosupException;

@Service
public class RoleService {
    
    @Autowired
    private RoleDao roleDao;
    
    

    /**
     * 批量新增用户角色
     */
    public void BatchAddUserRole(List<UserRoleBean> userRoles) throws GrosupException{
        roleDao.BatchAddUserRole(userRoles);
    }
    
    /**
     * 批量新增用户角色
     */
    public void BatchdelUserRole(List<UserRoleBean> userRoles) throws GrosupException{
        roleDao.BatchdelUserRole(userRoles);
    }
}
