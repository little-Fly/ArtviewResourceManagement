package com.grosup.ttzy.beans;

import net.sf.json.JSONObject;

public class UserBean {
	
	public UserBean(String str) {
		JSONObject obj = JSONObject.fromObject(str);
		this.userName = obj.optString("userName");
		this.age = obj.optInt("age");
	}
	
	private String userName;
	
	private int age;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
