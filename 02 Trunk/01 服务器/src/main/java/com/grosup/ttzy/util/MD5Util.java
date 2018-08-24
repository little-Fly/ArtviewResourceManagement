package com.grosup.ttzy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Util {
	
	private static Logger log=Logger.getLogger(MD5Util.class);

	public static String GetMD5Code(String srcStr) {
		String result =null;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(srcStr.getBytes("UTF-8"));
			
			byte[] byteArray = messageDigest.digest();
			
			result= toHexStr(byteArray);
			
		} catch (NoSuchAlgorithmException e) {
			log.warn(e);
		} catch (UnsupportedEncodingException e) {
			log.warn(e);
		}

		return result;
	}
	
	private static String toHexStr(byte[] byteArray)
	{
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println(GetMD5Code("abcdef"));
	}
}
