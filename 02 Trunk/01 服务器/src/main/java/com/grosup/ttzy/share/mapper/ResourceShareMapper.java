package com.grosup.ttzy.share.mapper;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.share.dto.ResourceShareDto;

@Repository
public interface ResourceShareMapper {

	public void add(ResourceShareDto resourceShareDto);

	public void del(String shareKey);

	public void update(ResourceShareDto resourceShareDto);

	public ResourceShareDto get(String shareKey);

	public Collection<ResourceShareDto> getAll();
}
