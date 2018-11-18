package com.grosup.ttzy.resource.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.grosup.ttzy.resource.dto.ResourceDetailDto;

public interface ResourceDetailService{
	public void create(String json);

	public void add(String json);

	public void del(String resourceKey);

	public void update(String resourceKey, String json);

	public List<ResourceDetailDto> get(String resourceKey);
	
	public List<ResourceDetailDto> get(Collection<String> resourceList);

	public Collection<ResourceDetailDto> getAll(String typeKey, int start, int len);
	
	public Collection<ResourceDetailDto> search(String typeKey, Map<String, String> searchKeyMap, int start, int len);

}
