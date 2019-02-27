package com.grosup.ttzy.resource.service.Imp;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grosup.ttzy.resource.dao.ResourceDetailDao;
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceAttrService;
import com.grosup.ttzy.resource.service.ResourceDetailService;

@Service
public class ResourceDetailServiceImp implements ResourceDetailService {

	private static Logger log = Logger.getLogger(ResourceDetailServiceImp.class);

	@Autowired
	private ResourceDetailDao resourceDetailDao;
	
	@Autowired
	ResourceAttrService resourceAttrService;

	public Collection<ResourceDetailDto> create(String json) {
		return resourceDetailDao.create(json);
	}

	public Collection<ResourceDetailDto> add(String json, String approvalUser) {
		Collection<ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			for(ResourceDetailDto resourceDetailDto: collection)
			{
				ResourceAttrDto resourceAttrDto = resourceAttrService.get(resourceDetailDto.getAttrKey());
				if(null != resourceAttrDto)
				{
					resourceDetailDto.setAttrName(resourceAttrDto.getAttrName());
					resourceDetailDto.setAttrLevel(resourceAttrDto.getAttrLevel());
				}else
				{
					return null;
				}
			}
			return resourceDetailDao.add(collection, approvalUser);
		} else {
			log.error("ResourceDetailService add resourceDetailDto is null. resourceKey json:" + json);
		}
		return null;
	}

	public void approvalAdd(String resourceKey, String approvalMess, String approvalUser) {
		resourceDetailDao.approvalAdd(resourceKey, approvalMess, approvalUser);
	}

	public void approvalDel(String resourceKey, String approvalMess, String approvalUser) {
		resourceDetailDao.approvalDel(resourceKey, approvalMess, approvalUser);
	}

	public void approvalUpdate(String resourceKey, String approvalMess, String approvalUser) {
		resourceDetailDao.approvalUpdate(resourceKey, approvalMess, approvalUser);
	}

	public void reject(String resourceKey, String approvalMess, String approvalUser) {
		resourceDetailDao.reject(resourceKey, approvalMess, approvalUser);
	}

	public void del(String resourceKey, String approvalUser) {
		resourceDetailDao.del(resourceKey, approvalUser);
	}

	public void update(String resourceKey, String json, String approvalUser) {
		Collection<ResourceDetailDto> collection = resourceDetailDao.create(json);
		if (collection != null) {
			resourceDetailDao.update(resourceKey, collection, approvalUser);
		} else {
			log.error("ResourceDetailService update resourceDetailDto is null. resourceKey json:" + json);
		}
	}

	public void updateAttrName(String newAttrKey, String newAttrName) {
		resourceDetailDao.updateAttrName(newAttrKey, newAttrName);
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

	public int getAllTotal(String typeKey) {
		return resourceDetailDao.getAllTotal(typeKey);
	}

	public Collection<ResourceDetailDto> getAllByUser(String typeKey, int start, int len) {
		return resourceDetailDao.getAllByUser(typeKey, start, len);
	}

	public int getAllTotalByUser(String typeKey) {
		return resourceDetailDao.getAllTotalByUser(typeKey);
	}

	public Collection<ResourceDetailDto> getAllByAdmin(String typeKey, int start, int len) {
		return resourceDetailDao.getAllByAdmin(typeKey, start, len);
	}

	public int getAllTotalByAdmin(String typeKey) {
		return resourceDetailDao.getAllTotalByAdmin(typeKey);
	}

	public Collection<ResourceDetailDto> getAllPending(String typeKey, int start, int len) {
		return resourceDetailDao.getAllPending(typeKey, start, len);
	}

	public int getAllPendingTotal(String typeKey) {
		return resourceDetailDao.getAllPendingTotal(typeKey);
	}

	public Collection<ResourceDetailDto> search(String typeKey, Map<String, String> searchKeyMap, int start, int len) {
		return resourceDetailDao.search(typeKey, searchKeyMap, start, len);
	}

	public int getSearchTotal(String typeKey, Map<String, String> searchKeyMap) {
		return resourceDetailDao.getSearchTotal(typeKey, searchKeyMap);
	}

	public Collection<ResourceDetailDto> searchByUser(String typeKey, Map<String, String> searchKeyMap, int start,
			int len) {
		return resourceDetailDao.searchByUser(typeKey, searchKeyMap, start, len);
	}

	public int getSearchTotalByUser(String typeKey, Map<String, String> searchKeyMap) {
		return resourceDetailDao.getSearchTotalByUserAndAdmin(typeKey, searchKeyMap);
	}

	public Collection<ResourceDetailDto> searchByAdmin(String typeKey, Map<String, String> searchKeyMap, int start,
			int len) {
		return resourceDetailDao.searchByAdmin(typeKey, searchKeyMap, start, len);
	}

	public int getSearchTotalByAdmin(String typeKey, Map<String, String> searchKeyMap) {
		return resourceDetailDao.getSearchTotalByUserAndAdmin(typeKey, searchKeyMap);
	}

}
