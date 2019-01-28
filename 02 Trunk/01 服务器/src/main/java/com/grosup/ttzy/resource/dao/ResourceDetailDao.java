package com.grosup.ttzy.resource.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.grosup.ttzy.resource.common.ResourceConstant;
import com.grosup.ttzy.resource.dto.ResourceDetailDto;
import com.grosup.ttzy.util.IdUtils;
import com.grosup.ttzy.util.StringUtil;

import net.sf.json.JSONArray;

@Component
public class ResourceDetailDao implements ResourceConstant {

	private static Logger log = Logger.getLogger(ResourceDetailDao.class);

	List<ResourceDetailDto> list = Collections.synchronizedList(new ArrayList<ResourceDetailDto>());

	private String resourceKey; // long Y 主键，资源Id
	private String attrKey; // varchar Y 主键，属性Key
	private int attrlen = 100; // varchar Y 资源长度
	private String typeKey; // varchar Y 资源类型Key
	private String attrName; // varchar Y 属性名
	private String attrLevel; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
	private String attrType; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
	private String attrValue; // varchar Y 属性值

	@PostConstruct
	public void initMethod() {

		resourceKey = RESOURCE_DETAIL + "示例值ID"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头1"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行1值1"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);
		resourceKey = RESOURCE_DETAIL + "示例值ID"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头2"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行1值2"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);
		resourceKey = RESOURCE_DETAIL + "示例值ID"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头3"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行1值3"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);

		resourceKey = RESOURCE_DETAIL + "示例值ID2"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头1"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2值1"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);
		resourceKey = RESOURCE_DETAIL + "示例值ID2"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头2"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "picture"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "/rs/file/getfile.do?filekey=RFlpicture"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);
		resourceKey = RESOURCE_DETAIL + "示例值ID2"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头3"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "video"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "/rs/file/getfile.do?filekey=RFlvideo"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);
		resourceKey = RESOURCE_DETAIL + "示例值ID2"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID4"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头4"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2值4"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_AVAILABLE);

		resourceKey = RESOURCE_DETAIL + "示例值ID2add"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头1"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2add值1"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_ADD);
		resourceKey = RESOURCE_DETAIL + "示例值ID2add"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头2"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "video"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "/rs/file/getfile.do?filekey=RFl示例视频"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_ADD);
		resourceKey = RESOURCE_DETAIL + "示例值ID2add"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头3"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2add值3"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_ADD);

		resourceKey = RESOURCE_DETAIL + "示例值ID2del"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头1"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2del值1"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_DEL);
		resourceKey = RESOURCE_DETAIL + "示例值ID2del"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头2"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2del值2"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_DEL);
		resourceKey = RESOURCE_DETAIL + "示例值ID2del"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头3"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2del值3"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_DEL);

		resourceKey = RESOURCE_DETAIL + "示例值ID2update"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID1"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头1"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2update值1"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_UPDATE);
		resourceKey = RESOURCE_DETAIL + "示例值ID2update"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID2"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头2"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2update值2"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_UPDATE);
		resourceKey = RESOURCE_DETAIL + "示例值ID2update"; // long Y 主键，资源Id
		attrKey = RESOURCE_ATTR + "示例表头ID3"; // varchar Y 主键，属性Key
		typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
		attrName = "示例表头3"; // varchar Y 属性名
		attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
		attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
		attrValue = "行2update值3"; // varchar Y 属性值
		initResourceDetailDto(RESOURCE_STATE_APPROVAL_UPDATE);
	}

	public void initResourceDetailDto(String state) {

		ResourceDetailDto resourceDetailDto = new ResourceDetailDto();
		resourceDetailDto.setTypeKey(typeKey);
		resourceDetailDto.setAttrKey(attrKey);
		resourceDetailDto.setResourceKey(resourceKey);
		resourceDetailDto.setAttrLevel(attrLevel);
		resourceDetailDto.setAttrName(attrName);
		resourceDetailDto.setAttrType(attrType);
		resourceDetailDto.setAttrValue(attrValue);
		list.add(resourceDetailDto);
		resourceDetailDto.setAttrState(state);
	}

	public Collection<ResourceDetailDto> create(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection<ResourceDetailDto> collection = (Collection<ResourceDetailDto>) JSONArray.toCollection(jsonArray,
				ResourceDetailDto.class);
		return collection;
	}

	public Collection<ResourceDetailDto> add(Collection<ResourceDetailDto> collection) {
		String resourceKey = RESOURCE_DETAIL + IdUtils.getIncreaseIdByCurrentTimeMillis();
		for (ResourceDetailDto resourceDetailDto : collection) {
			resourceDetailDto.setResourceKey(resourceKey);
			resourceDetailDto.setAttrState(RESOURCE_STATE_APPROVAL_ADD);
		}
		list.addAll(collection);
		return collection;
	}

	public void approvalAdd(String resourceKey, String approvalMess) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					if (RESOURCE_STATE_APPROVAL_ADD.equals(resourceDetailDto.getAttrState())) {
						resourceDetailDto.setAttrState(RESOURCE_STATE_AVAILABLE);
						resourceDetailDto.setApprovalMess(approvalMess);
					}
				}
			}
		} else {
			log.error("ResourceDetailDao approvalAdd is error. resourceKey is:" + resourceKey);
		}
	}

	public void approvalDel(String resourceKey, String approvalMess) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					if (RESOURCE_STATE_APPROVAL_DEL.equals(resourceDetailDto.getAttrState())) {
						resourceDetaillist.add(resourceDetailDto);
						resourceDetailDto.setApprovalMess(approvalMess);
					}
				}
			}
			list.removeAll(resourceDetaillist);
		} else {
			log.error("ResourceDetailDao approvalDel is error. resourceKey is:" + resourceKey);
		}
	}

	public void approvalUpdate(String resourceKey, String approvalMess) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
			boolean bflag = false;
			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					if (RESOURCE_STATE_APPROVAL_UPDATE.equals(resourceDetailDto.getAttrState())) {
						resourceDetailDto.setAttrState(RESOURCE_STATE_AVAILABLE);
						resourceDetailDto.setApprovalMess(approvalMess);
						bflag = true;
					} else {
						resourceDetaillist.add(resourceDetailDto);
					}
				}
			}
			if (bflag) {
				list.removeAll(resourceDetaillist);
			}
		} else {
			log.error("ResourceDetailDao approvalDel is error. resourceKey is:" + resourceKey);
		}
	}

	public void reject(String resourceKey, String approvalMess) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					resourceDetailDto.setAttrState(RESOURCE_STATE_APPROVA_REJECT);
					resourceDetailDto.setApprovalMess(approvalMess);
				}
			}
		} else {
			log.error("ResourceDetailDao reject is error. resourceKey is:" + resourceKey);
		}
	}

	public void del(String resourceKey) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {
			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					resourceDetailDto.setAttrState(RESOURCE_STATE_APPROVAL_DEL);
				}
			}
		} else {
			log.error("ResourceDetailDao del is error. resourceKey is:" + resourceKey);
		}
	}

	public void updateAttrName(String newAttrKey, String newAttrName)
	{
		for (ResourceDetailDto resourceDetailDto : list) {
			String attrKey = resourceDetailDto.getAttrKey();
			if(attrKey.equals(newAttrKey))
			{
				resourceDetailDto.setAttrName(newAttrName);
			}
		}
	}
	
	public void update(String resourceKey, Collection<ResourceDetailDto> collection) {
		if (!StringUtil.isNullOrEmpty(resourceKey)) {

			for (ResourceDetailDto resourceDetailDto : collection) {
				resourceDetailDto.setResourceKey(resourceKey);
				resourceDetailDto.setAttrState(RESOURCE_STATE_APPROVAL_UPDATE);
			}
			list.addAll(collection);
		} else {
			log.error("ResourceDetailDao update is error. resourceKey is:" + resourceKey);
		}
	}

	public List<ResourceDetailDto> get(String resourceKey) {
		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		if (!StringUtil.isNullOrEmpty(resourceKey)) {

			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					resourceDetaillist.add(resourceDetailDto);
				}
			}
		} else {
			log.error("ResourceDetailDao get is error. resourceKey is:" + resourceKey);
		}
		return resourceDetaillist;
	}

	public List<ResourceDetailDto> getByUser(String resourceKey) {
		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		if (!StringUtil.isNullOrEmpty(resourceKey)) {

			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
						if (!RESOURCE_LEVEL_2.equals(resourceDetailDto.getAttrLevel())) {
							resourceDetaillist.add(resourceDetailDto);
						}
					}
				}
			}
		} else {
			log.error("ResourceDetailDao get is error. resourceKey is:" + resourceKey);
		}
		return resourceDetaillist;
	}

	public List<ResourceDetailDto> getByAdmin(String resourceKey) {
		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		if (!StringUtil.isNullOrEmpty(resourceKey)) {

			for (ResourceDetailDto resourceDetailDto : list) {
				if (resourceKey.equals(resourceDetailDto.getResourceKey())) {
					if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
						resourceDetaillist.add(resourceDetailDto);
					}
				}
			}
		} else {
			log.error("ResourceDetailDao get is error. resourceKey is:" + resourceKey);
		}
		return resourceDetaillist;
	}

	public List<ResourceDetailDto> getByUser(Collection<String> resourceList) {
		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		for (String resourceKey : resourceList) {
			resourceDetaillist.addAll(getByUser(resourceKey));
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> getAll(String typeKey, int start, int len) {

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : list) {
			if (typeKey.equals(resourceDetailDto.getTypeKey())) {
				if (resourceKeylist.size() >= len && !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
					continue;
				}
				if (resourceKeybreaklist.size() >= start
						&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
					resourceDetaillist.add(resourceDetailDto);
					resourceKeylist.add(resourceDetailDto.getResourceKey());
					continue;
				}
				resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> getAllByUser(String typeKey, int start, int len) {

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : list) {
			if (typeKey.equals(resourceDetailDto.getTypeKey())) {
				if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
					if (!RESOURCE_LEVEL_2.equals(resourceDetailDto.getAttrLevel())) {
						if (resourceKeylist.size() >= len
								&& !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
							continue;
						}
						if (resourceKeybreaklist.size() >= start
								&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
							resourceDetaillist.add(resourceDetailDto);
							resourceKeylist.add(resourceDetailDto.getResourceKey());
							continue;
						}
						resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
					}
				}
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> getAllPending(String typeKey, int start, int len) {

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : list) {
			if (typeKey.equals(resourceDetailDto.getTypeKey())) {
				if (RESOURCE_STATE_APPROVAL_DEL.equals(resourceDetailDto.getAttrState())
						|| RESOURCE_STATE_APPROVAL_ADD.equals(resourceDetailDto.getAttrState())
						|| RESOURCE_STATE_APPROVAL_UPDATE.equals(resourceDetailDto.getAttrState())) {
					if (resourceKeylist.size() >= len
							&& !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
						continue;
					}
					if (resourceKeybreaklist.size() >= start
							&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
						resourceDetaillist.add(resourceDetailDto);
						resourceKeylist.add(resourceDetailDto.getResourceKey());
						continue;
					}
					resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
				}
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> getAllByAdmin(String typeKey, int start, int len) {

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : list) {
			if (typeKey.equals(resourceDetailDto.getTypeKey())) {
				if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
					if (resourceKeylist.size() >= len
							&& !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
						continue;
					}
					if (resourceKeybreaklist.size() >= start
							&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
						resourceDetaillist.add(resourceDetailDto);
						resourceKeylist.add(resourceDetailDto.getResourceKey());
						continue;
					}
					resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
				}
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> getAll(String typeKey) {

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		for (ResourceDetailDto resourceDetailDto : list) {
			if (typeKey.equals(resourceDetailDto.getTypeKey())) {
				resourceDetaillist.add(resourceDetailDto);
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> search(String typeKey, Map<String, String> searchKeyMap, int start, int len) {

		Collection<ResourceDetailDto> alllist = getAll(typeKey);
		Set<String> delResourceKeySet = new HashSet<String>();
		Set<String> addResourceKeySet = new HashSet<String>();
		String attrKey;
		String attrValue;
		String searchAttrValue;
		for (ResourceDetailDto resourceDetailDto : alllist) {

			attrKey = resourceDetailDto.getAttrKey();
			attrValue = resourceDetailDto.getAttrValue();
			if (attrKey != null) {
				searchAttrValue = searchKeyMap.get(attrKey);
				if (searchAttrValue == null) {
					continue;
				}
				if (attrValue != null && attrValue.indexOf(searchAttrValue) > -1) {
					addResourceKeySet.add(resourceDetailDto.getResourceKey());
				} else {
					delResourceKeySet.add(resourceDetailDto.getResourceKey());
				}
			}
		}
		addResourceKeySet.removeAll(delResourceKeySet);
		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : alllist) {
			if (addResourceKeySet.contains(resourceDetailDto.getResourceKey())) {
				if (resourceKeylist.size() >= len && !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
					continue;
				}
				if (resourceKeybreaklist.size() >= start
						&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
					resourceDetaillist.add(resourceDetailDto);
					resourceKeylist.add(resourceDetailDto.getResourceKey());
					continue;
				}
				resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
			}
		}
		return resourceDetaillist;
	}

	public Collection<ResourceDetailDto> searchByUser(String typeKey, Map<String, String> searchKeyMap, int start,
			int len) {

		Collection<ResourceDetailDto> alllist = getAll(typeKey);
		Set<String> delResourceKeySet = new HashSet<String>();
		Set<String> addResourceKeySet = new HashSet<String>();
		String attrKey;
		String attrValue;
		String searchAttrValue;
		for (ResourceDetailDto resourceDetailDto : alllist) {
			if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
				resourceKey = resourceDetailDto.getResourceKey();
				attrKey = resourceDetailDto.getAttrKey();
				attrValue = resourceDetailDto.getAttrValue();
				if (attrKey != null) {
					searchAttrValue = searchKeyMap.get(attrKey);
					if (searchAttrValue == null) {
						continue;
					}
					if (!RESOURCE_LEVEL_2.equals(resourceDetailDto.getAttrLevel())) {
						if (attrValue != null && attrValue.indexOf(searchAttrValue) > -1) {
							addResourceKeySet.add(resourceDetailDto.getResourceKey());
						} else {
							delResourceKeySet.add(resourceDetailDto.getResourceKey());
						}
					} else {
						delResourceKeySet.add(resourceDetailDto.getResourceKey());
					}
				}
			}
		}
		addResourceKeySet.removeAll(delResourceKeySet);

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : alllist) {
			if (addResourceKeySet.contains(resourceDetailDto.getResourceKey())) {
				if (!RESOURCE_LEVEL_2.equals(resourceDetailDto.getAttrLevel())) {
					if (resourceKeylist.size() >= len
							&& !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
						continue;
					}
					if (resourceKeybreaklist.size() >= start
							&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
						resourceDetaillist.add(resourceDetailDto);
						resourceKeylist.add(resourceDetailDto.getResourceKey());
						continue;
					}
					resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
				}
			}
		}
		return resourceDetaillist;

	}

	public Collection<ResourceDetailDto> searchByAdmin(String typeKey, Map<String, String> searchKeyMap, int start,
			int len) {

		Collection<ResourceDetailDto> alllist = getAll(typeKey);
		Set<String> delResourceKeySet = new HashSet<String>();
		Set<String> addResourceKeySet = new HashSet<String>();
		String attrKey;
		String attrValue;
		String searchAttrValue;
		for (ResourceDetailDto resourceDetailDto : alllist) {
			if (RESOURCE_STATE_AVAILABLE.equals(resourceDetailDto.getAttrState())) {
				attrKey = resourceDetailDto.getAttrKey();
				attrValue = resourceDetailDto.getAttrValue();
				if (attrKey != null) {
					searchAttrValue = searchKeyMap.get(attrKey);
					if (searchAttrValue == null) {
						continue;
					}
					if (attrValue != null && attrValue.indexOf(searchAttrValue) > -1) {
						addResourceKeySet.add(resourceDetailDto.getResourceKey());
					} else {
						delResourceKeySet.add(resourceDetailDto.getResourceKey());
					}
				}
			}
		}
		addResourceKeySet.removeAll(delResourceKeySet);

		List<ResourceDetailDto> resourceDetaillist = new ArrayList<ResourceDetailDto>();
		Set<String> resourceKeylist = new HashSet<String>();
		Set<String> resourceKeybreaklist = new HashSet<String>();
		for (ResourceDetailDto resourceDetailDto : alllist) {
			if (addResourceKeySet.contains(resourceDetailDto.getResourceKey())) {
				if (resourceKeylist.size() >= len && !resourceKeylist.contains(resourceDetailDto.getResourceKey())) {
					continue;
				}
				if (resourceKeybreaklist.size() >= start
						&& !resourceKeybreaklist.contains(resourceDetailDto.getResourceKey())) {
					resourceDetaillist.add(resourceDetailDto);
					resourceKeylist.add(resourceDetailDto.getResourceKey());
					continue;
				}
				resourceKeybreaklist.add(resourceDetailDto.getResourceKey());
			}
		}
		return resourceDetaillist;

	}

}
