package com.grosup.ttzy.resource.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.resource.dto.ResourceFileDto;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceFileDao implements ResourceConstant {
	private static Logger log = Logger.getLogger(ResourceFileDao.class);

	Map<String, ResourceFileDto> map = Collections.synchronizedMap(new HashMap<String, ResourceFileDto>());

	private String resourceKey = RESOURCE_DETAIL + "picture"; // long Y 主键，资源Id
	private String attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
	private String typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
	private String attrName = "示例表头"; // varchar Y 属性名
	private String fileKey = RESOURCE_FILE + "picture"; // varchar Y 主键，资源Id
	private String directory = ""; // varchar Y 存储的目录
	private String relativePath = RESOURCE_FILE + "1381901148036.jpg"; // varchar 相对路径
	private long lastmodifytime = 0; // long Y 最后修改时间

	@PostConstruct
	public void initMethod() {
		ResourceFileDto resourceFileDto = new ResourceFileDto();
		resourceFileDto.setTypeKey(typeKey);
		resourceFileDto.setAttrKey(attrKey);
		resourceFileDto.setResourceKey(resourceKey);
		resourceFileDto.setAttrName(attrName);
		resourceFileDto.setFileKey(fileKey);
		resourceFileDto.setDirectory(directory);
		resourceFileDto.setRelativePath(relativePath);
		map.put(fileKey, resourceFileDto);
		resourceFileDto = new ResourceFileDto();
		resourceKey = RESOURCE_DETAIL + "video"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头"; // varchar Y 属性名
		fileKey = RESOURCE_FILE + "video"; // varchar Y 主键，资源Id
		directory = ""; // varchar Y 存储的目录
		relativePath = RESOURCE_FILE + "EP40.mp4"; // varchar 相对路径
		lastmodifytime = 0; // long Y 最后修改时间
		resourceFileDto.setTypeKey(typeKey);
		resourceFileDto.setAttrKey(attrKey);
		resourceFileDto.setResourceKey(resourceKey);
		resourceFileDto.setAttrName(attrName);
		resourceFileDto.setFileKey(fileKey);
		resourceFileDto.setDirectory(directory);
		resourceFileDto.setRelativePath(relativePath);
		map.put(fileKey, resourceFileDto);
	}

	public ResourceFileDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceFileDto resourceFileDto = (ResourceFileDto) JSONObject.toBean(jsonObject, ResourceFileDto.class);
		resourceFileDto.setLastmodifytime(String.valueOf(System.currentTimeMillis()));
		return resourceFileDto;
	}

	public void add(ResourceFileDto resourceFileDto) {
		String typeKey = RESOURCE_FILE + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceFileDto.setFileKey(typeKey);

		map.put(typeKey, resourceFileDto);
	}

	public ResourceFileDto del(String fileKey) {
		ResourceFileDto resourceFileDto = null;
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			resourceFileDto = map.remove(fileKey);
		} else {
			log.error("ResourceFileDao del is error. fileKey is:" + fileKey);
		}
		return resourceFileDto;
	}

	public void update(ResourceFileDto resourceFileDto) {
		String fileKey = resourceFileDto.getTypeKey();
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			resourceFileDto.setLastmodifytime(String.valueOf(System.currentTimeMillis()));
			map.put(fileKey, resourceFileDto);
		} else {
			log.error("ResourceFileDao update is error. fileKey is:" + fileKey);
		}
	}

	public ResourceFileDto get(String key) {
		return map.get(key);
	}

	public Collection<ResourceFileDto> getAll() {
		return map.values();
	}

}
