package com.grosup.ttzy.share.mapper;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.share.dto.ResourceShareTempDto;

@Repository
public interface ResourceShareTempMapper {

	public void add(ResourceShareTempDto resourceShareTempDto);

	public void del(String tempKey);

	public void update(ResourceShareTempDto resourceShareTempDto);

	public ResourceShareTempDto get(String tempKey);

	public Collection<ResourceShareTempDto> getAll();
}
