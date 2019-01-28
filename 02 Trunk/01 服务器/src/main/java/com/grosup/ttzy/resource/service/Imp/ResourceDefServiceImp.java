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

	public ResourceDefDto create(String json) {
		return resourceDefDao.create(json);
	}

	public ResourceDefDto add(String json) {
		ResourceDefDto resourceDefDto = resourceDefDao.create(json);
		if (resourceDefDto != null) {
			return resourceDefDao.add(resourceDefDto);
		} else {
			log.error("resourceDefService add resourceDefDto is null. resourceKey json:" + json);
		}
		return null;
	}

	public ResourceDefDto del(String resourceKey) {
		return resourceDefDao.del(resourceKey);

	}

	public ResourceDefDto update(String json) {
		ResourceDefDto resourceDefDto = resourceDefDao.create(json);
		if (resourceDefDto != null) {
			return resourceDefDao.update(resourceDefDto);
		} else {
			log.error("resourceDefService update resourceDefDto is null. resourceKey json:" + json);
		}
		return null;
	}

	public ResourceDefDto get(String resourceKey) {
		return resourceDefDao.get(resourceKey);
	}

	public Collection<ResourceDefDto> getAll() {
		return resourceDefDao.getAll();
	}
}
