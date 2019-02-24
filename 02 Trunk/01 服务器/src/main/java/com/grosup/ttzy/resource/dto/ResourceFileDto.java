package com.grosup.ttzy.resource.dto;

public class ResourceFileDto {

	private String fileKey; // varchar Y 主键，资源Id
	private String resourceKey; // long Y 资源Id
	private String attrKey; // varchar Y 属性Key
	private String typeKey; // varchar Y 资源类型Key
	private String attrName; // varchar Y 属性名
	private String directory; // varchar Y 存储的目录
	private String relativePath; // varchar 相对路径
	private String lastmodifytime; // long Y 最后修改时间
	public String getResourceKey() {
		return resourceKey;
	}
	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}
	public String getAttrKey() {
		return attrKey;
	}
	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}
	public String getTypeKey() {
		return typeKey;
	}
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(String lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
}
