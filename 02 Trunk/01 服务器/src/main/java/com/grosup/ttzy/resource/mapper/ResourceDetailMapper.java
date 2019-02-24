package com.grosup.ttzy.resource.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.resource.dto.ResourceDetailDto;

@Repository
public interface ResourceDetailMapper {

	public void add(ResourceDetailDto resourceDetailDto);

	public void del(String resourceKey);

	public void updateAttrName(Map<String, Object> map);
	
	public void updateState(Map<String, Object> map);
	
	public void update(ResourceDetailDto resourceDetailDto);

	public Collection<ResourceDetailDto> get(String resourceKey);

	public Collection<ResourceDetailDto> getByUser(Map<String, Object> map);
	
	public Collection<ResourceDetailDto> getByAdmin(Map<String, Object> map);
	
	public Collection<String> getAll(Map<String, Object> map);
	
	public Collection<String> getAllByUser(Map<String, Object> map);
	
	public Collection<String> getAllPending(Map<String, Object> map);
	
	public Collection<String> getAllByAdmin(Map<String, Object> map);
	
	public List<String> search(Map<String, Object> map);
	
	public List<String> searchInResourceKey(Map<String, Object> map);
		
	public List<String> searchByUserAndAdmin(Map<String, Object> map);
	
	public List<String> searchByUserAndAdminInResourceKey(Map<String, Object> map);
}
