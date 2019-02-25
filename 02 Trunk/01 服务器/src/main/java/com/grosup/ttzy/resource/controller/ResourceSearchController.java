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

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/rs/search")
public class ResourceSearchController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceSearchController.class);

	@Autowired
	ResourceDetailService resourceDetailService;

	@Autowired
	RoleDao roleDao;

	/**
	 * /rs/search/search.do
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return {"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 *         示例URL：localhost:8080/practice/rs/search/search.do?typekey=RDf示例表ID&searchkey={"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/search.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, Object> messageMap = new HashMap<String, Object>();
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
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String typeKey = request.getParameter("typekey");
			String searchKey = request.getParameter("searchkey");
			if (!StringUtil.isNullOrEmpty(typeKey) && !StringUtil.isNullOrEmpty(searchKey)) {
				JSONObject jsonObject = JSONObject.fromObject(searchKey);

				Map<String, String> searchKeyMap = (Map<String, String>) JSONObject.toBean(jsonObject, Map.class);

				Collection<ResourceDetailDto> collection = resourceDetailService.search(typeKey, searchKeyMap, start,
						len);
				if (collection != null) {
					JSONArray resourceDetailJson = JSONArray.fromObject(collection);
					messageMap.put(DATA, resourceDetailJson.toString());
					int total = resourceDetailService.getSearchTotal(typeKey, searchKeyMap);
					messageMap.put(TOTAL, total);
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
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/search/searchbyuser.do
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return {"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 *         示例URL：localhost:8080/practice/rs/search/search.do?typekey=RDf示例表ID&searchkey={"searchKeyMap":"[{\"RDt示例值ID\":\"1234\"}]"}
	 * @throws GrosupException 
	 */
	@RequestMapping(value = "/searchbyuser.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String searchByUser(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, Object> messageMap = new HashMap<String, Object>();
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

			Collection<ResourceDetailDto> collection;
			
			if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
				collection = resourceDetailService.searchByAdmin(typeKey, searchKeyMap, start, len);
				int total = resourceDetailService.getSearchTotalByAdmin(typeKey, searchKeyMap);
				messageMap.put(TOTAL, total);
			} else {
				collection = resourceDetailService.searchByUser(typeKey, searchKeyMap, start, len);
				int total = resourceDetailService.getSearchTotalByUser(typeKey, searchKeyMap);
				messageMap.put(TOTAL, total);
			}
			
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
