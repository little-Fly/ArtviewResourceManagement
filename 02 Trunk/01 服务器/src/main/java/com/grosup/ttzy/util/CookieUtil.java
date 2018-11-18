/**
 * 
 */
package com.grosup.ttzy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grosup.ttzy.beans.UserBean;


/**
 * @author Dean
 *
 */
public final class CookieUtil {

	public static void clearCookies(HttpServletRequest request,
			HttpServletResponse response) {
		if (null == request) {
			return;
		}
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain(".grosup.com");
			response.addCookie(cookie);
		}

		request.getSession().invalidate();
	}
	
	public static void setCookies(HttpServletResponse response,UserBean user) {
		String code = user.toString();
		String userCookieStr = AESUtil.GetAESCode(code);
		String userValidStr = MD5Util.GetMD5Code(userCookieStr);

		// 1.设置用户信息生成MD5码，有效期无期限，用于自动登录时验证或用户Cookie有效期免验证
		// SSO_GROSUP 此处为用户信息编码后的值，可解码生成UserBean对象
		// 将以上需要写入cookie的用户信息通过AES编码后写入Cookie，用于前台解码后获取用户信息
		Cookie cookie_Grosup = new Cookie(SsoConstant.SSO_GROSUP, userCookieStr);
		cookie_Grosup.setMaxAge(SsoConstant.COOKIE_VALID_TIME);
		cookie_Grosup.setPath("/");
//		cookie_Grosup.setDomain(".grosup.com");
		response.addCookie(cookie_Grosup);

		// 2.SSO_GROSUP_VALID;
		// 此处为UserBean经过MD5编码后处理，不可解码，用于验证SSO_GROSUP的一致性。
		Cookie cookie_Valid = new Cookie(SsoConstant.SSO_GROSUP_VALID,
				userValidStr);
		cookie_Valid.setMaxAge(SsoConstant.COOKIE_VALID_TIME);
		cookie_Valid.setPath("/");
//		cookie_Valid.setDomain(".grosup.com");
		response.addCookie(cookie_Valid);
	}
	
	public static Cookie getCookie(HttpServletRequest request,String key) {
		if (StringUtil.isNullOrEmpty(key)) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (key.equalsIgnoreCase(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

}
