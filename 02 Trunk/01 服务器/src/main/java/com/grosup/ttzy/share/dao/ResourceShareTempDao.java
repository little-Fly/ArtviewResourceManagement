package com.grosup.ttzy.share.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.share.dto.ResourceShareTempDto;
import com.grosup.ttzy.share.mapper.ResourceShareMapper;
import com.grosup.ttzy.share.mapper.ResourceShareTempMapper;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceShareTempDao implements ResourceConstant {

	private static Logger log = Logger.getLogger(ResourceShareTempDao.class);

	@Autowired
	private ResourceShareTempMapper resourceShareTempMapper;
	
//	Map<String, ResourceShareTempDto> map = Collections.synchronizedMap(new HashMap<String, ResourceShareTempDto>());

//	private String tempKey=RESOURCE_SHARE_TEMP + "default"; //模板ID
//	private String tempName="default模板"; //模板名稱
//	private String tempFilePath="/pages/share/template/template0.jsp"; //模板filepath
	

//	@PostConstruct
//	public void initMethod() {
//		ResourceShareTempDto resourceShareTempDto = new ResourceShareTempDto();
//		resourceShareTempDto.setTempFilePath(tempFilePath);
//		resourceShareTempDto.setTempKey(tempKey);
//		resourceShareTempDto.setTempName(tempName);
//
//		map.put(tempKey, resourceShareTempDto);
//	}

	public ResourceShareTempDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceShareTempDto resourceShareTempDto = (ResourceShareTempDto) JSONObject.toBean(jsonObject, ResourceShareTempDto.class);
		return resourceShareTempDto;
	}

	public void add(ResourceShareTempDto resourceShareTempDto) {
		String tempKey = RESOURCE_SHARE + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceShareTempDto.setTempKey(tempKey);
		resourceShareTempMapper.add(resourceShareTempDto);
//		map.put(tempKey, resourceShareTempDto);
	}

	public void del(String tempKey) {
		if (!StringUtil.isNullOrEmpty(tempKey)) {
			resourceShareTempMapper.del(tempKey);
//			map.remove(tempKey);
		} else {
			log.error("ResourceDefDao del is error. tempKey is:" + tempKey);
		}
	}

	public ResourceShareTempDto get(String tempKey) {
		return resourceShareTempMapper.get(tempKey);
//		return map.get(tempKey);
	}

	public Collection<ResourceShareTempDto> getAll() {
		return resourceShareTempMapper.getAll();
//		return map.values();
	}

}
