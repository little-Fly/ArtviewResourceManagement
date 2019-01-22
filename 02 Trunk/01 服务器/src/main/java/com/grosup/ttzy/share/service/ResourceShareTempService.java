package com.grosup.ttzy.share.service;

import java.util.Collection;

import com.grosup.ttzy.share.dto.ResourceShareTempDto;

public interface ResourceShareTempService {
	public void create(String json);

	public ResourceShareTempDto add(String json);

	public void del(String shareKey);

	public ResourceShareTempDto get(String shareKey);
	
	public Collection<ResourceShareTempDto> getAll();
}
