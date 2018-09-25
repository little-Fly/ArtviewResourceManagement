package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceDetailDao;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;

@Service
public class ResourceDetailServiceImp implements ResourceDetailService {

	private static Logger log = Logger.getLogger(ResourceDetailServiceImp.class);

	@Autowired
	private ResourceDetailDao resourceDetailDao;

	public void create(String json) {
		resourceDetailDao.create(json);
	}

	public void add(String json) {
		Collection <ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			resourceDetailDao.add(collection);
		} else {
			log.error("ResourceDetailService add resourceDetailDto is null. resourceKey json:" + json);
		}

	}

	public void del(String resourceKey) {
		resourceDetailDao.del(resourceKey);

	}

	public void update(String resourceKey, String json) {
		Collection <ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			resourceDetailDao.update(resourceKey, collection);
		} else {
			log.error("ResourceDetailService update resourceDetailDto is null. resourceKey json:" + json);
		}
	}

	public List<ResourceDetailDto> get(String resourceKey) {
		return resourceDetailDao.get(resourceKey);
	}

	public Collection<ResourceDetailDto> getAll(String typeKey, int start, int len) {
		return resourceDetailDao.getAll(typeKey, start, len);
	}
}
