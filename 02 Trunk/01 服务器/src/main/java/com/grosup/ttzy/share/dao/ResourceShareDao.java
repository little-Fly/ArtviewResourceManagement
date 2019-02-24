package com.grosup.ttzy.share.dao;

import java.util.Collection;

//import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.share.dto.ResourceShareDto;
import com.grosup.ttzy.share.mapper.ResourceShareMapper;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONObject;

@Component
public class ResourceShareDao implements ResourceConstant {

	private static Logger log = Logger.getLogger(ResourceShareDao.class);

	@Autowired
	private ResourceShareMapper resourceShareMapper;
	
	//Map<String, ResourceShareDto> map = Collections.synchronizedMap(new HashMap<String, ResourceShareDto>());

//	private String shareKey = RESOURCE_SHARE + "templatekey"; // 分享ID，用于访问和获取
//	private String resourceListJson = "[{\"typeKey\":\"RDf示例表ID\", \"resourceKey\":\"RDt示例值ID\"}, {\"typeKey\":\"RDf示例表ID\", \"resourceKey\":\"RDt示例值ID2\"}]"; // 分享的资源列表Json格式
//	private String sendUser = "示例分享人"; // 分享人
//	private String templateName = "template0"; // 模板名称

//	@PostConstruct
//	public void initMethod() {
//		ResourceShareDto resourceShareDto = new ResourceShareDto();
//		resourceShareDto.setShareKey(shareKey);
//		resourceShareDto.setResourceListJson(resourceListJson);
//		resourceShareDto.setTemplateName(templateName);
//		resourceShareDto.setSendUser(sendUser);
//		Long time = System.currentTimeMillis();
//		resourceShareDto.setSendTime(time);
//		resourceShareDto.setLastTime(time);
//		map.put(shareKey, resourceShareDto);
//	}

	public ResourceShareDto create(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ResourceShareDto resourceShareDto = (ResourceShareDto) JSONObject.toBean(jsonObject, ResourceShareDto.class);
		Long time = System.currentTimeMillis();
		resourceShareDto.setSendTime(String.valueOf(time));
		resourceShareDto.setLastTime(String.valueOf(time));
		return resourceShareDto;
	}

	public void add(ResourceShareDto resourceShareDto) {
		String shareKey = RESOURCE_SHARE + IdUtils.getIncreaseIdByCurrentTimeMillis();
		resourceShareDto.setShareKey(shareKey);
		resourceShareMapper.add(resourceShareDto);
//		map.put(shareKey, resourceShareDto);
	}

	public void del(String shareKey) {
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			resourceShareMapper.del(shareKey);
//			map.remove(shareKey);
		} else {
			log.error("ResourceDefDao del is error. shareKey is:" + shareKey);
		}
	}

	public void update(String shareKey, ResourceShareDto resourceShareDto) {
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			resourceShareDto.setShareKey(shareKey);
			resourceShareMapper.update(resourceShareDto);
//			map.put(shareKey, resourceShareDto);
		} else {
			log.error("ResourceDefDao update is error. shareKey is:" + shareKey);
		}
	}

	public ResourceShareDto get(String shareKey) {
		return resourceShareMapper.get(shareKey);
//		return map.get(key);
	}

	public Collection<ResourceShareDto> getAll() {
		return resourceShareMapper.getAll();
//		return map.values();
	}

}
