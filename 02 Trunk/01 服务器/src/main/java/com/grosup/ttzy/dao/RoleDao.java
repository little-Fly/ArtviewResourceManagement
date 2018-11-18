package com.grosup.ttzy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.grosup.ttzy.beans.RoleBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.GrosupException;

@Repository
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
    
}
