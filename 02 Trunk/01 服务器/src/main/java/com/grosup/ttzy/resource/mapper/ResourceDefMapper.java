package com.grosup.ttzy.resource.mapper;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.grosup.ttzy.resource.dto.ResourceDefDto;

@Repository
public interface ResourceDefMapper {

	public void add(ResourceDefDto resourceDefDto);

	public void del(String typeKey);

	public void update(ResourceDefDto resourceDefDto);

	public ResourceDefDto get(String typeKey);

	public Collection<ResourceDefDto> getAll();
}
