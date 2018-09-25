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
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.service.ResourceAttrService;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/attr")
public class ResourceAttrController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceAttrController.class);

	@Autowired
	ResourceAttrService resourceAttrService;

	@RequestMapping(value = "/create.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceAttrService.create(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("create " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
			resourceAttrService.add(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("add " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
			resourceAttrService.del(resourceKey);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
			log.error("del " + MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
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
			resourceAttrService.update(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("update " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
			ResourceAttrDto resourceAttrDto = resourceAttrService.get(resourceKey);
			if (resourceAttrDto != null) {
				JSONArray resourceAttrJson = JSONArray.fromObject(resourceAttrDto);
				messageMap.put(DATA, resourceAttrJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourceKey:\"" + resourceKey + "\"");
				log.error("get " + MESSAGE_DTO_ETER + "resourceKey:\"" + resourceKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
			log.error("update " + MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String typeKey = request.getParameter("typekey");
		if (!StringUtil.isNullOrEmpty(typeKey)) {
			Collection<ResourceAttrDto> collection = resourceAttrService.getAll(typeKey);
			if (collection != null) {
				JSONArray resourceAttrJson = JSONArray.fromObject(collection);
				messageMap.put(DATA, resourceAttrJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_LIST_ETER);
				log.error("getAll " + MESSAGE_LIST_ETER);
			}
		}else
		{
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
			log.error("getAll " + MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");	
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
