/**
 * 
 */
package com.grosup.ttzy.util;

/**
 * @author Dean
 * 
 */
public class SsoConstant {

	public static final String SSO_GROSUP = "SSO_TTZY";
	
	public static final String SSO_GROSUP_VALID = "SSO_TTZY_VALID";
	
	public static final String SESSION_USER = "SESSION_USER";
	
	public static final String ATTRIBUTE_USER = "ATTRIBUTE_USER";
	
	/**
	 * 间隔30分钟重新刷新时间，防止过期超时。以毫秒为单位。
	 */
	public static final long REVALID_TERVAL_TIME = 1000 * 60 * 30;
	
	/**
	 * Cookie有效期 以秒为单位
	 */
	public static int COOKIE_VALID_TIME = 60 * 60 * 12;
	
	public static String SSO_URL="https://www.debug.group/";
	
	public static final String DEFAULT_REDIRECT = "https://www.debug.group//index.html#/";

}
