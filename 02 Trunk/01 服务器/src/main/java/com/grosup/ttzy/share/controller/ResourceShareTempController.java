package com.grosup.ttzy.share.controller;

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
import com.grosup.ttzy.share.dto.ResourceShareTempDto;
import com.grosup.ttzy.share.service.ResourceShareTempService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping ( "/rs/sharetemp" )
public class ResourceShareTempController implements MessageMapConstant{

	private static Logger log = Logger.getLogger(ResourceShareTempController.class);

	@Autowired
	ResourceShareTempService resourceShareTempService;
	
	@Autowired
	RoleDao roleDao;
	
	/**
	 * /rs/sharetemp/add.do
	 * @param json {"tempFilePath":"template0.jsp","tempKey":"RSTdefault","tempName":"default模板"}
	 * @return [{"state":"error", "message":"错误消息"}]
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String json = request.getParameter("json");
		if (!StringUtil.isNullOrEmpty(json)) {
			ResourceShareTempDto ResourceShareTempDto = resourceShareTempService.add(json);
			messageMap.put("id", ResourceShareTempDto.getTempKey());
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
	 * /rs/sharetemp/del.do
	 * @param tempkey ：tempKey
	 * @return [{"state":"error", "message":"错误消息"}]
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String tempKey = request.getParameter("tempkey");
		if (!StringUtil.isNullOrEmpty(tempKey)) {
			resourceShareTempService.del(tempKey);
			messageMap.put(STATE, STATE_SUCCESSFUL);
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "tempkey:\"" + tempKey + "\"");
			log.error("del "+MESSAGE_PARAM_ETER + "tempkey:\"" + tempKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	
	/**
	 * /rs/sharetemp/get.do
	 * @param tempkey ：tempKey
	 * @return [{"data":"[{\"tempFilePath\":\"template0.jsp\",\"tempKey\":\"RSTdefault\",\"tempName\":\"default模板\"}]","state":"successful"}]
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> messageMap = new HashMap<String, String>();
		String tempKey = request.getParameter("tempkey");
		if (!StringUtil.isNullOrEmpty(tempKey)) {
			ResourceShareTempDto resourceShareTempDto = resourceShareTempService.get(tempKey);
			if (resourceShareTempDto != null) {
				JSONArray resourceShareTempJson = JSONArray.fromObject(resourceShareTempDto);
				messageMap.put(DATA, resourceShareTempJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "tempkey:\"" + tempKey + "\"");
				log.error("get "+MESSAGE_DTO_ETER + "tempkey:\"" + tempKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "tempkey:\"" + tempKey + "\"");
			log.error("update "+MESSAGE_PARAM_ETER + "tempkey:\"" + tempKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/sharetemp/getall.do
	 * @return [{"data":"[{\"tempFilePath\":\"template0.jsp\",\"tempKey\":\"RSTdefault\",\"tempName\":\"default模板\"}]","state":"successful"}]
	 * @throws GrosupException 
	 */
	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			Collection<ResourceShareTempDto> collection = resourceShareTempService.getAll();
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
	
}
