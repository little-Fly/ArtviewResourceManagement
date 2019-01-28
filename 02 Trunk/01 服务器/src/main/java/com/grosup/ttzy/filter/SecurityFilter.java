package com.grosup.ttzy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.util.SpringUtil;
import com.grosup.ttzy.util.TtzyUtil;

public class SecurityFilter implements Filter {
    
    private static final Logger logger = Logger.getLogger(SecurityFilter.class);
    
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Begin init...");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            logger.info("Begin SecurityFilter...");
            RoleDao roleDao = SpringUtil.getBean(RoleDao.class);
            HttpServletRequest sRequest = (HttpServletRequest) request;
            HttpServletResponse sResponse = (HttpServletResponse) response;
            String url = sRequest.getRequestURI();
            logger.info("url =" + url);
            //角色审核接口,管理员与超级管理员可访问
            if (url.matches("/wx/user/queryUnchecked.do")) {
                long uid = TtzyUtil.getUid(sRequest);
                logger.info("uid = " + uid);
                logger.info("roleDao = " + roleDao);
                if (roleDao.isAdmin(uid) || roleDao.isRoot(uid)) {
                    chain.doFilter(sRequest, sResponse);
                } else {
                    sResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无访问权限");
                }
            } else {
                logger.info("权限校验之外...");
                chain.doFilter(sRequest, sResponse);
            }
        } catch (Exception e) {
            logger.error("权限拦截异常", e);
        }
        
    }

    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}