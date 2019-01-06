package com.grosup.ttzy.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.beans.RoleBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.beans.UserRoleBean;
import com.grosup.ttzy.dao.AbstractDao;
import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.util.GrosupException;

@Repository
public class RoleImpl extends AbstractDao implements RoleDao{

    public List<UserBean> getUserListByRole(String roleKey) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<RoleBean> getAllRoles() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * 是否超管
     */
    public boolean isRoot(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsRoot", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /**
     * 是否管理员
     */
    public boolean isAdmin(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsAdmin", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /**
     * 是否为资源审核人员
     */
    public boolean isChecker(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsChecker", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /**
     * 是否普通员工
     */
    public boolean isCommon(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsCommon", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /**
     * 是否超管
     */
    public boolean isVisitor(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsVisitor", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    /**
     * 是否为资源录入人员
     */
    public boolean isWriter(long uid) throws GrosupException{
        int count = this.getSession().selectOne("com.grosup.ttzy.role.checkIsWriter", uid);
        if (count > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 批量新增用户角色
     */
    public void BatchAddUserRole(List<UserRoleBean> userRoles) throws GrosupException{
        this.getSession().insert("com.grosup.ttzy.role.BatchAddUserRole", userRoles);
    }
    
    public void BatchdelUserRole(List<UserRoleBean> userRoles) throws GrosupException{
        this.getSession().delete("com.grosup.ttzy.role.BatchdelUserRole", userRoles);
    }
    
}
