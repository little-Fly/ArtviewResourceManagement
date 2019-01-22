package com.grosup.ttzy.share.dto;

public class ResourceShareTempDto {
	
	private String tempKey; //模板ID
	private String tempName; //模板名稱
	private String tempFilePath; //模板filepath
	public String getTempKey() {
		return tempKey;
	}
	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTempFilePath() {
		return tempFilePath;
	}
	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}
}
