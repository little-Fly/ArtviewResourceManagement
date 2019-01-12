package com.grosup.ttzy.resource.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.grosup.ttzy.resource.dto.ResourceDetailDto;

public interface ResourceDetailService {
	public void create(String json);

	public void add(String json);

	public void approvalAdd(String resourceKey, String approvalMess);

	public void approvalDel(String resourceKey, String approvalMess);
	
	public void approvalUpdate(String resourceKey, String approvalMess);

	public void reject(String resourceKey, String approvalMess);

	public void del(String resourceKey);

	public void update(String resourceKey, String json);

	public List<ResourceDetailDto> get(String resourceKey);

	public List<ResourceDetailDto> getByUser(String resourceKey);
	
	public List<ResourceDetailDto> getByAdmin(String resourceKey);

	public List<ResourceDetailDto> getByUser(Collection<String> resourceList);

	public Collection<ResourceDetailDto> getAll(String typeKey, int start, int len);

	public Collection<ResourceDetailDto> getAllByUser(String typeKey, int start, int len);
	
	public Collection<ResourceDetailDto> getAllByAdmin(String typeKey, int start, int len);
	
	public Collection<ResourceDetailDto> getAllPending(String typeKey, int start, int len);

	public Collection<ResourceDetailDto> search(String typeKey, Map<String, String> searchKeyMap, int start, int len);

}
