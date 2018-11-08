package com.grosup.ttzy.resource.service.Imp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grosup.ttzy.resource.dao.ResourceFileDao;
import com.grosup.ttzy.resource.dto.ResourceFileDto;
import com.grosup.ttzy.resource.service.ResourceFileService;
import com.grosup.ttzy.util.SpringPropertyConfigurer;
import com.grosup.ttzy.util.StringUtil;

@Service
public class ResourceFileServiceImp implements ResourceFileService {

	private static Logger log = Logger.getLogger(ResourceFileServiceImp.class);

	@Autowired
	private ResourceFileDao resourceFileDao;

	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

	private static final String FILE_SPLIT_CHAR = ";";

	@Autowired
	private Environment env;

	@Value("#{resourceFileService['ResourceFileService.uploadDirectory']}")
	private String uploadDirectory;

	@Value("#{resourceFileService['ResourceFileService.delDirectory']}")
	private String delDirectory;

	public ResourceFileDto create(String json) {
		return resourceFileDao.create(json);
	}

	public void add(String json) {
		ResourceFileDto resourceFileDto = resourceFileDao.create(json);
		if (resourceFileDto != null) {
			resourceFileDao.add(resourceFileDto);
		} else {
			log.error("resourceFileService add resourceFileDto is null. fileKey json:" + json);
		}

	}

	public void add(ResourceFileDto resourceFileDto) {
		if (resourceFileDto != null) {
			resourceFileDao.add(resourceFileDto);
		} else {
			log.error("resourceFileService add resourceFileDto is null. fileKey resourceFileDto:" + resourceFileDto);
		}
	}

	public void del(String fileKey) {
		ResourceFileDto resourceFileDto = resourceFileDao.del(fileKey);
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			delFile(resourceFileDto.getDirectory(), resourceFileDto.getRelativePath());
		}
	}

	public void update(String json) {
		ResourceFileDto resourceFileDto = resourceFileDao.create(json);
		if (resourceFileDto != null) {
			resourceFileDao.update(resourceFileDto);
		} else {
			log.error("resourceFileService update resourceFileDto is null. fileKey json:" + json);
		}
	}

	public ResourceFileDto get(String fileKey) {
		ResourceFileDto resourceFileDto = resourceFileDao.get(fileKey);
		if (StringUtil.isNullOrEmpty(resourceFileDto.getDirectory())) {
			resourceFileDto.setDirectory(getUploadDirectory());
		}
		return resourceFileDto;
	}

	public Collection<ResourceFileDto> getAll() {
		return resourceFileDao.getAll();
	}

	public String getUploadDirectory() {
		return uploadDirectory;
	}

	public String getDelDirectory() {
		return delDirectory;
	}

	public void delFile(String directory, String relativePath) {
		String[] filelist = relativePath.split(FILE_SPLIT_CHAR);
		File file;
		for (String fileName : filelist) {
			file = new File(directory + File.pathSeparator + fileName);
			if (!file.isFile()) {
				file = new File(getUploadDirectory() + File.pathSeparator + fileName);
			}
			if (file.isFile()) {
				moveToDelDirectory(file);
			}
		}
	}

	public void addFile(ResourceFileDto resourceFileDto, MultipartFile multipartFile, String filename)
			throws IllegalStateException, IOException {
		String name = filename;
		String name2 = "";
		int index = name.lastIndexOf(".");
		if (index > -1) {
			name2 = name.substring(index);
			name = name.substring(0, index);
		}
		String path = getUploadDirectory();
		String relativePath = name + name2;
		File afile = new File(path + File.separator + relativePath);
		int time = 0;
		while (afile.isFile()) {
			relativePath = name + "(" + (++time) + ")" + name2;
			afile = new File(path + File.separator + relativePath);
		}
		afile.mkdirs();
		multipartFile.transferTo(afile);
		resourceFileDto.setDirectory(getUploadDirectory());
		resourceFileDto.setRelativePath(relativePath);
	}

	public void moveToDelDirectory(File f) {
		String name = f.getName();
		String name2 = "";
		int index = name.lastIndexOf(".");
		if (index > -1) {
			name2 = name.substring(index);
			name = name.substring(0, index);
		}
		String path = getDelDirectory();
		String date = sdf1.format(new Date());
		File afile = new File(path + File.separator + date + File.separator + name + name2);
		int time = 0;
		while (afile.isFile()) {
			afile = new File(path + File.separator + date + File.separator + name + "(" + (++time) + ")" + name2);
		}
		afile.mkdirs();
		f.renameTo(afile);
	}

}
