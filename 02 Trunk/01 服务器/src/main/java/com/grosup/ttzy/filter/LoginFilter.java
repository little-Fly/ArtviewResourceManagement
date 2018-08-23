/**
 * 
 */
package com.grosup.ttzy.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.SsoConstant;
import com.grosup.ttzy.util.SsoUtil;


/**
 * @author Dean
 * 
 */
public class LoginFilter extends SsoFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.grosup.sso.filter.SsoFilter#doBeforFilter(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doBeforFilter(HttpServletRequest request,
			HttpServletResponse response) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.grosup.sso.filter.SsoFilter#doAfterFilter(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doAfterFilter(HttpServletRequest request,
			HttpServletResponse response) {
	}

	public void doAfterLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 判断用户，并刷新用户角色基本信息表
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session
				.getAttribute(SsoConstant.SESSION_USER);
		
		UserBean cUser=SsoUtil.getUserBean(request);
		if ((null == user)||(!user.equals(cUser))){
			user = SsoUtil.getUserBean(request);
			SsoUtil.addSessionInfo(request);
		}
	}
}
