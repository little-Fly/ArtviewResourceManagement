package com.grosup.ttzy.dao;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.util.GrosupException;

@Repository
public class LogDao extends AbstractDao {
    
    public void logAdd(LogBean logBean) throws GrosupException{
        this.getSession().insert("com.grosup.ttzy.log.logAdd", logBean);
    }
}
