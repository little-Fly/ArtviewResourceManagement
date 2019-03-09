/*
Navicat MySQL Data Transfer

Source Server         : 47.92.166.33
Source Server Version : 50635
Source Host           : 47.92.166.33:3306
Source Database       : ttzy

Date: 2018-11-25 10:45:32
*/


/*
-- ----------------------------
-- Table structure for resource_def
-- 	private String typeKey = RESOURCE_DEF + "示例表ID";	//varchar	Y	主键，资源类型Key
--	private String logoUrl = "/pages/share/model.jpg";//示例图片
--	private String name = "示例表名";	//varchar	Y	资源类型名称
--	private String remark = "示例表注释";	//varchar	N	备注
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_def`;
CREATE TABLE `resource_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `typekey` varchar(100) NOT NULL COMMENT '资源类型Key',
  `logourl` varchar(1000) DEFAULT NULL COMMENT '资源logoURL',
  `name` varchar(200) NOT NULL COMMENT '资源类型名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_def
-- ----------------------------*/
INSERT INTO `resource_def` VALUES ('1', 'RESOURCE_DEF示例表ID', 'RESOURCE_DETAILpicture', '示例表名', '示例表注释', '预留1', '预留2', '预留3', '预留4', '预留5');

/*
-- ----------------------------
-- Table structure for resource_attr
--	private String attrKey = RESOURCE_ATTR + "示例表头ID"; // varchar Y 主键，属性Key
--	private String typeKey = RESOURCE_DEF + "示例表ID"; // varchar Y 资源类型Key
--  private int attrlen; // varchar Y 资源长度
--	private String attrName = "示例表头"; // varchar Y 属性名
--	private String attrLevel = "0"; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
--	private float attrPosition = 0; // varchar Y 资源位置
--	private String attrType = "default"; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
--	private String remark = "示例表头备注"; // varchar N 备注
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_attr`;
CREATE TABLE `resource_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `attrkey` varchar(100) NOT NULL COMMENT '资源属性Key',
  `typekey` varchar(1000) NOT NULL COMMENT '资源类型Key',
  `attrlen` int(11) DEFAULT NULL COMMENT '资源长度',
  `attrname` varchar(200) NOT NULL COMMENT '资源属性名',
  `attrlevel` varchar(200) NOT NULL COMMENT '属性级别',
  `attrposition` float(35) DEFAULT NULL COMMENT '资源位置',
  `attrtype` varchar(200) DEFAULT NULL COMMENT '属性类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_attr
-- ----------------------------*/
INSERT INTO `resource_attr` VALUES ('1', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', 100, '示例表头1', '0', 1, 'default', '示例表头备注1', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_attr` VALUES ('2', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', 100, '示例表头2', '0', 2, 'default', '示例表头备注2', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_attr` VALUES ('3', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', 100, '示例表头3', '0', 4, 'default', '示例表头备注3', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_attr` VALUES ('4', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', 100, '示例表头4', '0', 3, 'default', '示例表头备注4', '预留1', '预留2', '预留3', '预留4', '预留5');

/*
-- ----------------------------
-- Table structure for resource_detail
--	private String resourceKey; // varchar Y 资源Key
--	private String attrKey; // varchar Y 属性Key
--	private String typeKey; // varchar Y 资源类型Key
--	private String attrName; // varchar Y 属性名
--	private String attrLevel; // int Y 属性级别 0:任何人可见1:成员可见 2:管理员可见
--	private String attrType; // varchar Y 属性类型 默认：直接读取图片：根据值从图片库中获取
--	private String attrValue; // varchar Y 属性值
--	private String attrState; // varchar Y 状态
--	private String attrLastState; // varchar Y 状态
--	private String approvalUser;// 審批人
--	private String approvalMess;// 審批意見
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_detail`;
CREATE TABLE `resource_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `resourcekey` varchar(100) NOT NULL COMMENT '资源Key',
  `attrkey` varchar(100) NOT NULL COMMENT '资源属性Key',
  `typekey` varchar(100) DEFAULT NULL COMMENT '资源类型Key',
  `attrname` varchar(200) DEFAULT NULL COMMENT '属性名',
  `attrlevel` varchar(11) DEFAULT NULL COMMENT '属性级别',
  `attrtype` varchar(100) DEFAULT NULL COMMENT '属性类型',
  `attrvalue` text DEFAULT NULL COMMENT '属性值',
  `attrstate` varchar(100) DEFAULT NULL COMMENT '状态',
  `attrlaststate` varchar(100) DEFAULT NULL COMMENT 'last状态',
  `approvaluser` varchar(100) DEFAULT NULL COMMENT '審批人',
  `approvalmess` varchar(200) DEFAULT NULL COMMENT '審批意見',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_detail
-- ----------------------------*/
INSERT INTO `resource_detail` VALUES ('1', 'RESOURCE_DETAIL示例值ID1', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', '示例表头1', '0', 'default', '行1值1', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('2', 'RESOURCE_DETAIL示例值ID1', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', '0', 'default', '行1值2', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('3', 'RESOURCE_DETAIL示例值ID1', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', '0', 'default', '行1值3', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('4', 'RESOURCE_DETAIL示例值ID1', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', '示例表头4', '2', 'default', '行1值4', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('5', 'RESOURCE_DETAIL示例值ID2', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', '示例表头1', '0', 'default', '行2值1', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('6', 'RESOURCE_DETAIL示例值ID2', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', '0', 'picture', '/rs/file/getfile.do?filekey=RESOURCE_DETAILpicture', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('7', 'RESOURCE_DETAIL示例值ID2', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', '0', 'video', '/rs/file/getfile.do?filekey=RESOURCE_DETAILvideo', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('8', 'RESOURCE_DETAIL示例值ID2', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', '示例表头4', '2', 'default', '行2值4', 'Available', 'ApprovalAdd', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('9', 'RESOURCE_DETAIL示例值ID3', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', '示例表头1', '0', 'default', '行3值1', 'ApprovalAdd', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('10', 'RESOURCE_DETAIL示例值ID3', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', '0', 'default', '行3值2', 'ApprovalAdd', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('11', 'RESOURCE_DETAIL示例值ID3', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', '0', 'picture', '/rs/file/getfile.do?filekey=RESOURCE_DETAILpicture', 'ApprovalAdd', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('12', 'RESOURCE_DETAIL示例值ID3', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', '示例表头4', '2', 'video', '/rs/file/getfile.do?filekey=RESOURCE_DETAILvideo', 'ApprovalAdd', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('13', 'RESOURCE_DETAIL示例值ID4', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', '示例表头1', '0', 'video', '/rs/file/getfile.do?filekey=RESOURCE_DETAILvideo', 'ApprovalDel', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('14', 'RESOURCE_DETAIL示例值ID4', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', '0', 'default', '行4值2', 'ApprovalDel', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('15', 'RESOURCE_DETAIL示例值ID4', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', '0', 'default', '行4值3', 'ApprovalDel', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('16', 'RESOURCE_DETAIL示例值ID4', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', '示例表头4', '2', 'picture', '/rs/file/getfile.do?filekey=RESOURCE_DETAILpicture', 'ApprovalDel', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('17', 'RESOURCE_DETAIL示例值ID5', 'RESOURCE_ATTR示例表头ID1', 'RESOURCE_DEF示例表ID', '示例表头1', '0', 'picture', '/rs/file/getfile.do?filekey=RESOURCE_DETAILpicture', 'ApprovalUpdate', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('18', 'RESOURCE_DETAIL示例值ID5', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', '0', 'video', '/rs/file/getfile.do?filekey=RESOURCE_DETAILvideo', 'ApprovalUpdate', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('19', 'RESOURCE_DETAIL示例值ID5', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', '0', 'default', '行5值3', 'ApprovalUpdate', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_detail` VALUES ('20', 'RESOURCE_DETAIL示例值ID5', 'RESOURCE_ATTR示例表头ID4', 'RESOURCE_DEF示例表ID', '示例表头4', '2', 'default', '行5值4', 'ApprovalUpdate', '', '系统添加', '', '预留1', '预留2', '预留3', '预留4', '预留5');


/*
-- ----------------------------
-- Table structure for resource_file
--	private String resourceKey; // long Y 资源Id
--	private String attrKey; // varchar Y 属性Key
--	private String typeKey; // varchar Y 资源类型Key
--	private String attrName; // varchar Y 属性名
--	private String fileKey; // varchar Y 主键，filekey
--	private String directory; // varchar Y 存储的目录
--	private String relativePath; // varchar 相对路径
--	private long lastmodifytime; // long Y 最后修改时间
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_file`;
CREATE TABLE `resource_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `resourcekey` varchar(100) NOT NULL COMMENT '资源Key',
  `attrkey` varchar(100) NOT NULL COMMENT '资源属性Key',
  `typekey` varchar(100) DEFAULT NULL COMMENT '资源类型Key',
  `attrname` varchar(200) DEFAULT NULL COMMENT '属性名',
  `filekey` varchar(100) NOT NULL COMMENT 'filekey',
  `directory` varchar(255) DEFAULT NULL COMMENT '存储的目录',
  `relativepath` varchar(255) DEFAULT NULL COMMENT '相对路径',
  `lastmodifytime` varchar(100) DEFAULT NULL COMMENT '最后修改时间',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_file
-- ----------------------------*/
INSERT INTO `resource_file` VALUES ('1', 'RESOURCE_DETAIL示例值ID2', 'RESOURCE_ATTR示例表头ID2', 'RESOURCE_DEF示例表ID', '示例表头2', 'RESOURCE_DETAILpicture', '', 'RFl1381901148036.jpg', '0', '预留1', '预留2', '预留3', '预留4', '预留5');
INSERT INTO `resource_file` VALUES ('2', 'RESOURCE_DETAIL示例值ID3', 'RESOURCE_ATTR示例表头ID3', 'RESOURCE_DEF示例表ID', '示例表头3', 'RESOURCE_DETAILvideo', '', 'RFlEP40.mp4', '0', '预留1', '预留2', '预留3', '预留4', '预留5');

/*
-- ----------------------------
-- Table structure for resource_share
--	private String shareKey; //分享ID，用于访问和获取
--	private String shareName; //分享ID，用于访问和获取
--	private String resourceListJson; //分享的资源列表Json格式
--	private String sendUser;		//分享人
--	private String templateName;		//模板名称
--	private long sendTime;			//最后一次发布时间
--	private long LastTime;			//最近更新时间，用于做双校验
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_share`;
CREATE TABLE `resource_share` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `sharekey` varchar(100) NOT NULL COMMENT '分享Key',
  `sharename` varchar(100) DEFAULT NULL COMMENT '分享名称',
  `resourcelistjson` text DEFAULT NULL COMMENT '资源列表',
  `senduser` varchar(100) DEFAULT NULL COMMENT '分享人',
  `templatename` varchar(100) NOT NULL COMMENT '模板名称',
  `sendtime` varchar(100) DEFAULT NULL COMMENT '发布时间',
  `lasttime` varchar(100) DEFAULT NULL COMMENT '最后更新时间',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_share
-- ----------------------------*/
INSERT INTO `resource_share` VALUES ('1', 'RESOURCE_SHAREtemplatekey', '示例分享', '[{"typeKey":"RDf示例表ID", "resourceKey":"RDt示例值ID"}, {"typeKey":"RDf示例表ID", "resourceKey":"RDt示例值ID2"}]', '示例分享人', 'template0', '0', '0', '预留1', '预留2', '预留3', '预留4', '预留5');

/*
-- ----------------------------
-- Table structure for resource_sharetemp
--	private String tempKey; //模板ID
--	private String tempName; //模板名稱
--	private String tempFilePath; //模板filepath
-- ----------------------------*/
DROP TABLE IF EXISTS `resource_sharetemp`;
CREATE TABLE `resource_sharetemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，ID',
  `tempKey` varchar(100) NOT NULL COMMENT '分享模板Key',
  `tempName` varchar(100) NOT NULL COMMENT '分享模板名称',
  `tempFilePath` varchar(255) DEFAULT NULL COMMENT '模板所在路径',
  `rf1` varchar(200) DEFAULT NULL COMMENT '预留1',
  `rf2` varchar(200) DEFAULT NULL COMMENT '预留2',
  `rf3` varchar(200) DEFAULT NULL COMMENT '预留3',
  `rf4` varchar(200) DEFAULT NULL COMMENT '预留4',
  `rf5` varchar(200) DEFAULT NULL COMMENT '预留5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*
-- ----------------------------
-- Records of resource_sharetemp
-- ----------------------------*/
INSERT INTO `resource_sharetemp` VALUES ('1', 'RESOURCE_SHAREtemplatekey', 'default模板', '/pages/share/template/template0.jsp', '预留1', '预留2', '预留3', '预留4', '预留5');


