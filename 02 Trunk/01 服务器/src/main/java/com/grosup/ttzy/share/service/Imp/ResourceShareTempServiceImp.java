package com.grosup.ttzy.share.service.Imp;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.share.dao.ResourceShareTempDao;
import com.grosup.ttzy.share.dto.ResourceShareTempDto;
import com.grosup.ttzy.share.service.ResourceShareTempService;

@Service
public class ResourceShareTempServiceImp implements ResourceShareTempService {

	private static Logger log = Logger.getLogger(ResourceShareTempServiceImp.class);

	@Autowired
	private ResourceShareTempDao resourceShareTempDao;

	public void create(String json) {
		resourceShareTempDao.create(json);
	}

	public ResourceShareTempDto add(String json) {
		ResourceShareTempDto resourceDefDto = resourceShareTempDao.create(json);
		if (resourceDefDto != null) {
			resourceShareTempDao.add(resourceDefDto);
		} else {
			log.error("resourceDefService add resourceDefDto is null. shareKey json:" + json);
		}
		return resourceDefDto;
	}

	public void del(String shareKey) {
		resourceShareTempDao.del(shareKey);

	}

	public ResourceShareTempDto get(String shareKey) {
		return resourceShareTempDao.get(shareKey);
	}

	public Collection<ResourceShareTempDto> getAll() {
		return resourceShareTempDao.getAll();
	}
}