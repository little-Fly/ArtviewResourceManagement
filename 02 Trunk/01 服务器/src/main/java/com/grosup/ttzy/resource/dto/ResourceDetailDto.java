package com.grosup.ttzy.resource.dto;

/**
 * 
 * @author Administrator
 *
 */
public class ResourceDetailDto {

	private String resourceKey; // long Y 主键，资源Id
	private String attrKey; // varchar Y 主键，属性Key
	private String typeKey; // varchar Y 资源类型Key
	private String attrName; // varchar Y 属性名
	private String attrLevel; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
	private String attrType; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
	private String attrValue; // varchar Y 属性值
	private String attrState; // varchar Y 状态
	private String attrLastState; // varchar Y 状态
	private String approvalUser;// 審批人
	private String approvalMess;// 審批意見

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

	public String getAttrLevel() {
		return attrLevel;
	}

	public void setAttrLevel(String attrLevel) {
		this.attrLevel = attrLevel;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrState() {
		return attrState;
	}

	public String getAttrLastState() {
		return attrLastState;
	}

	public void setAttrState(String attrState) {
		if (this.attrState != attrState) {
			attrLastState = this.attrState;
			this.attrState = attrState;
		}
	}

	public String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public String getApprovalMess() {
		return approvalMess;
	}

	public void setApprovalMess(String approvalMess) {
		this.approvalMess = approvalMess;
	}

}
