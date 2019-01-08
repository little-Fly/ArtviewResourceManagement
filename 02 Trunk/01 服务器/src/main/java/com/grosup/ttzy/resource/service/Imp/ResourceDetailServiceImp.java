package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
		Collection<ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			resourceDetailDao.add(collection);
		} else {
			log.error("ResourceDetailService add resourceDetailDto is null. resourceKey json:" + json);
		}

	}

	public void approvalAdd(String resourceKey) {
		resourceDetailDao.approvalAdd(resourceKey);
	}

	public void approvalDel(String resourceKey) {
		resourceDetailDao.approvalDel(resourceKey);
	}

	public void approvalUpdate(String resourceKey)
	{
		resourceDetailDao.approvalUpdate(resourceKey);
	}
	
	public void reject(String resourceKey) {
		resourceDetailDao.reject(resourceKey);
	}
	
	public void del(String resourceKey) {
		resourceDetailDao.del(resourceKey);
	}

	public void update(String resourceKey, String json) {
		Collection<ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			resourceDetailDao.update(resourceKey, collection);
		} else {
			log.error("ResourceDetailService update resourceDetailDto is null. resourceKey json:" + json);
		}
	}

	public List<ResourceDetailDto> get(String resourceKey) {
		return resourceDetailDao.get(resourceKey);
	}

	public List<ResourceDetailDto> getByUser(String resourceKey) {
		return resourceDetailDao.getByUser(resourceKey);
	}
	
	public List<ResourceDetailDto> getByAdmin(String resourceKey) {
		return resourceDetailDao.getByAdmin(resourceKey);
	}

	public List<ResourceDetailDto> getByUser(Collection<String> resourceList) {
		return resourceDetailDao.getByUser(resourceList);
	}

	public Collection<ResourceDetailDto> getAll(String typeKey, int start, int len) {
		return resourceDetailDao.getAll(typeKey, start, len);
	}

	public Collection<ResourceDetailDto> search(String typeKey, Map<String, String> searchKeyMap, int start, int len) {
		return resourceDetailDao.search(typeKey, searchKeyMap, start, len);
	}

	public Collection<ResourceDetailDto> getAllByUser(String typeKey, int start, int len) {
		return resourceDetailDao.getAllByUser(typeKey, start, len);
	}
	
	public Collection<ResourceDetailDto> getAllByAdmin(String typeKey, int start, int len) {
		return resourceDetailDao.getAllByAdmin(typeKey, start, len);
	}

	public Collection<ResourceDetailDto> getAllPending(String typeKey, int start, int len) {
		return resourceDetailDao.getAllPending(typeKey, start, len);
	}
}
