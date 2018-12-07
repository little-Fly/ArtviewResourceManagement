package com.grosup.ttzy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.GrosupException;

@Repository
public class UserDao extends AbstractDao {
    
    public UserBean getUserInfo(Map<String, Object> params) throws GrosupException{
        return this.getSession().selectOne("com.grosup.ttzy.user.getUserInfo", params);
    }
    
    public long userAdd(UserBean userBean) throws GrosupException{
        this.getSession().insert("com.grosup.ttzy.user.userAdd", userBean);
        return userBean.getUid();
    }
    /**
     * 查询待审核人员
     */
    public List<UserBean> queryUnCheckedUser() throws GrosupException{
        return this.getSession().selectList("com.grosup.ttzy.user.queryUnCheckedUser");
    }
    /**
     * 人员审核
     * @param param
     * @throws GrosupException
     */
    public void changeUserStatus(Map<String, Object> param) throws GrosupException{
       this.getSession().update("com.grosup.ttzy.user.changeUserStatus", param); 
    }
    /**
     * 查询所有非超管人员信息
     * @return
     */
    public List<UserBean> queryUsers() throws GrosupException {
        return this.getSession().selectList("com.grosup.ttzy.user.queryUsers");
    }
    /**
     * 查询角色下所有人员信息
     * @param roleKey
     * @return
     */
    public List<UserBean> getUsersByRole(String roleKey) throws GrosupException{
        return this.getSession().selectList("com.grosup.ttzy.user.getUsersByRole", roleKey);
    }
    
    /**
     * 查询未在该角色下所有人员信息
     * @param roleKey
     * @return
     */
    public List<UserBean> getUnUsersByRole(String roleKey) throws GrosupException{
        return this.getSession().selectList("com.grosup.ttzy.user.getUnUsersByRole", roleKey);
    }
}
