package com.grosup.ttzy.share.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.share.dto.ResourceDto;
import com.grosup.ttzy.share.dto.ResourceShareDto;
import com.grosup.ttzy.share.service.ResourceShareService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping ( "/rs/share" )
public class ResourceShareController implements MessageMapConstant{

	private static Logger log = Logger.getLogger(ResourceShareController.class);

	@Autowired
	ResourceShareService resourceShareService;

	@Autowired
	ResourceDetailService resourceDetailService;
	
	@Autowired
	RoleDao roleDao;
	
	/**
	 * /rs/share/add.do
	 * @param json {"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\", \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 * @return [{"state":"error", "message":"错误消息"}]
	 * 示例：localhost:8080/practice/rs/share/add.do?json={"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\", \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceShareService.add(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("add "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/del.do
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
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "shareKey:\"" + shareKey + "\"");
			log.error("del "+MESSAGE_PARAM_ETER + "shareKey:\"" + shareKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/update.do
	 * @param json {"resourceListJson":"[{\"typeKey\":\"RDf示例表ID\", \"resourceKey\":\"RDt示例值ID\"}]","templateName":"default"}
	 * @return [{"state":"error", "message":"错误消息"}]
	 */
	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceShareService.update(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "shareKey:\"" + shareKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "shareKey:\"" + shareKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "shareKey:\"" + shareKey + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "shareKey:\"" + shareKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/share/getall.do
	 * @return [{"data":"[{\"lastTime\":1538382729506,\"resourceListJson\":\"[{\\\"typeKey\\\":\\\"RDf示例表ID\\\", \\\"resourceKey\\\":\\\"RDt示例值ID\\\"}]\",\"sendTime\":1538382729506,\"sendUser\":\"示例分享人\",\"shareKey\":\"RSrtemplatekey\",\"templateName\":\"default\"}]","state":"successful"}]
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
				log.error("getAll "+MESSAGE_LIST_ETER);
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}
	
	/**
	 * /rs/share/getall.do
	 * @return [{"data":"[{\"lastTime\":1538382729506,\"resourceListJson\":\"[{\\\"typeKey\\\":\\\"RDf示例表ID\\\", \\\"resourceKey\\\":\\\"RDt示例值ID\\\"}]\",\"sendTime\":1538382729506,\"sendUser\":\"示例分享人\",\"shareKey\":\"RSrtemplatekey\",\"templateName\":\"default\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/getresource.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getResource(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourceKey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			JSONArray jsonArray = JSONArray.fromObject(resourceKey);
			Collection<ResourceDto> resourceDtoList = (Collection<ResourceDto>) JSONArray.toCollection(jsonArray,
					ResourceDto.class);
			
			Collection<String> resourceList =new ArrayList<String>();
			for(ResourceDto resourceDto: resourceDtoList)
			{
				resourceList.add(resourceDto.getResourceKey());
			}
			Collection<ResourceDetailDto> collection = resourceDetailService.getByUser(resourceList);
			JSONArray resourceDefJson = JSONArray.fromObject(collection);
			messageMap.put(DATA, resourceDefJson.toString());
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_LIST_ETER);
			log.error("getAll "+MESSAGE_LIST_ETER);
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}
	
}
