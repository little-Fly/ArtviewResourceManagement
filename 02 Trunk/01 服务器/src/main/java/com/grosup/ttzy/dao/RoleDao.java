package com.grosup.ttzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.grosup.ttzy.beans.RoleBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.beans.UserRoleBean;
import com.grosup.ttzy.util.GrosupException;

public interface RoleDao {
    
    public List<UserBean> getUserListByRole(@Param("roleKey") String roleKey);
    
    public List<RoleBean> getAllRoles();
    /**
     * 判断是否为管理员
     * @param uid
     * @return
     */
    public boolean isAdmin(@Param("uid") long uid) throws GrosupException;
    
    public boolean isChecker(@Param("uid") long uid) throws GrosupException;
    
    public boolean isWriter(@Param("uid") long uid) throws GrosupException;
    
    public boolean isRoot(@Param("uid") long uid) throws GrosupException;
    
    public boolean isCommon(@Param("uid") long uid) throws GrosupException;
    
    public boolean isVisitor(@Param("uid") long uid) throws GrosupException;
    
    public void BatchAddUserRole(@Param("userRoles") List<UserRoleBean> userRoles) throws GrosupException;
    
    public void BatchdelUserRole(@Param("userRoles") List<UserRoleBean> userRoles) throws GrosupException;
}
