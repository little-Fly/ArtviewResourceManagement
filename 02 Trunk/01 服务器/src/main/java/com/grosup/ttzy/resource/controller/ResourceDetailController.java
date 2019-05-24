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

import com.grosup.ttzy.dao.RoleDao;
import com.grosup.ttzy.resource.common.MessageMapConstant;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.resource.service.ResourceDetailService;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/rs/detail")
public class ResourceDetailController implements MessageMapConstant {

	private static Logger log = Logger.getLogger(ResourceDetailController.class);

	@Autowired
	ResourceDetailService resourceDetailService;

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
				Collection<ResourceDetailDto> list = resourceDetailService.create(json);
				if (null != list) {
					JSONArray jsonlist = JSONArray.fromObject(messageMap);
					messageMap.put(OBJECT, jsonlist.toString());
					messageMap.put(STATE, STATE_SUCCESSFUL);
				}else
				{
					messageMap.put(STATE, STATE_ERROR);
					messageMap.put(MESSAGE, MESSAGE_JSON_ETER + "json:\"" + json + "\"");
					log.error("create " + MESSAGE_JSON_ETER + "json:\"" + json + "\"");
				}
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
	 * /rs/detail/add.do
	 * 
	 * @param json [{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","attrValue":"值","resourceKey":"RDt示例值ID","typeKey":"RDf示例表ID"}]]
	 * @return ["state":"successful"}]
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
				Collection<ResourceDetailDto> list = resourceDetailService.add(json, String.valueOf(TtzyUtil.getUser(request)));
				if (null != list) {
					JSONArray jsonlist = JSONArray.fromObject(list);
					messageMap.put(OBJECT, jsonlist.toString());
					messageMap.put(STATE, STATE_SUCCESSFUL);
				}else
				{
					messageMap.put(STATE, STATE_ERROR);
					messageMap.put(MESSAGE, MESSAGE_JSON_ETER + "json:\"" + json + "\"");
					log.error("create " + MESSAGE_JSON_ETER + "json:\"" + json + "\"");
				}
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
	 * /rs/detail/approvaladd.do 需要有管理员权限
	 * 
	 * @param resourcekey  resourceKey
	 * @param approvalmess approvalMess
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/approvaladd.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String approvalAdd(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			String approvalMess = request.getParameter("approvalmess");
			if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(approvalMess)) {
				resourceDetailService.approvalAdd(resourceKey, approvalMess, String.valueOf(TtzyUtil.getUser(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
				log.error("del " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
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
	 * /rs/detail/approvaldel.do 需要有管理员权限
	 * 
	 * @param resourcekey  resourceKey
	 * @param approvalmess approvalMess
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/approvaldel.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String approvalDel(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			String approvalMess = request.getParameter("approvalmess");
			if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(approvalMess)) {
				resourceDetailService.approvalDel(resourceKey, approvalMess, String.valueOf(TtzyUtil.getUid(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
				log.error("del " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
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
	 * /rs/detail/approvalupdate.do 需要有管理员权限
	 * 
	 * @param resourcekey  resourceKey
	 * @param approvalmess approvalMess
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/approvalupdate.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String approvalUpdate(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			String approvalMess = request.getParameter("approvalmess");
			if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(approvalMess)) {
				resourceDetailService.approvalUpdate(resourceKey, approvalMess, String.valueOf(TtzyUtil.getUser(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
				log.error("del " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
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
	 * /rs/detail/reject.do 需要有管理员权限
	 * 
	 * @param resourcekey  resourceKey
	 * @param approvalmess approvalMess
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/reject.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reject(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();

		if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			String approvalMess = request.getParameter("approvalmess");
			if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(approvalMess)) {
				resourceDetailService.reject(resourceKey, approvalMess, String.valueOf(TtzyUtil.getUser(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\" " + "approvalmess:\""
						+ approvalMess + "\" ");
				log.error("del " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + "approvalmess:\""
						+ approvalMess + "\" ");
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
	 * /rs/detail/getallpending.do 获取所有待审批的资源 需要有管理员权限
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/getallpending.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllPending(HttpServletRequest request, HttpServletResponse response) throws GrosupException {

		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
			int start = 0;
			int len = 10;
			String startStr = request.getParameter("start");
			String lenStr = request.getParameter("len");
			try {
				start = Integer.parseInt(startStr);
			} catch (NumberFormatException e) {
				log.error("getAll " + MESSAGE_PARAM_ETER + "startStr:\"" + startStr + "\"");
			}
			try {
				len = Integer.parseInt(lenStr);
			} catch (NumberFormatException e) {
				log.error("getAll " + MESSAGE_PARAM_ETER + "lenStr:\"" + lenStr + "\"");
			}
			String typeKey = request.getParameter("typekey");
			if (!StringUtil.isNullOrEmpty(typeKey)) {

				Collection<ResourceDetailDto> collection = resourceDetailService.getAllPending(typeKey, start, len);

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
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/detail/del.do
	 * 
	 * @param resourcekey resourceKey
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/del.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			if (!StringUtil.isNullOrEmpty(resourceKey)) {
				resourceDetailService.del(resourceKey, String.valueOf(TtzyUtil.getUser(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
				log.error("del " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
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
	 * /rs/detail/update.do
	 * 
	 * @param json [{"attrKey":"RAt示例表头ID","attrLevel":"0","attrName":"示例表头","attrType":"default","attrValue":"值","resourceKey":"RDt示例值ID","typeKey":"RDf示例表ID"}]]
	 * @return ["state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/update.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			String json = request.getParameter("json");

			if (!StringUtil.isNullOrEmpty(resourceKey) && !StringUtil.isNullOrEmpty(json)) {
				resourceDetailService.update(resourceKey, json, String.valueOf(TtzyUtil.getUser(request)));
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE,
						MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + " json:\"" + json + "\"");
				log.error("update " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"" + " json:\"" + json
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
	 * /rs/detail/get.do
	 * 
	 * @param resourcekey resourceKey
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/get.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			String resourceKey = request.getParameter("resourcekey");
			if (!StringUtil.isNullOrEmpty(resourceKey)) {
				List<ResourceDetailDto> resourceDetailList = resourceDetailService.get(resourceKey);
				if (resourceDetailList != null) {
					JSONArray resourceDetailJson = JSONArray.fromObject(resourceDetailList);
					messageMap.put(DATA, resourceDetailJson.toString());
					messageMap.put(STATE, STATE_SUCCESSFUL);
				} else {
					messageMap.put(STATE, STATE_ERROR);
					messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourcekey:\"" + resourceKey + "\"");
					log.error("get " + MESSAGE_DTO_ETER + "resourcekey:\"" + resourceKey + "\"");
				}
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
				log.error("get " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
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
	 * /rs/detail/getbyuser.do
	 * 
	 * @param resourcekey resourceKey
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/getbyuser.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getByUser(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, String> messageMap = new HashMap<String, String>();
		String resourceKey = request.getParameter("resourcekey");
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			List<ResourceDetailDto> resourceDetailList;
			if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
				resourceDetailList = resourceDetailService.getByAdmin(resourceKey);
			} else {
				resourceDetailList = resourceDetailService.getByUser(resourceKey);
			}
			if (resourceDetailList != null) {
				JSONArray resourceDetailJson = JSONArray.fromObject(resourceDetailList);
				messageMap.put(DATA, resourceDetailJson.toString());
				messageMap.put(STATE, STATE_SUCCESSFUL);
			} else {
				messageMap.put(STATE, STATE_ERROR);
				messageMap.put(MESSAGE, MESSAGE_DTO_ETER + "resourcekey:\"" + resourceKey + "\"");
				log.error("get " + MESSAGE_DTO_ETER + "resourcekey:\"" + resourceKey + "\"");
			}
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
			log.error("get " + MESSAGE_PARAM_ETER + "resourcekey:\"" + resourceKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/detail/getall.do
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/getall.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		if (roleDao.isWriter(TtzyUtil.getUid(request))) {
			int start = 0;
			int len = 10;
			String startStr = request.getParameter("start");
			String lenStr = request.getParameter("len");
			try {
				start = Integer.parseInt(startStr);
			} catch (NumberFormatException e) {
				log.error("getAll " + MESSAGE_PARAM_ETER + "startStr:\"" + startStr + "\"");
			}
			try {
				len = Integer.parseInt(lenStr);
			} catch (NumberFormatException e) {
				log.error("getAll " + MESSAGE_PARAM_ETER + "lenStr:\"" + lenStr + "\"");
			}
			String typeKey = request.getParameter("typekey");
			if (!StringUtil.isNullOrEmpty(typeKey)) {

				Collection<ResourceDetailDto> collection = resourceDetailService.getAll(typeKey, start, len);
				if (collection != null) {
					JSONArray resourceDetailJson = JSONArray.fromObject(collection);
					messageMap.put(DATA, resourceDetailJson.toString());
					messageMap.put(STATE, STATE_SUCCESSFUL);
					int total = resourceDetailService.getAllTotal(typeKey);
					messageMap.put(TOTAL, total);
					
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
		} else {
			messageMap.put(STATE, STATE_ERROR);
			messageMap.put(MESSAGE, MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
			log.error("add " + MESSAGE_AUTHORITY_ETER + TtzyUtil.getUid(request));
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

	/**
	 * /rs/detail/getallbyuser.do
	 * 
	 * @param typekey typeKey
	 * @param start   start
	 * @param len     len
	 * @return [{"data":"[{\"attrKey\":\"RAt示例表头ID\",\"attrLevel\":\"0\",\"attrName\":\"示例表头\",\"attrType\":\"default\",\"attrValue\":\"值\",\"resourceKey\":\"RDt示例值ID\",\"typeKey\":\"RDf示例表ID\"}]","state":"successful"}]
	 * @throws GrosupException
	 */
	@RequestMapping(value = "/getallbyuser.do", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllByUser(HttpServletRequest request, HttpServletResponse response) throws GrosupException {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		int start = 0;
		int len = 10;
		String startStr = request.getParameter("start");
		String lenStr = request.getParameter("len");
		try {
			start = Integer.parseInt(startStr);
		} catch (NumberFormatException e) {
			log.error("getAll " + MESSAGE_PARAM_ETER + "startStr:\"" + startStr + "\"");
		}
		try {
			len = Integer.parseInt(lenStr);
		} catch (NumberFormatException e) {
			log.error("getAll " + MESSAGE_PARAM_ETER + "lenStr:\"" + lenStr + "\"");
		}
		String typeKey = request.getParameter("typekey");
		if (!StringUtil.isNullOrEmpty(typeKey)) {

			Collection<ResourceDetailDto> collection;
			if (roleDao.isAdmin(TtzyUtil.getUid(request))) {
				collection = resourceDetailService.getAllByAdmin(typeKey, start, len);
				int total = resourceDetailService.getAllTotalByAdmin(typeKey);
				messageMap.put(TOTAL, total);
			} else {
				collection = resourceDetailService.getAllByUser(typeKey, start, len);
				int total = resourceDetailService.getAllTotalByUser(typeKey);
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
			messageMap.put(MESSAGE, MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
			log.error("getAll " + MESSAGE_PARAM_ETER + "typeKey:\"" + typeKey + "\"");
		}
		JSONArray jsonobj = JSONArray.fromObject(messageMap);
		return jsonobj.toString();
	}

}
