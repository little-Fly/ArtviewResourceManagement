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
import com.grosup.ttzy.resource.dto.ResourceAttrDto;
import com.grosup.ttzy.resource.service.ResourceAttrService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/attr")
public class ResourceAttrController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceAttrController.class);

	@Autowired
	ResourceAttrService resourceAttrService;

	@Autowired
	RoleDao roleDao;

	@RequestMapping(value = "/create.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String json = request.getParameter("json");
			if (!StringUtil.isNullOrEmpty(json)) {
				resourceAttrService.create(json);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
				log.error("create " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
	 * /rs/attr/add.do
	 * 
	 * @param json ："[{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","remark":"示例表头备注","typeKey":"RDf示例表ID"}]"
	 * @return ["state":"successful"}]
	 *         参见示例请求：localhost:8080/practice/rs/attr/getall.do?typekey=RDf示例表ID
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String json = request.getParameter("json");
			if (!StringUtil.isNullOrEmpty(json)) {
				resourceAttrService.add(json);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
				log.error("add " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
	 * /rs/attr/del.do
	 * 
	 * @param attkey ：attrKey
	 * @return ["state":"successful"}]
	 *         参见示例请求：localhost:8080/practice/rs/attr/getall.do?typekey=RDf示例表ID
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String attrKey = request.getParameter("attrkey");
			if (!StringUtil.isNullOrEmpty(attrKey)) {
				resourceAttrService.del(attrKey);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "attrKey:\"" + attrKey + "\"");
				log.error("del " + MESSAGE_PARAM_ETER + "attrKey:\"" + attrKey + "\"");
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
	 * /rs/attr/update.do
	 * 
	 * @param json ："[{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","remark":"示例表头备注","typeKey":"RDf示例表ID"}]"
	 * @return ["state":"successful"}]
	 *         参见示例请求：localhost:8080/practice/rs/attr/getall.do?typekey=RDf示例表ID
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String json = request.getParameter("json");
			if (!StringUtil.isNullOrEmpty(json)) {
				resourceAttrService.update(json);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
				log.error("update " + MESSAGE_PARAM_ETER + "json:\"" + json + "\"");
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
	 * /rs/attr/get.do
	 * 
	 * @param attkey ：attrKey
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"remark\":\"示例表头备注\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 *         参见示例请求：localhost:8080/practice/rs/attr/getall.do?typekey=RDf示例表ID
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String attrKey = request.getParameter("attrkey");
		if (!StringUtil.isNullOrEmpty(attrKey)) {
			ResourceAttrDto resourceAttrDto = resourceAttrService.get(attrKey);
			if (resourceAttrDto != null) {
				JSONArray resourceAttrJson = JSONArray.fromObject(resourceAttrDto);
				messageMap.put(DATA, resourceAttrJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "attrKey:\"" + attrKey + "\"");
				log.error("get " + MESSAGE_DTO_ETER + "attrKey:\"" + attrKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "attrKey:\"" + attrKey + "\"");
			log.error("get " + MESSAGE_PARAM_ETER + "attrKey:\"" + attrKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/attr/getAll.do
	 * 
	 * @param typekey ：typeKey
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"remark\":\"示例表头备注\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 *         参见示例请求：localhost:8080/practice/rs/attr/getall.do?typekey=RDf示例表ID
	 */
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
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
			log.error("getAll " + MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
