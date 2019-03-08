package com.grosup.ttzy.share.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.dto.ResourceFileDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.resource.service.ResourceFileService;
import com.grosup.ttzy.share.dto.ResourceDto;
import com.grosup.ttzy.share.dto.ResourceShareDto;
import com.grosup.ttzy.share.service.ResourceShareService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/share")
public class ResourceShareController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceShareController.class);

	@Autowired
	ResourceFileService resourceFileService;
	
	@Autowired
	ResourceShareService resourceShareService;

	@Autowired
	ResourceDetailService resourceDetailService;

	@Autowired
	RoleDao roleDao;

	/**
	 * /rs/share/add.do
	 * 
	 * @param json {"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\",
	 *             \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 * @return [{"state":"error", "message":"错误消息"}]
	 *         示例：localhost:8080/practice/rs/share/add.do?json={"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\",
	 *         \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			ResourceShareDto ResourceShareDto = resourceShareService.add(json);
			messageMap.put("id", ResourceShareDto.getShareKey());
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("add " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/del.do
	 * 
	 * @param sharekey ：shareKey
	 * @return [{"state":"error", "message":"错误消息"}]
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String shareKey = request.getParameter("sharekey");
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			resourceShareService.del(shareKey);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
			log.error("del " + MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/update.do
	 * @param sharekey ：shareKey
	 * @param json {"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\",
	 *             \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 * @return [{"state":"error", "message":"错误消息"}]
	 */
	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String shareKey = request.getParameter("sharekey");
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			ResourceShareDto resourceShareDto = resourceShareService.get(shareKey);
			if (resourceShareDto != null) {
				String json = request.getParameter("json");
				if (!StringUtil.isNullOrEmpty(json)) {
					resourceShareService.update(shareKey, json);
					messageMap.put(STATE, STATE_SUCCESSFUL);
				} else {
					messageMap.put(STATE, STATE_ERROR);
					messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
					log.error("update "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
				}
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}
	
	/**
	 * /rs/share/get.do
	 * @param sharekey ：shareKey
	 * @return [{"data":"{\"lastTime\":1538382729506,\"resourceListJson\":\"[{\\\"typeKey\\\":\\\"RDf示例表ID\\\", \\\"resourceKey\\\":\\\"RDt示例值ID\\\"}]\",\"sendTime\":1538382729506,\"sendUser\":\"示例分享人\",\"shareKey\":\"RSrtemplatekey\",\"templateName\":\"default\"}","state":"successful"}]
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String shareKey = request.getParameter("sharekey");
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			ResourceShareDto resourceShareDto = resourceShareService.get(shareKey);
			if (resourceShareDto != null) {
				JSONArray resourceShareJson = JSONArray.fromObject(resourceShareDto);
				messageMap.put(DATA, resourceShareJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/getall.do
	 * 
	 * @return [{"data":"[{\"lastTime\":1538382729506,\"resourceListJson\":\"[{\\\"typeKey\\\":\\\"RDf示例表ID\\\",
	 *         \\\"resourceKey\\\":\\\"RDt示例值ID\\\"}]\",\"sendTime\":1538382729506,\"sendUser\":\"示例分享人\",\"shareKey\":\"RSrtemplatekey\",\"templateName\":\"default\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			Collection<ResourceShareDto> collection = resourceShareService.getAll();
			if (collection != null) {
				JSONArray resourceDefJson = JSONArray.fromObject(collection);
				messageMap.put(DATA, resourceDefJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_LIST_ETER);
				log.error("getAll " + MESSAGE_LIST_ETER);
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/getresource.do
	 * 
	 * @return [{"data":"[{\"lastTime\":1538382729506,\"resourceListJson\":\"[{\\\"typeKey\\\":\\\"RDf示例表ID\\\",
	 *         \\\"resourceKey\\\":\\\"RDt示例值ID\\\"}]\",\"sendTime\":1538382729506,\"sendUser\":\"示例分享人\",\"shareKey\":\"RSrtemplatekey\",\"templateName\":\"default\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/getresource.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getResource(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		
		String shareKey = request.getParameter("sharekey");
		if (!StringUtil.isNullOrEmpty(shareKey)) {
			ResourceShareDto resourceShareDto = resourceShareService.get(shareKey);
			if (resourceShareDto != null) {
		
				
				JSONArray jsonArray = JSONArray.fromObject(resourceShareDto.getResourceListJson());
				Collection<ResourceDto> resourceDtoList = (Collection<ResourceDto>) JSONArray.toCollection(jsonArray,
						ResourceDto.class);

				Collection<String> resourceList = new ArrayList<String>();
				for (ResourceDto resourceDto : resourceDtoList) {
					resourceList.add(resourceDto.getResourceKey());
				}
				Collection<ResourceDetailDto> collection = resourceDetailService.getByUser(resourceList);
				String value;
				for(ResourceDetailDto resourceDetailDto : collection)
				{
					value =  resourceDetailDto.getAttrValue();
					value = value.replaceAll("/rs/file/", "/rs/share/");
					resourceDetailDto.setAttrValue(value);
				}
				JSONArray resourceDefJson = JSONArray.fromObject(collection);
				messageMap.put(DATA, resourceDefJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "sharekey:\"" + shareKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "sharekey:\"" + shareKey + "\"");
		}
		
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	
	/**
	 * /rs/share/get.do
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
