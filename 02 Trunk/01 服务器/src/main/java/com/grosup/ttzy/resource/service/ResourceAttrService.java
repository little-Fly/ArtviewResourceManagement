package com.grosup.ttzy.resource.service;

import java.util.List;

import com.grosup.ttzy.resource.dto.ResourceAttrDto;

public interface ResourceAttrService {
	public ResourceAttrDto create(String json);

	public ResourceAttrDto add(String json);

	public ResourceAttrDto del(String resourceKey);

	public ResourceAttrDto update(String attrKey, String json);

	public ResourceAttrDto get(String resourceKey);

	public List<ResourceAttrDto> getAll(String typeKey);
}
