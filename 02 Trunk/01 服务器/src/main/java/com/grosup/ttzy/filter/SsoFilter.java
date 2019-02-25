package com.grosup.ttzy.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.SsoConstant;
import com.grosup.ttzy.util.SsoUtil;

/**
 * @author Dean
 * 
 */
public abstract class SsoFilter implements Filter {

	private static final Logger log = Logger.getLogger(SsoFilter.class);

	private String[] excludeUrls;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludeParam = filterConfig.getInitParameter("excludeurls");
		this.excludeUrls = excludeParam.split(",");
//		SsoConstant.SSO_URL = SsoConfig.getSsoAppUrl() + "/index.action";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public final void doFilter(ServletRequest sRequest,
			ServletResponse sResponse, FilterChain chain) throws IOException,
			ServletException {
		log.info("SSO: Do Befor Fiter.");

		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		this.doBeforFilter(request, response);
		// System.out.println(RequestUtil.getCurrentUrl(request));

		log.info("request url is," + request.getRequestURI());
		// 如果URL属于排除之列，直接执行嵌套Filter和Filter后期操作，并且返回。
		if (this.isExcludeUrl(request)) {
			chain.doFilter(request, response);
			if (!response.isCommitted()) {
				this.doAfterFilter(request, response);
			}
			return;
		}

		if (this.isLogin(request, response)) {
			this.doAfterLogin(request, response);
			if (!response.isCommitted()) {
				chain.doFilter(request, response);
			}
			if (!response.isCommitted()) {
				this.doAfterFilter(request, response);
			}
		} else {
			// 跳转登录页面
			redirectLogin(request, response);
			return;
		}

	}

	public abstract void doBeforFilter(HttpServletRequest request,
			HttpServletResponse response);

	public abstract void doAfterFilter(HttpServletRequest request,
			HttpServletResponse response);

	public abstract void doAfterLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException;

	protected void redirectLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 跳转登录页面
		String redirectUrl = SsoUtil.getLoginRedirectUrl(request);
		response.sendRedirect(redirectUrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	private boolean isExcludeUrl(HttpServletRequest request) {
		String url = request.getRequestURI();
		if (this.excludeUrls == null) {
			return false;
		}
		for (int i = 0; i < this.excludeUrls.length; i++) {
			String excludeUrl = this.excludeUrls[i];
			if (url.matches(excludeUrl.replaceAll("\\*", "\\.\\*"))) {
				return true;
			}
		}
		return false;
	}

	private boolean isLogin(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("judge is login");
		UserBean user = SsoUtil.getUserBean(request);
		if (null == user) {
//			// 用户状态 0：表示正常 9：表示禁用 1：表示未激活
//			if (!UserStatus.Normal.value().equals(user.getStatus())) {
//				return false;
//			}

			return false;
		}

		long curTime = System.currentTimeMillis();
		log.info("user.getLastValidTime()=" + user.getLastValidTime());
		log.info("curTime=" + curTime);
		// 在服务器验证有效期内
		if ((curTime - user.getLastValidTime()) <=
				SsoConstant.REVALID_TERVAL_TIME) {
			log.info("1111");
			SsoUtil.addSessionInfo(request, user);
			return true;
		}
		return false;
	}
}
