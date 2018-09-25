package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceAttrDao;
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.service.ResourceAttrService;

@Service
public class ResourceAttrServiceImp  implements ResourceAttrService {

	private static Logger log = Logger.getLogger(ResourceAttrServiceImp.class);

	@Autowired
	private ResourceAttrDao resourceAttrDao;

	public void create(String json) {
		resourceAttrDao.create(json);
	}

	public void add(String json) {
		ResourceAttrDto resourceAttrDto = resourceAttrDao.create(json);
		if (resourceAttrDto != null) {
			resourceAttrDao.add(resourceAttrDto);
		} else {
			log.error("resourceAttrService add resourceAttrDto is null. resourceKey json:" + json);
		}

	}

	public void del(String resourceKey) {
		resourceAttrDao.del(resourceKey);

	}

	public void update(String json) {
		ResourceAttrDto resourceAttrDto = resourceAttrDao.create(json);
		if (resourceAttrDto != null) {
			resourceAttrDao.update(resourceAttrDto);
		} else {
			log.error("resourceAttrService update resourceAttrDto is null. resourceKey json:" + json);
		}
	}

	public ResourceAttrDto get(String resourceKey) {
		return resourceAttrDao.get(resourceKey);
	}

	public Collection<ResourceAttrDto> getAll(String typeKey) {
		return resourceAttrDao.getAll(typeKey);
	}

}
