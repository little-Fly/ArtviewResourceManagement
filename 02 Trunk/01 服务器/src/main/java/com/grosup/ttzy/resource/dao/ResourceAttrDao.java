package com.grosup.ttzy.resource.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.mapper.ResourceAttrMapper;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceAttrDao implements ResourceConstant {

	private static Logger log = Logger.getLogger(ResourceAttrDao.class);

	@Autowired
	private ResourceAttrMapper resourceAttrMapper;
	
//	Map<String, ResourceAttrDto> map = Collections.synchronizedMap(new HashMap<String, ResourceAttrDto>());
//	
//	private String attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
//	private String typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//	private String attrName = "示例表头"; // varchar Y 属性名
//	private String attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
//	private float attrPosition = 0; // varchar Y 资源长位置
//	private String attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
//	private String remark = "示例表头备注"; // varchar N 备注
	
//	@PostConstruct
//	public void initMethod() {
//		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
//		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//		attrName = "示例表头1"; // varchar Y 属性名
//		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
//		attrPosition = 1; // varchar Y 资源长位置
//		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
//		remark = "示例表头备注"; // varchar N 备注
//		initResourceAttrDto();
//		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
//		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//		attrName = "示例表头2"; // varchar Y 属性名
//		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
//		attrPosition = 2; // varchar Y 资源长位置
//		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
//		remark = "示例表头备注"; // varchar N 备注
//		initResourceAttrDto();
//		attrKey = RESOURCE_ATTR + "示例表头ID4"; // varchar Y 主键，属性Key
//		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//		attrName = "示例表头4"; // varchar Y 属性名
//		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
//		attrPosition = 4; // varchar Y 资源长位置
//		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
//		remark = "示例表头备注"; // varchar N 备注
//		initResourceAttrDto();
//		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
//		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//		attrName = "示例表头3"; // varchar Y 属性名
//		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
//		attrPosition = 3; // varchar Y 资源长位置
//		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
//		remark = "示例表头备注"; // varchar N 备注
//		initResourceAttrDto();
//
//	}
	
//	public void initResourceAttrDto() {
//		ResourceAttrDto resourceAttrDto =new ResourceAttrDto();
//		resourceAttrDto.setTypeKey(typeKey);
//		resourceAttrDto.setAttrKey(attrKey);
//		resourceAttrDto.setAttrLevel(attrLevel);
//		resourceAttrDto.setAttrPosition(attrPosition);
//		resourceAttrDto.setAttrName(attrName);
//		resourceAttrDto.setAttrType(attrType);
//		resourceAttrDto.setRemark(remark);
//		map.put(attrKey, resourceAttrDto);
//	}
	
	public ResourceAttrDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceAttrDto resourceAttrDto = (ResourceAttrDto) JSONObject.toBean(jsonObject, ResourceAttrDto.class);
		return resourceAttrDto;
	}

	public ResourceAttrDto add(ResourceAttrDto resourceAttrDto) {
		String attrKey = RESOURCE_ATTR + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceAttrDto.setAttrKey(attrKey);
//		map.put(attrKey, resourceAttrDto);
		resourceAttrMapper.add(resourceAttrDto);
		return resourceAttrDto;
	}

	public void del(String attrKey) {
		if (!StringUtil.isNullOrEmpty(attrKey)) {
			resourceAttrMapper.del(attrKey);
//			map.remove(attrKey);
		} else {
			log.error("ResourceAttrDao del is error. AttrKey is:" + attrKey);
		}
	}

	public ResourceAttrDto update(String attrKey, ResourceAttrDto resourceAttrDto) {
		if (!StringUtil.isNullOrEmpty(attrKey)) {
//			if(!map.containsKey(attrKey))
//			{
//				return null;
//			}
//			resourceAttrDto.setAttrKey(attrKey);
//			map.put(attrKey, resourceAttrDto);
			resourceAttrMapper.update(resourceAttrDto);
		} else {
			log.error("ResourceAttrDao update is error. AttrKey is:" + attrKey);
		}
		return resourceAttrDto;
	}

	public ResourceAttrDto get(String attrKey) {
//		return map.get(key);
		return resourceAttrMapper.get(attrKey);
	}

	public List<ResourceAttrDto> getAll(String typeKey) {
//		Collection<ResourceAttrDto> collection = map.values();
//		List<ResourceAttrDto> list = new ArrayList<ResourceAttrDto>();
//		for (ResourceAttrDto resourceAttrDto : collection) {
//			if (typeKey.equals(resourceAttrDto.getTypeKey())) {
//				list.add(resourceAttrDto);
//			}
//		}
		return resourceAttrMapper.getAll(typeKey);
//		return list;
	}
}
