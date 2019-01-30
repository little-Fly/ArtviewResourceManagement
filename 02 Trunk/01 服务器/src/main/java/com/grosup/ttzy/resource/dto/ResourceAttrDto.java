package com.grosup.ttzy.resource.dto;

/**
 * 
 * @author Administrator
 *	 ��Դ���Ա�
 */
public class ResourceAttrDto {

	private String attrKey; // varchar Y 主键，属性Key
	private String typeKey; // varchar Y 资源类型Key
	private int attrlen; // varchar Y 资源长度
	private float attrPosition = 0; // varchar Y 资源长位置
	private String attrName; // varchar Y 属性名
	private String attrLevel; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
	private String attrType; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
	private String remark; // varchar N 备注ע

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

	public int getAttrlen() {
		return attrlen;
	}

	public void setAttrlen(int attrlen) {
		this.attrlen = attrlen;
	}
	
	public float getAttrPosition() {
		return attrPosition;
	}

	public void setAttrPosition(float attrPosition) {
		this.attrPosition = attrPosition;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
