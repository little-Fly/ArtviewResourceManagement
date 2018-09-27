package com.grosup.ttzy.resource.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/detail")
public class ResourceDetailController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceDetailController.class);

	@Autowired
	ResourceDetailService resourceDetailService;

	@RequestMapping(value = "/create.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceDetailService.create(json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
			log.error("create " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/detail/add.do
	 * @param json [{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","attrValue":"值","resourceKey":"RDt示例值ID","typeKey":"RDf示例表ID"}]]
	 * @return ["state":"successful"}]
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			resourceDetailService.add(json);
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
	 * /rs/detail/del.do
	 * @param resourcekey resourceKey
	 * @return ["state":"successful"}]
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			resourceDetailService.del(resourceKey);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
			log.error("del " + MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}
	
	/**
	 * /rs/detail/update.do
	 * @param json [{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","attrValue":"值","resourceKey":"RDt示例值ID","typeKey":"RDf示例表ID"}]]
	 * @return ["state":"successful"}]
	 */
	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		String json = request.getParameter("json");

		if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(json)) {
			resourceDetailService.update(resourceKey, json);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE,
					MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"" + " json:\"" + json + "\"");
			log.error(
					"update " + MESSAGE_PARAM_ETER + "resourceKey:\"" + resourceKey + "\"" + " json:\"" + json + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/detail/get.do
	 * @param resourcekey resourceKey
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			List<ResourceDetailDto> resourceDetailList = resourceDetailService.get(resourceKey);
			if (resourceDetailList != null) {
				JSONArray resourceDetailJson = JSONArray.fromObject(resourceDetailList);
				messageMap.put(DATA, resourceDetailJson.toString());
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

	/**
	 * /rs/detail/getall.do
	 * @param typekey typeKey
	 * @param start start
	 * @param len len
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		int start = 0;
		int len = 10;
		String startStr = request.getParameter("start");
		String lenStr = request.getParameter("len");
		try {
			start = Integer.parseInt(startStr);
		} catch (NumberFormatException e) {
			log.error("update " + MESSAGE_PARAM_ETER + "startStr:\"" + startStr + "\"");
		}
		try {
			len = Integer.parseInt(lenStr);
		} catch (NumberFormatException e) {
			log.error("update " + MESSAGE_PARAM_ETER + "lenStr:\"" + lenStr + "\"");
		}
		String typeKey = request.getParameter("typekey");
		if (!StringUtil.isNullOrEmpty(typeKey)) {

			Collection<ResourceDetailDto> collection = resourceDetailService.getAll(typeKey, start, len);
			if (collection != null) {
				JSONArray resourceDetailJson = JSONArray.fromObject(collection);
				messageMap.put(DATA, resourceDetailJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_LIST_ETER);
				log.error("getAll " + MESSAGE_LIST_ETER);
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
			log.error("getAll " + MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
