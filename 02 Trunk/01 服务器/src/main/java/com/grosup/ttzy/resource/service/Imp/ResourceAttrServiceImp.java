package com.grosup.ttzy.resource.service.Imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceAttrDao;
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.service.ResourceAttrService;
import com.grosup.ttzy.resource.service.ResourceDetailService;

@Service
public class ResourceAttrServiceImp implements ResourceAttrService {

	private static Logger log = Logger.getLogger(ResourceAttrServiceImp.class);

	@Autowired
	private ResourceAttrDao resourceAttrDao;

	@Autowired
	ResourceDetailService resourceDetailService;

	public ResourceAttrDto create(String json) {
		return resourceAttrDao.create(json);
	}

	public ResourceAttrDto add(String json) {
		ResourceAttrDto resourceAttrDto = resourceAttrDao.create(json);
		if (resourceAttrDto != null) {
			return resourceAttrDao.add(resourceAttrDto);
		} else {
			log.error("resourceAttrService add resourceAttrDto is null. attrKey json:" + json);
		}
		return null;
	}

	public ResourceAttrDto del(String attrKey) {
		return resourceAttrDao.del(attrKey);
	}

	public ResourceAttrDto update(String json) {
		ResourceAttrDto resourceAttrDto = resourceAttrDao.create(json);
		if (resourceAttrDto != null) {
			resourceAttrDto = resourceAttrDao.update(resourceAttrDto);
			if (null != resourceAttrDto) {
				resourceDetailService.updateAttrName(resourceAttrDto.getAttrKey(), resourceAttrDto.getAttrName());
				return resourceAttrDto;
			} else {
				log.error("resourceAttrService update resourceAttrDto with attrKey is error. json:" + json);
			}
		} else {
			log.error("resourceAttrService update resourceAttrDto is null. attrKey json:" + json);
		}
		return null;
	}

	public ResourceAttrDto get(String attrKey) {
		return resourceAttrDao.get(attrKey);
	}

	public List<ResourceAttrDto> getAll(String typeKey) {
		return resourceAttrDao.getAll(typeKey);
	}

}
