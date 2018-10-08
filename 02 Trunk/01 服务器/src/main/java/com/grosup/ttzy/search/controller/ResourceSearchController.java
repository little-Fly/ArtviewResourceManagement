package com.grosup.ttzy.search.controller;

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
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/rs/search")
public class ResourceSearchController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceSearchController.class);

	@Autowired
	ResourceDetailService resourceDetailService;

	/**
	 * /rs/search/search.do
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return {"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 * 示例URL：localhost:8080/practice/rs/search/search.do?typekey=RDf示例表ID&searchkey={"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 */
	@RequestMapping(value = "/search.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		int start = 0;
		int len = 10;
		String startStr = request.getParameter("start");
		String lenStr = request.getParameter("len");
		try {
			start = Integer.parseInt(startStr);
		} catch (NumberFormatException e) {
			log.error("search " + MESSAGE_PARAM_ETER + "startStr:\"" + startStr + "\"");
		}
		try {
			len = Integer.parseInt(lenStr);
		} catch (NumberFormatException e) {
			log.error("search " + MESSAGE_PARAM_ETER + "lenStr:\"" + lenStr + "\"");
		}
		String typeKey = request.getParameter("typekey");
		String searchKey = request.getParameter("searchkey");
		if (!StringUtil.isNullOrEmpty(typeKey) && !StringUtil.isNullOrEmpty(searchKey)) {
			JSONObject jsonObject = JSONObject.fromObject(searchKey);
			
			Map<String, String> searchKeyMap = (Map<String, String>) JSONObject.toBean(jsonObject, Map.class);
			
			Collection<ResourceDetailDto> collection = resourceDetailService.search(typeKey, searchKeyMap, start, len);
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
			messageMap.put(MESSAGE,
					MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"" + ", searchKey:\"" + searchKey + "\"");
			log.error("getAll " + MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"" + ", searchKey:\"" + searchKey
					+ "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
