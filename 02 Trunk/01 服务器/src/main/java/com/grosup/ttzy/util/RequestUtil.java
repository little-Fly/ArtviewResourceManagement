/**
 * 
 */
package com.grosup.ttzy.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dean
 * 
 */
public class RequestUtil {

	public static String getBaseUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		return url.delete(url.length() - uri.length(), url.length())
				.append(contextpath).toString();
	}
	
	
	public static String getCurrentUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String params = request.getQueryString();
		if (null != params) {
			url.append("?").append(params);
		}
		return url.toString();
	}

}
