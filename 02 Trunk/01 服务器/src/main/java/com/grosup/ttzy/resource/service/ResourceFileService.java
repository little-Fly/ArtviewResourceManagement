package com.grosup.ttzy.resource.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.grosup.ttzy.resource.dto.ResourceFileDto;

public interface ResourceFileService {
	public ResourceFileDto create(String json);

	public void add(String json);

	public void add(ResourceFileDto resourceFileDto);

	public void del(String fileKey);
	
	public void del4def(String typeKey);
	
	public void del4detail(String resourceKey);
	
	public void update(String json);

	public ResourceFileDto get(String fileKey);

	public Collection<ResourceFileDto> getAll();

	public void addFile(ResourceFileDto resourceFileDto,MultipartFile multipartFile, String name) throws IllegalStateException, IOException;
}
