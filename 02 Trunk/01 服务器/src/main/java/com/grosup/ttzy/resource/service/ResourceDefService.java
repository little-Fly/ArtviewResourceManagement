package com.grosup.ttzy.resource.service;

import java.util.Collection;

import com.grosup.ttzy.resource.dto.ResourceDefDto;

public interface ResourceDefService {
	public void create(String json);

	public void add(String json);

	public void del(String resourceKey);

	public void update(String json);

	public ResourceDefDto get(String resourceKey);

	public Collection<ResourceDefDto> getAll();
}
