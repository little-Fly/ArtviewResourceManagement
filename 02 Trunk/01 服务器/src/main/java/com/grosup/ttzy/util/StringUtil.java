/**
 * 
 */
package com.grosup.ttzy.util;


/**
 * @author JohnPake
 * 
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空，为空返回true，不为空返回false
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String input) {
		if (null == input) {
			return true;
		}

		if (input.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 对应javascript的escape()函数, 加码后的串可直接使用javascript的unescape()进行解码
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 对应javascript的unescape()函数, 可对javascript的escape()进行解码
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	// public static void main(String[] args)
	// {
	// BigDecimal a=new BigDecimal("1111141158920");
	// a.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(10));
	// System.out.println(a.toString());
	//
	// a=a.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(10));
	//
	//
	// BigDecimal b=new BigDecimal("2222282317840");
	//
	// BigDecimal c=new BigDecimal("2222282317839");
	//
	// BigDecimal d=new BigDecimal("2222282317841");
	// System.out.println(a.toString());
	// System.out.println(c.compareTo(a));
	// System.out.println(b.compareTo(a));
	// System.out.println(d.compareTo(a));
	//
	// System.out.println(d.toString());
	//
	// BigDecimal e=d.subtract(b);
	// System.out.println(e.toString());
	// }
}
