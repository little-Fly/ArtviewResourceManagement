package com.grosup.ttzy.resource.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.resource.dto.ResourceDefDto;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceDefDao implements ResourceConstant {

	private static Logger log = Logger.getLogger(ResourceDefDao.class);

	Map<String, ResourceDefDto> map = Collections.synchronizedMap(new HashMap<String, ResourceDefDto>());

	private String typeKey = RESOURCE_DEF + "示例表ID";	//varchar	Y	主键，资源类型Key
	private String logoUrl = "../static/img/model.jpg";//示例图片
	private String name = "示例表名";	//varchar	Y	资源类型名称
	private String remark = "示例表注释";	//varchar	N	备注
	
	@PostConstruct
	public void initMethod() {
		ResourceDefDto resourceDefDto =new ResourceDefDto();
		resourceDefDto.setTypeKey(typeKey);
		resourceDefDto.setLogoUrl(logoUrl);
		resourceDefDto.setName(name);
		resourceDefDto.setRemark(remark);
		map.put(typeKey, resourceDefDto);
	}

	public ResourceDefDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceDefDto resourceDefDto = (ResourceDefDto) JSONObject.toBean(jsonObject, ResourceDefDto.class);
		return resourceDefDto;
	}

	public ResourceDefDto add(ResourceDefDto resourceDefDto) {
		String typeKey = RESOURCE_DEF + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceDefDto.setTypeKey(typeKey);
		map.put(typeKey, resourceDefDto);
		return resourceDefDto;
	}

	public ResourceDefDto del(String typeKey) {
		if (!StringUtil.isNullOrEmpty(typeKey)) {
			return map.remove(typeKey);
		} else {
			log.error("ResourceDefDao del is error. typeKey is:" + typeKey);
		}
		return null;
	}

	public ResourceDefDto update(ResourceDefDto resourceDefDto) {
		String typeKey = resourceDefDto.getTypeKey();
		if (!StringUtil.isNullOrEmpty(typeKey)) {
			map.put(typeKey, resourceDefDto);
			return resourceDefDto;
		} else {
			log.error("ResourceDefDao update is error. typeKey is:" + typeKey);
		}
		return null;
	}

	public ResourceDefDto get(String key) {
		return map.get(key);
	}

	public Collection<ResourceDefDto> getAll() {
		return map.values();
	}

}
