package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceDefDao;
import com.grosup.ttzy.resource.dto.ResourceDefDto;
import com.grosup.ttzy.resource.service.ResourceDefService;
import com.grosup.ttzy.resource.service.ResourceFileService;

@Service
public class ResourceDefServiceImp implements ResourceDefService {

	private static Logger log = Logger.getLogger(ResourceDefServiceImp.class);

	@Autowired
	private ResourceDefDao resourceDefDao;

	@Autowired
	ResourceFileService resourceFileService;
	
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

	public ResourceDefDto del(String typeKey) {
		ResourceDefDto resourceDefDto = resourceDefDao.del(typeKey);
		resourceFileService.del4def(typeKey);
		return resourceDefDto;

	}

	public ResourceDefDto update(String typeKey, String json) {
		ResourceDefDto resourceDefDto = resourceDefDao.create(json);
		if (resourceDefDto != null) {
			return resourceDefDao.update(typeKey, resourceDefDto);
		} else {
			log.error("resourceDefService update resourceDefDto is null. resourceKey json:" + json);
		}
		return null;
	}

	public ResourceDefDto get(String typeKey) {
		return resourceDefDao.get(typeKey);
	}

	public Collection<ResourceDefDto> getAll() {
		return resourceDefDao.getAll();
	}
}
