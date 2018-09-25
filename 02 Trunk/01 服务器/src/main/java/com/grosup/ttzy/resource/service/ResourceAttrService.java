package com.grosup.ttzy.resource.service;

import java.util.Collection;

import com.grosup.ttzy.resource.dto.ResourceAttrDto;

public interface ResourceAttrService {
	public void create(String json);

	public void add(String json);

	public void del(String resourceKey);

	public void update(String json);

	public ResourceAttrDto get(String resourceKey);

	public Collection<ResourceAttrDto> getAll(String typeKey);
}
