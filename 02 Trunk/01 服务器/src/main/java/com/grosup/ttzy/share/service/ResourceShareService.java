package com.grosup.ttzy.share.service;


import java.util.Collection;

import com.grosup.ttzy.share.dto.ResourceShareDto;

public interface ResourceShareService {
	public void create(String json);

	public void add(String json);

	public void del(String shareKey);

	public void update(String json);

	public ResourceShareDto get(String shareKey);
	
	public Collection<ResourceShareDto> getAll();

}
