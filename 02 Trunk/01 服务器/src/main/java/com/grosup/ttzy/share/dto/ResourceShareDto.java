package com.grosup.ttzy.share.dto;
/**
 * 
 * @author Administrator
 *
 */
public class ResourceShareDto {
	
	private String shareKey; //分享ID，用于访问和获取
	private String shareName; //分享ID，用于访问和获取
	private String resourceListJson; //分享的资源列表Json格式
	private String sendUser;		//分享人
	private String templateName;		//模板名称
	private String sendTime;			//最后一次发布时间
	private String LastTime;			//最近更新时间，用于做双校验
	public String getShareKey() {
		return shareKey;
	}
	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}
	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	public String getResourceListJson() {
		return resourceListJson;
	}
	public void setResourceListJson(String resourceListJson) {
		this.resourceListJson = resourceListJson;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getLastTime() {
		return LastTime;
	}
	public void setLastTime(String lastTime) {
		LastTime = lastTime;
	}

}
