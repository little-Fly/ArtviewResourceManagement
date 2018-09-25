package com.grosup.ttzy.resource.controller;

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

import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceDefDto;
import com.grosup.ttzy.resource.service.ResourceDefService;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping ( "/rs/def" )
public class ResourceDefController implements MessageMapConstant{

	private static Logger log = Logger.getLogger(ResourceDefController.class);

	@Autowired
	ResourceDefService resourceDefService;

	@RequestMapping(value = "/create.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceDefService.create(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("create "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceDefService.add(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("add "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			resourceDefService.del(resourceKey);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
			log.error("del "+MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceDefService.update(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			ResourceDefDto resourceDefDto = resourceDefService.get(resourceKey);
			if (resourceDefDto != null) {
				JSONArray resourceDefJson = JSONArray.fromObject(resourceDefDto);
				messageMap.put(DATA, resourceDefJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourceKey:\"" + resourceKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "resourceKey:\"" + resourceKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		Collection<ResourceDefDto> collection = resourceDefService.getAll();
		if (collection != null) {
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
