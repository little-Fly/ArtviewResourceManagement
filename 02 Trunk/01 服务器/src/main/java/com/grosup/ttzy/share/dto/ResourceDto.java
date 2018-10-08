package com.grosup.ttzy.share.dto;
/**
 * 
 * @author Administrator
 *
 */
public class ResourceDto {

	private String typeKey; // varchar Y 资源类型Key
	private String resourceKey; // long Y 主键，资源Id
	public String getTypeKey() {
		return typeKey;
	}
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	public String getResourceKey() {
		return resourceKey;
	}
	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}
	

}
