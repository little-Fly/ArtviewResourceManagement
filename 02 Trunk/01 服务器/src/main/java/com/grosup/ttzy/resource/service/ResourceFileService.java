package com.grosup.ttzy.resource.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.grosup.ttzy.resource.dto.ResourceFileDto;

public interface ResourceFileService {
	public ResourceFileDto create(String json);

	public void add(String json);

	public void add(ResourceFileDto resourceFileDto);

	public void del(String resourceKey);

	public void update(String json);

	public ResourceFileDto get(String resourceKey);

	public Collection<ResourceFileDto> getAll();

	public void addFile(ResourceFileDto resourceFileDto,MultipartFile multipartFile, String name) throws IllegalStateException, IOException;
}
