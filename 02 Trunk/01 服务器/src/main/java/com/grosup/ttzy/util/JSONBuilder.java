/**
 * 
 */
package com.grosup.ttzy.util;

import net.sf.json.JSONObject;

/**
 * @author Dean
 *
 */
public class JSONBuilder {
	
	private JSONObject obj;
	
	private JSONBuilder()
	{
		obj=new JSONObject();
	}
	
	public static JSONBuilder build()
	{
		return new JSONBuilder();
	}
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @throws JSONException
	 * @see org.json.JSONObject#put(java.lang.String, boolean)
	 */
	public JSONBuilder put(String key, Object value)  {
	 this.obj.put(key, value);
	 return this;
	}
	
	public String toString()
	{
		return this.obj.toString();
	}
}
