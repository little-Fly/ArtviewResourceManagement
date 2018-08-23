/**
 * 
 */
package com.grosup.ttzy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.grosup.ttzy.beans.UserBean;

/**
 * Cookie 公共读取类 写入不在此类,统一放在服务端
 * 
 * @author Dean
 * 
 */
public class SsoUtil {

	public static UserBean getUserBean(HttpServletRequest request) {
		Cookie cookieUser = getCookie(request, "SSO_TTZY");
		Cookie cookieValid = getCookie(request, "SSO_TTZY_VALID");
		if ((null == cookieUser) || (null == cookieValid)) {
			return null;
		}

		String userStr = cookieUser.getValue();
		String validStr = cookieValid.getValue();
		if (MD5Util.GetMD5Code(userStr).equalsIgnoreCase(validStr)) {
			String userJson = AESUtil.RevertAESCode(userStr);
			UserBean user = new UserBean(userJson);
			return user;
		}
		return null;
	}

	private static Cookie getCookie(HttpServletRequest request, String key) {
		if (null == request) {
			return null;
		}

		if (StringUtil.isNullOrEmpty(key)) {
			return null;
		}

		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (key.equalsIgnoreCase(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	public static void addSessionInfo(HttpServletRequest request) {
		UserBean user = getUserBean(request);
		if (null != user) {
			request.getSession().setAttribute(SsoConstant.SESSION_USER, user);
		}
	}

	public static void addSessionInfo(HttpServletRequest request, UserBean user) {
		if (null == user) {
			return;
		}
		UserBean oldUser = (UserBean) request.getSession().getAttribute(
				SsoConstant.SESSION_USER);
		if (null == oldUser) {
			request.getSession().setAttribute(SsoConstant.SESSION_USER, user);
			return;
		}
		if (user.toString().equals(oldUser.toString())) {
			return;
		}
		request.getSession().setAttribute(SsoConstant.SESSION_USER, user);
	}

	public static String getLoginRedirectUrl(HttpServletRequest request) {
		String curUrl = RequestUtil.getCurrentUrl(request);
		StringBuilder result = new StringBuilder();
		result.append(SsoConstant.SSO_URL).append("?redirect=").append(curUrl);
		return result.toString();
	}

}
