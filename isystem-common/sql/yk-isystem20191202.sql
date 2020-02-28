/*
Navicat MySQL Data Transfer

Source Server         : pingyougou
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : yk-isystem20191202

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-02-28 16:11:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2访问令牌';

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for `oauth_approvals`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2已授权客户端';

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `client_secret` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `scope` varchar(1024) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(2048) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('7gBZcbsC7kLIWCdELIl8nxcs', '$2a$10$4di0sSQdr9yk4uTKWtZqzedsxI8sWXoR67x.G.Qmy4K2L7ZaFQt6W', '', 'app', 'authorization_code,client_credentials,password', 'http://localhost:8888/login,http://localhost:8888/webjars/springfox-swagger-ui/o2c.html,http://www.baidu.com', '', '43200', '2592000', '{\"website\":\"http://www.baidu.com\",\"apiKey\":\"7gBZcbsC7kLIWCdELIl8nxcs\",\"secretKey\":\"0osTIhce7uPvDKHz6aa67bhCukaKoYl4\",\"appName\":\"平台用户认证服务器\",\"updateTime\":1562841065000,\"isPersist\":1,\"appOs\":\"\",\"appIcon\":\"\",\"developerId\":0,\"createTime\":1542016125000,\"appType\":\"server\",\"appDesc\":\"资源服务器\",\"appId\":\"1552274783265\",\"appNameEn\":\"open-cloud-uaa-admin-server\",\"status\":1}', '');
INSERT INTO `oauth_client_details` VALUES ('rOOM15Zbc3UFWgW2h71gRFvi', '$2a$10$NSb94sKA5i9kS/F0mUBehuBs9Gtvlv8wdxQOYMg3WxMRt0nyP2Xn.', '', 'userProfile', 'authorization_code,client_credentials,password', 'http://localhost:2222/login', '', '43200', '2592000', '{\"website\":\"http://www.baidu.com\",\"apiKey\":\"rOOM15Zbc3UFWgW2h71gRFvi\",\"secretKey\":\"2Iw2B0UCMYd1cZjt8Fpr4KJUx75wQCwW\",\"appName\":\"SSO单点登录DEMO\",\"updateTime\":1568611165000,\"isPersist\":1,\"appOs\":\"\",\"appIcon\":\"\",\"developerId\":0,\"createTime\":1542016125000,\"appType\":\"pc\",\"appDesc\":\"sso\",\"appId\":\"1552294656514\",\"appNameEn\":\"sso-demo\",\"status\":1}', '');

-- ----------------------------
-- Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2客户端令牌';

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2授权码';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2刷新令牌';

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_attachment`;
CREATE TABLE `tb_attachment` (
  `id` varchar(32) NOT NULL COMMENT '附件标识',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '所属标识',
  `attach_url` varchar(255) DEFAULT NULL COMMENT '文件地址url',
  `attach_origin_title` varchar(255) DEFAULT NULL COMMENT '文件原名',
  `attach_utily` varchar(255) DEFAULT NULL COMMENT '附件属性，例身份证证明',
  `attach_type` int(11) DEFAULT NULL COMMENT '附件类型0图片1文件2视频',
  `attach_name` varchar(20) DEFAULT NULL COMMENT '文件名',
  `attach_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `attach_postfix` varchar(32) DEFAULT NULL COMMENT '文件后缀',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_att_owner_id` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_authority_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_authority_role`;
CREATE TABLE `tb_authority_role` (
  `role_id` varchar(200) DEFAULT NULL COMMENT '角色标识',
  `authority_id` varchar(32) DEFAULT NULL COMMENT '权限标识',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `id` varchar(32) NOT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of tb_authority_role
-- ----------------------------
INSERT INTO `tb_authority_role` VALUES ('1', '2', null, null, null, null, '1218079153409101824');
INSERT INTO `tb_authority_role` VALUES ('1', '1', null, null, null, null, '1218079153757229056');
INSERT INTO `tb_authority_role` VALUES ('1', '5', null, null, null, null, '1218079153853698048');
INSERT INTO `tb_authority_role` VALUES ('1', '3', null, null, null, null, '1218079153971138560');
INSERT INTO `tb_authority_role` VALUES ('1', '4', null, null, null, null, '1218079154063413248');
INSERT INTO `tb_authority_role` VALUES ('2', '4', '1', null, null, null, '4');

-- ----------------------------
-- Table structure for `tb_gateway_route`
-- ----------------------------
DROP TABLE IF EXISTS `tb_gateway_route`;
CREATE TABLE `tb_gateway_route` (
  `id` varchar(32) NOT NULL COMMENT '路由标识',
  `route_name` varchar(200) DEFAULT NULL COMMENT '路由名称',
  `path` varchar(200) DEFAULT NULL COMMENT '路径',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务id',
  `url` varchar(32) DEFAULT NULL COMMENT '完整地址',
  `strip_prefix` int(4) DEFAULT '1' COMMENT '忽略前缀',
  `retryable` int(4) DEFAULT '0' COMMENT '0  不重试, 1  重试',
  `persist` int(4) DEFAULT '1' COMMENT '是否保留数据:0 否 1 是',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `route_desc` varchar(200) DEFAULT NULL COMMENT '路由描述',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关路由表';

-- ----------------------------
-- Records of tb_gateway_route
-- ----------------------------
INSERT INTO `tb_gateway_route` VALUES ('1', 'isystem-service-admin', '/admin/**', 'isystem-service-admin', null, '0', '0', '1', '1', '平台用户服务', '1', '2019-12-27 09:38:19', '1', '2019-12-27 09:38:21');
INSERT INTO `tb_gateway_route` VALUES ('1217290818918944768', 'test', 'test', null, null, '1', '0', '0', '0', 'test', null, null, null, null);
INSERT INTO `tb_gateway_route` VALUES ('1217290922174320640', 'test2', 'test2', null, null, '1', '0', '0', '0', 'test2', null, null, null, null);
INSERT INTO `tb_gateway_route` VALUES ('1233201866452635648', 'isystem-service-fileupload', '/fileupload/**', 'isystem-service-fileupload', null, '1', '0', '0', '1', '文件上传服务', null, null, null, null);
INSERT INTO `tb_gateway_route` VALUES ('2', 'isystem-auth-server', '/auth/**', 'isystem-auth-server', null, '0', '0', '1', '1', '平台认证服务', '1', '2019-12-27 09:39:20', '1', '2019-12-27 09:39:24');
INSERT INTO `tb_gateway_route` VALUES ('3', 'isystem-service-generate', '/generate/**', 'isystem-service-generate', null, '0', '0', '1', '1', '代码生成服务', '1', '2020-01-13 16:57:45', '1', '2020-01-13 16:57:48');
INSERT INTO `tb_gateway_route` VALUES ('4', 'isystem-service-system', '/system/**', 'isystem-service-system', null, '1', '0', '1', '1', '系统管理服务', '1', '2020-01-14 16:43:43', '1', '2020-01-14 16:43:45');

-- ----------------------------
-- Table structure for `tb_system_api`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_api`;
CREATE TABLE `tb_system_api` (
  `id` varchar(32) NOT NULL COMMENT '接口标识',
  `api_code` varchar(200) DEFAULT NULL COMMENT '接口编码',
  `api_name` varchar(200) DEFAULT NULL COMMENT '接口名称',
  `api_category` varchar(200) DEFAULT NULL COMMENT '接口分类',
  `api_desc` varchar(200) DEFAULT NULL COMMENT '接口描述',
  `request_method` varchar(200) DEFAULT NULL COMMENT '请求方式',
  `content_type` varchar(200) DEFAULT NULL COMMENT '响应类型',
  `class_name` varchar(200) DEFAULT NULL COMMENT '类名',
  `method_name` varchar(200) DEFAULT NULL COMMENT '方法名',
  `path` varchar(200) DEFAULT NULL COMMENT '路径',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务id',
  `persist` int(4) DEFAULT '1' COMMENT '是否保留数据:0 否 1 是',
  `auth` int(4) DEFAULT '1' COMMENT '是否需要认证:0 否 1 是',
  `open` int(4) DEFAULT '1' COMMENT '是否公开:0 否 1 是',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关api接口表';

-- ----------------------------
-- Records of tb_system_api
-- ----------------------------
INSERT INTO `tb_system_api` VALUES ('1', 'all', '全部', 'default', '所有请求', 'GET,POST,PUT,DELETE', null, null, null, '/**', 'isystem-zuul', '1', '1', '1', '1', '1', '2020-01-02 16:55:41', '1', '2020-01-02 16:55:44');
INSERT INTO `tb_system_api` VALUES ('1217621312277712896', 'getGatewayRoutes', '获取路由网关列表', 'gatewayRout', '获取所有路由网关集合(分页)', 'GET', null, null, null, '/api/gatewayRoute/getGatewayRoutes', 'isystem-service-system', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('1233202337129041920', 'fileUpload', '/api/fileupload/upload', 'default', '文件上传api', 'POST', null, 'FileUploadController', 'upload', '/api/fileupload/upload', 'isystem-service-fileupload', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('2', 'actuator', '监控端点', 'gatewayRout', '监控端点', 'POST', null, null, null, '/actuator/**', 'isystem-zuul', '1', '1', '1', '1', '1', '2020-01-02 16:56:36', '1', '2020-01-02 16:56:39');
INSERT INTO `tb_system_api` VALUES ('3', 'login', '登录获取用户访问令牌', 'auth', '基于oauth2密码模式登录,无需签名,返回access_token', 'POST', null, null, null, '/api/login/token', 'isystem-auth-server', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('4', 'userInfo', '获取当前用户信息', 'auth', '获取当前用户信息', 'GET', null, null, null, '/api/systemUserInfo/getUserProfile', 'isystem-auth-server', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('5', 'systemUserList', '获取用户列表', 'default', '获取用户列表', 'GET', null, null, null, '/api/systemUser/getSystemUsers', 'isystem-service-admin', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('6', 'getDBTables', '获取所有数据库表', 'default', '获取所有数据库表', 'GET', null, null, null, '/api/generate/getDBTables', 'isystem-service-generate', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `tb_system_api` VALUES ('7', 'generateCode', '生成代码', 'generate', '生成代码', 'POST,GET', null, null, null, '/api/generate/generateCode', 'isystem-service-generate', '1', '1', '1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for `tb_system_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_authority`;
CREATE TABLE `tb_system_authority` (
  `id` varchar(32) NOT NULL COMMENT '权限标识',
  `authority` varchar(200) DEFAULT NULL COMMENT '权限编码',
  `api_id` varchar(200) DEFAULT 'null' COMMENT 'api接口id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '菜单标识',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

-- ----------------------------
-- Records of tb_system_authority
-- ----------------------------
INSERT INTO `tb_system_authority` VALUES ('1', 'authority_system_manager', '1', '1', null, '1', '1', '2019-12-26 14:55:19', '1', '2019-12-26 14:55:23');
INSERT INTO `tb_system_authority` VALUES ('2', 'authority_generation_code', '7', '2', null, '1', '1', '2019-12-26 14:55:21', '1', '2019-12-26 14:55:25');
INSERT INTO `tb_system_authority` VALUES ('3', 'authority_login_token', '3', null, null, '1', null, null, null, null);
INSERT INTO `tb_system_authority` VALUES ('4', 'authority_user_info', '4', null, null, '1', null, null, null, null);
INSERT INTO `tb_system_authority` VALUES ('5', 'authority__db_tables', '6', null, null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for `tb_system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_menu`;
CREATE TABLE `tb_system_menu` (
  `id` varchar(32) NOT NULL COMMENT '菜单标识',
  `menu_name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(200) DEFAULT NULL COMMENT '菜单编码',
  `menu_desc` varchar(200) DEFAULT NULL COMMENT '菜单描述',
  `pid` varchar(32) DEFAULT NULL COMMENT '上级菜单id',
  `scheme` varchar(32) DEFAULT NULL COMMENT '路径前缀',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式:_self窗口内,_blank新窗口',
  `sort` int(4) DEFAULT NULL COMMENT '序号',
  `level` int(4) DEFAULT NULL COMMENT '等级',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `url` varchar(200) DEFAULT NULL COMMENT '链接',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标class',
  `service_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

-- ----------------------------
-- Records of tb_system_menu
-- ----------------------------
INSERT INTO `tb_system_menu` VALUES ('1', '系统管理', 'system', '系统管理', '0', '/', '_self', '1', '1', null, '1', '1', '2019-12-25 09:08:31', '1', '2019-12-25 09:08:34', null, null, 'isystem-service-admin');
INSERT INTO `tb_system_menu` VALUES ('2', '代码生成', 'generationCode', null, '1', '/', '_self', '1', '2', null, '1', '1', '2019-12-25 09:11:34', '1', '2019-12-25 09:11:37', 'system/generationCode', null, 'isystem-service-admin');

-- ----------------------------
-- Table structure for `tb_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role`;
CREATE TABLE `tb_system_role` (
  `id` varchar(32) NOT NULL COMMENT '角色标识',
  `role_name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of tb_system_role
-- ----------------------------
INSERT INTO `tb_system_role` VALUES ('1', 'admin', null, '1', null, null, null, null, 'admin');
INSERT INTO `tb_system_role` VALUES ('2', 'customer', null, '1', null, null, null, null, 'customer');

-- ----------------------------
-- Table structure for `tb_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user` (
  `id` varchar(32) NOT NULL COMMENT '用户标识',
  `account` varchar(200) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '用户昵称',
  `user_icon` varchar(255) DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(2) DEFAULT NULL COMMENT '性别 1男  0女',
  `marry_flag` int(2) DEFAULT NULL COMMENT '婚否',
  `education` int(2) DEFAULT NULL COMMENT '学历',
  `phone` varchar(200) DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `prov` varchar(32) DEFAULT NULL COMMENT '省级',
  `city` varchar(32) DEFAULT NULL COMMENT '地市级',
  `dist` varchar(32) DEFAULT NULL COMMENT '区县',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `idcard` varchar(200) DEFAULT NULL COMMENT '身份证号',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_marry` (`marry_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES ('1', 'admin', 'admin', '$2a$10$r621AGI63YNyGaLGWwc8peYkJUGeZix/371IsEx6l9vIOH.Ay0Wvy', null, null, 'http://i1.sinaimg.cn/ent/d/2008-06-04/U105P28T3D2048907F326DT20080604225106.jpg', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tb_system_user` VALUES ('1202427935047421952', 'fyk', null, '$2a$10$r621AGI63YNyGaLGWwc8peYkJUGeZix/371IsEx6l9vIOH.Ay0Wvy', '6960230573805098', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tb_system_user` VALUES ('1202866887747309568', 'test', 'test', '$2a$10$G7CkvGcfurX5eLvp6Z.b5.MkAPY/8bptff1dMSxntTpb/yOjSdfq2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(200) DEFAULT NULL COMMENT '用户标识',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色标识',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1', '1', '2019-12-25 16:28:16', '1', '2019-12-25 16:28:19');
INSERT INTO `tb_user_role` VALUES ('2', '1202427935047421952', '2', '1', '2020-01-11 10:36:26', '1', '2020-01-11 10:36:29');
