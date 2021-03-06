package com.grosup.ttzy.resource.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.resource.dto.ResourceFileDto;
import com.grosup.ttzy.resource.mapper.ResourceDetailMapper;
import com.grosup.ttzy.resource.mapper.ResourceFileMapper;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceFileDao implements ResourceConstant {
	private static Logger log = Logger.getLogger(ResourceFileDao.class);

	@Autowired
	private ResourceFileMapper resourceFileMapper;

//	Map<String, ResourceFileDto> map = Collections.synchronizedMap(new HashMap<String, ResourceFileDto>());
//
//	private String resourceKey = RESOURCE_DETAIL + "picture"; // long Y 主键，资源Id
//	private String attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
//	private String typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//	private String attrName = "示例表头"; // varchar Y 属性名
//	private String fileKey = RESOURCE_FILE + "picture"; // varchar Y 主键，资源Id
//	private String directory = ""; // varchar Y 存储的目录
//	private String relativePath = RESOURCE_FILE + "1381901148036.jpg"; // varchar 相对路径
//	private long lastmodifytime = 0; // long Y 最后修改时间

//	@PostConstruct
//	public void initMethod() {
//		ResourceFileDto resourceFileDto = new ResourceFileDto();
//		resourceFileDto.setTypeKey(typeKey);
//		resourceFileDto.setAttrKey(attrKey);
//		resourceFileDto.setResourceKey(resourceKey);
//		resourceFileDto.setAttrName(attrName);
//		resourceFileDto.setFileKey(fileKey);
//		resourceFileDto.setDirectory(directory);
//		resourceFileDto.setRelativePath(relativePath);
//		map.put(fileKey, resourceFileDto);
//		resourceFileDto = new ResourceFileDto();
//		resourceKey = RESOURCE_DETAIL + "video"; // long Y 主键，资源Id
//		attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
//		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
//		attrName = "示例表头"; // varchar Y 属性名
//		fileKey = RESOURCE_FILE + "video"; // varchar Y 主键，资源Id
//		directory = ""; // varchar Y 存储的目录
//		relativePath = RESOURCE_FILE + "EP40.mp4"; // varchar 相对路径
//		lastmodifytime = 0; // long Y 最后修改时间
//		resourceFileDto.setTypeKey(typeKey);
//		resourceFileDto.setAttrKey(attrKey);
//		resourceFileDto.setResourceKey(resourceKey);
//		resourceFileDto.setAttrName(attrName);
//		resourceFileDto.setFileKey(fileKey);
//		resourceFileDto.setDirectory(directory);
//		resourceFileDto.setRelativePath(relativePath);
//		map.put(fileKey, resourceFileDto);
//	}

	public ResourceFileDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceFileDto resourceFileDto = (ResourceFileDto) JSONObject.toBean(jsonObject, ResourceFileDto.class);
		resourceFileDto.setLastmodifytime(String.valueOf(System.currentTimeMillis()));
		return resourceFileDto;
	}

	public void add(ResourceFileDto resourceFileDto) {
		String typeKey = RESOURCE_FILE + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceFileDto.setFileKey(typeKey);
		resourceFileMapper.add(resourceFileDto);
//		map.put(typeKey, resourceFileDto);
	}

	public ResourceFileDto del4def(String typeKey) {
		ResourceFileDto resourceFileDto = null;
		if (!StringUtil.isNullOrEmpty(typeKey)) {
			resourceFileDto = resourceFileMapper.get4def(typeKey);
			resourceFileMapper.del4def(typeKey);
		} else {
			log.error("ResourceFileDao del4def is error. typeKey is:" + typeKey);
		}
		return resourceFileDto;
	}

	public Collection<ResourceFileDto> del4detail(String resourceKey) {
		Collection<ResourceFileDto> resourceFileDtos = null;
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			resourceFileDtos = resourceFileMapper.get4detail(resourceKey);
			resourceFileMapper.del4detail(resourceKey);
		} else {
			log.error("ResourceFileDao del4detail is error. resourceKey is:" + resourceKey);
		}
		return resourceFileDtos;
	}

	public ResourceFileDto del(String fileKey) {
		ResourceFileDto resourceFileDto = null;
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			resourceFileDto = get(fileKey);
			resourceFileMapper.del(fileKey);
//			resourceFileDto = map.remove(fileKey);
		} else {
			log.error("ResourceFileDao del is error. fileKey is:" + fileKey);
		}
		return resourceFileDto;
	}

	public void update(ResourceFileDto resourceFileDto) {
		String fileKey = resourceFileDto.getTypeKey();
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			resourceFileDto.setLastmodifytime(String.valueOf(System.currentTimeMillis()));
			resourceFileMapper.update(resourceFileDto);
//			map.put(fileKey, resourceFileDto);
		} else {
			log.error("ResourceFileDao update is error. fileKey is:" + fileKey);
		}
	}

	public ResourceFileDto get(String fileKey) {
		return resourceFileMapper.get(fileKey);
//		return map.get(fileKey);
	}

	public Collection<ResourceFileDto> getAll() {
		return resourceFileMapper.getAll();
//		return map.values();
	}

}
