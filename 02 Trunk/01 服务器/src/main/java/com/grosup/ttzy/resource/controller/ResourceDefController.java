package com.grosup.ttzy.resource.controller;

import java.io.UnsupportedEncodingException;
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
import com.grosup.ttzy.resource.dto.ResourceDefDto;
import com.grosup.ttzy.resource.service.ResourceDefService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/def")
public class ResourceDefController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceDefController.class);

	@Autowired
	ResourceDefService resourceDefService;

	@Autowired
	RoleDao roleDao;

	@RequestMapping(value = "/create.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			log.error("request.getCharacterEncoding(): " + request.getCharacterEncoding());
			log.error("request.getParameter(\"json\"): " + request.getParameter("json"));
			String json = request.getParameter("json");
//		String json= "";
//		try {
//			json = new String(request.getParameter("json").getBytes("iso-8859-1"), "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
			log.error("new String(request.getParameter(\"json\").getBytes(\"iso-8859-1\"), \"utf-8\"): "
					+ (new String(json.getBytes("iso-8859-1"), "utf-8")));
			if (!StringUtil.isNullOrEmpty(json)) {
				resourceDefService.create(json);
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
	 * /rs/def/add.do
	 * 
	 * @param json [{"name":"示例表名","remark":"示例表注释","typeKey":"RDf示例表ID"}]
	 * @return ["state":"error", "message":"错误消息"}]
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
				resourceDefService.add(json);
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
	 * /rs/def/del.do
	 * 
	 * @param typekey ：typeKey
	 * @return ["state":"error", "message":"错误消息"}]
	 * @throws GrosupException 
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String typeKey = request.getParameter("typekey");
			if (!StringUtil.isNullOrEmpty(typeKey)) {
				resourceDefService.del(typeKey);
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + typeKey + "\"");
				log.error("del " + MESSAGE_PARAM_ETER + "resourceKey:\"" + typeKey + "\"");
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
	 * /rs/def/update.do
	 * 
	 * @param json [{"name":"示例表名","remark":"示例表注释","typeKey":"RDf示例表ID"}]
	 * @return ["state":"error", "message":"错误消息"}]
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
				resourceDefService.update(json);
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
	 * /rs/def/get.do
	 * 
	 * @param typekey ：typeKey
	 * @return [{"data":"[{\"name\":\"示例表名\",\"remark\":\"示例表注释\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String typeKey = request.getParameter("typekey");
		if (!StringUtil.isNullOrEmpty(typeKey)) {
			ResourceDefDto resourceDefDto = resourceDefService.get(typeKey);
			if (resourceDefDto != null) {
				JSONArray resourceDefJson = JSONArray.fromObject(resourceDefDto);
				messageMap.put(DATA, resourceDefJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourceKey:\"" + typeKey + "\"");
				log.error("get " + MESSAGE_DTO_ETER + "resourceKey:\"" + typeKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourceKey:\"" + typeKey + "\"");
			log.error("get " + MESSAGE_PARAM_ETER + "resourceKey:\"" + typeKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/def/getAll.do
	 * 
	 * @return [{"data":"[{\"name\":\"示例表名\",\"remark\":\"示例表注释\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 */
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
			log.error("getAll " + MESSAGE_LIST_ETER);
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
