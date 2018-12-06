package com.grosup.ttzy.util;

import java.util.Collection;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class ObjectUtil {
	/**
	 * 判断一个对象是否不为空
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		Boolean checkState = Boolean.TRUE;
		try {
			Assert.notNull(obj, "对象不为null");
		} catch (IllegalArgumentException e) {
			checkState = Boolean.FALSE;
		}
		return checkState;
	}
	
	public static boolean isNull(Object obj) {
		Boolean checkState = Boolean.TRUE;
		try {
		    Assert.isNull(obj, "对象为null");
		    if (obj instanceof List) {
		        CollectionUtils.isEmpty((Collection<?>) obj);
		        return Boolean.TRUE;
		    }
			
		} catch (Exception e) {
			checkState = Boolean.FALSE;
		}
		return checkState;
	}
}
