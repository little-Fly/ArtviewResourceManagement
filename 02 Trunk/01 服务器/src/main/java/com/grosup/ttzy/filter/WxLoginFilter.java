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

import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

/**
 * @author xuelifei
 *
 */
public class WxLoginFilter implements Filter {
    
    private static final Logger log = Logger.getLogger(WxLoginFilter.class);
    
    private String[] excludeUrls;

    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化不经过拦截的参数
        String excludeParam = filterConfig.getInitParameter("excludeurls");
        this.excludeUrls = excludeParam.split(",");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 使用third_session判断用户登录态
        HttpServletRequest sRequest = (HttpServletRequest) request;
        HttpServletResponse sResponse = (HttpServletResponse) response;
        //根据third_session判断用户是否登录
        String third_session = sRequest.getHeader("third_session");
        if (this.isExcludeUrl(sRequest)) {
            log.error("排除之外");
            chain.doFilter(sRequest, sResponse);
            return;
        }
        if (StringUtil.isNullOrEmpty(third_session)) {
            //需要重新授权
            sResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "授权不正确");
        } else {
            log.info("用third_session查询sessionKey，验证是否登录");
            //用third_session查询sessionKey，验证是否授权
            boolean login = TtzyUtil.checkThirdSession(third_session);
            if (login) {
                chain.doFilter(sRequest, sResponse);
            } else {
                sResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "授权不正确");
            }
        }
    }

    public void destroy() {

    }
    
    private boolean isExcludeUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (this.excludeUrls == null) {
            return false;
        }
        for (int i = 0; i < this.excludeUrls.length; i++) {
            String excludeUrl = this.excludeUrls[i];
            //本地为resource
            if (url.matches("/resource/wx/login.do") ||url.matches("/wx/login.do")) {
                return true;
            }
            if (url.matches(excludeUrl.replaceAll("\\*", "\\.\\*"))) {
                log.info("匹配到了");
                return true;
            }
        }
        return false;
    }
}
