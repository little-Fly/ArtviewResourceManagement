package com.grosup.ttzy.resource.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.resource.dto.ResourceAttrDto;

@Repository
public interface ResourceAttrMapper {

	public void add(ResourceAttrDto resourceAttrDto);

	public void del(String attrKey);

	public void update(ResourceAttrDto resourceAttrDto);

	public ResourceAttrDto get(String attrKey);

	public List<ResourceAttrDto> getAll(String typeKey);
}
