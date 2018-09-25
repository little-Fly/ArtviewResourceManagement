package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceDefDao;
import com.grosup.ttzy.resource.dto.ResourceDefDto;
import com.grosup.ttzy.resource.service.ResourceDefService;

@Service
public class ResourceDefServiceImp implements ResourceDefService {

	private static Logger log = Logger.getLogger(ResourceDefServiceImp.class);

	@Autowired
	private ResourceDefDao resourceDefDao;

	public void create(String json) {
		resourceDefDao.create(json);
	}

	public void add(String json) {
		ResourceDefDto resourceDefDto = resourceDefDao.create(json);
		if (resourceDefDto != null) {
			resourceDefDao.add(resourceDefDto);
		} else {
			log.error("resourceDefService add resourceDefDto is null. resourceKey json:" + json);
		}

	}

	public void del(String resourceKey) {
		resourceDefDao.del(resourceKey);

	}

	public void update(String json) {
		ResourceDefDto resourceDefDto = resourceDefDao.create(json);
		if (resourceDefDto != null) {
			resourceDefDao.update(resourceDefDto);
		} else {
			log.error("resourceDefService update resourceDefDto is null. resourceKey json:" + json);
		}
	}

	public ResourceDefDto get(String resourceKey) {
		return resourceDefDao.get(resourceKey);
	}

	public Collection<ResourceDefDto> getAll() {
		return resourceDefDao.getAll();
	}
}
