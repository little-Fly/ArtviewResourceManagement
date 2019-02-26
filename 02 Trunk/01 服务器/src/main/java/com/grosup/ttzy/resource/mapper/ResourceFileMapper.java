package com.grosup.ttzy.resource.mapper;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.resource.dto.ResourceFileDto;

@Repository
public interface ResourceFileMapper {

	public void add(ResourceFileDto resourceFileDto);

	public void del(String fileKey);

	public void update(ResourceFileDto resourceFileDto);

	public ResourceFileDto get(String fileKey);

	public Collection<ResourceFileDto> getAll();
}
