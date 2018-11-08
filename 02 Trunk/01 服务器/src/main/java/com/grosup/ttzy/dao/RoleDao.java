package com.grosup.ttzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.grosup.ttzy.beans.RoleBean;
import com.grosup.ttzy.beans.UserBean;

public interface RoleDao {
    
    public List<UserBean> getUserListByRole(@Param("roleKey") String roleKey);
    
    public List<RoleBean> getAllRoles();
}
