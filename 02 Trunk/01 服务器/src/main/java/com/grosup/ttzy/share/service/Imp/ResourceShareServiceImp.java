package com.grosup.ttzy.share.service.Imp;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.share.dao.ResourceShareDao;
import com.grosup.ttzy.share.dto.ResourceShareDto;
import com.grosup.ttzy.share.service.ResourceShareService;

@Service
public class ResourceShareServiceImp implements ResourceShareService {

	private static Logger log = Logger.getLogger(ResourceShareServiceImp.class);

	@Autowired
	private ResourceShareDao resourceShareDao;

	public void create(String json) {
		resourceShareDao.create(json);
	}

	public ResourceShareDto add(String json) {
		ResourceShareDto resourceDefDto = resourceShareDao.create(json);
		if (resourceDefDto != null) {
			resourceShareDao.add(resourceDefDto);
		} else {
			log.error("resourceDefService add resourceDefDto is null. shareKey json:" + json);
		}
		return resourceDefDto;
	}

	public void del(String shareKey) {
		resourceShareDao.del(shareKey);

	}

	public void update(String json) {
		ResourceShareDto resourceDefDto = resourceShareDao.create(json);
		if (resourceDefDto != null) {
			resourceShareDao.update(resourceDefDto);
		} else {
			log.error("resourceDefService update resourceDefDto is null. shareKey json:" + json);
		}
	}

	public ResourceShareDto get(String shareKey) {
		return resourceShareDao.get(shareKey);
	}

	public Collection<ResourceShareDto> getAll() {
		return resourceShareDao.getAll();
	}
}
