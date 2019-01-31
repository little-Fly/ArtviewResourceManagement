package com.grosup.ttzy.resource.service;

import java.util.Collection;

import com.grosup.ttzy.resource.dto.ResourceDefDto;

public interface ResourceDefService {
	public ResourceDefDto create(String json);

	public ResourceDefDto add(String json);

	public ResourceDefDto del(String resourceKey);

	public ResourceDefDto update(String typeKey, String json);

	public ResourceDefDto get(String resourceKey);

	public Collection<ResourceDefDto> getAll();
}
