package com.grosup.ttzy.resource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceFileDto;
import com.grosup.ttzy.resource.service.ResourceFileService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/file")
public class ResourceFileController implements MessageMapConstant {
	private static Logger log = Logger.getLogger(ResourceFileController.class);

	@Autowired
	ResourceFileService resourceFileService;

	@Autowired
	RoleDao roleDao;

	/**
	 * <form action="/rs/file/add.do?" method="post" enctype="multipart/form-data">
	 * 
	 * @param json {""}参见 /pages/testUpload.jsp
	 * @post File
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String json = request.getParameter("json");
			if (!StringUtil.isNullOrEmpty(json)) {
				ResourceFileDto resourceFileDto = resourceFileService.create(json);
				boolean bFlag = springUpload(request, resourceFileDto, messageMap);
				if (bFlag) {

					resourceFileService.add(resourceFileDto);
					messageMap.put(STATE, STATE_SUCCESSFUL);
					messageMap.put(DATA, resourceFileDto.getFileKey());
				} else {
					messageMap.put(STATE, STATE_ERROR);
					messageMap.put(MESSAGE, MESSAGE_FILE_ETER + "json:\"" + json + "\"");
					log.error("add " + MESSAGE_FILE_ETER + "json:\"" + json + "\"");
				}
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
				log.error("upload " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	public boolean springUpload(HttpServletRequest request, ResourceFileDto resourceFileDto,
			Map<String, String> messageMap) throws GrosupException {
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			long startTime = System.currentTimeMillis();
			// 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				String filenName = "";

				while (iter.hasNext()) {

					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						filenName = file.getOriginalFilename();
						// 上传
						try {
							resourceFileService.addFile(resourceFileDto, file, filenName);
						} catch (IllegalStateException e) {
							log.error(MESSAGE_FILE_ETER, e);
						} catch (IOException e) {
							log.error(MESSAGE_FILE_ETER, e);
						}
					}
				}
				long endTime = System.currentTimeMillis();
				log.info("springUpload time：" + String.valueOf(endTime - startTime) + "ms");
				return true;
			}
		} else {
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return false;
	}

	/**
	 * /rs/file/del.do
	 * 
	 * @param filekey ：fileKey
	 * @return ["state":"error", "message":"错误消息"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String fileKey = request.getParameter("filekey");
			if (!StringUtil.isNullOrEmpty(fileKey)) {
				resourceFileService.del(fileKey);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + fileKey + "\"");
				log.error("del " + MESSAGE_PARAM_ETER + "resourceKey:\"" + fileKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/file/get.do
	 * 
	 * @param filekey ：fileKey
	 * @return [{"data":"[{\"name\":\"示例表名\",\"remark\":\"示例表注释\",\"fileKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String fileKey = request.getParameter("filekey");
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			ResourceFileDto resourceFileDto = resourceFileService.get(fileKey);
			if (resourceFileDto != null) {
				JSONArray resourceFileJson = JSONArray.fromObject(resourceFileDto);
				messageMap.put(DATA, resourceFileJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourceKey:\"" + fileKey + "\"");
				log.error("get " + MESSAGE_DTO_ETER + "resourceKey:\"" + fileKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + fileKey + "\"");
			log.error("update " + MESSAGE_PARAM_ETER + "resourceKey:\"" + fileKey + "\"");
		}

		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/file/get.do
	 * 
	 * @param filekey ：fileKey
	 * @return [{"data":"[{\"name\":\"示例表名\",\"remark\":\"示例表注释\",\"fileKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		Collection<ResourceFileDto> list = resourceFileService.getAll();
		if (list != null) {
			JSONArray ResourceFileDtoJson = JSONArray.fromObject(list);
			messageMap.put(DATA, ResourceFileDtoJson.toString());
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} 

		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * 
	 * @param filekey:fileKey
	 * @return 文件流
	 * @throws Exception
	 */
	@RequestMapping(value = "/getfile.do", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request) throws Exception {
		String fileKey = request.getParameter("filekey");
		if (!StringUtil.isNullOrEmpty(fileKey)) {
			ResourceFileDto resourceFileDto = resourceFileService.get(fileKey);
			String fileName = resourceFileDto.getRelativePath();
			String directory = resourceFileDto.getDirectory();
			InputStream in = new FileInputStream(new File(directory + File.separator + fileName));// 将该文件加入到输入流之中
			byte[] body = null;
			body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
			in.read(body);// 读入到输入流里面

			fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");// 防止中文乱码
			HttpHeaders headers = new HttpHeaders();// 设置响应头
			headers.add("Content-Disposition", "attachment;filename=" + fileName);
			HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
			return response;
		}
		return null;
	}

}
