package com.grosup.ttzy.resource.dto;
/**
 * 
 * @author Administrator
 *	 ��Դ����
 */
public class ResourceDefDto {

	private String typeKey;	//varchar	Y	主键，资源类型Key
	private String name;	//varchar	Y	资源类型名称
	private String remark;	//varchar	N	备注ע


	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
