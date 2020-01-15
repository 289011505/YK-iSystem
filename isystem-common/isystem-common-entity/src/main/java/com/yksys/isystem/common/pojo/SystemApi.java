package com.yksys.isystem.common.pojo;

import java.io.Serializable;
import lombok.Data;


/**
 * 网关api接口表
 * 
 * @author YuKai Fan
 * @create 2020-01-15 20:59:15
 */
@Data
public class SystemApi implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//接口标识
	private String id;
	//接口编码
	private String apiCode;
	//接口名称
	private String apiName;
	//接口分类
	private String apiCategory;
	//接口描述
	private String apiDesc;
	//请求方式
	private String requestMethod;
	//响应类型
	private String contentType;
	//类名
	private String className;
	//方法名
	private String methodName;
	//路径
	private String path;
	//服务id
	private String serviceId;
	//是否保留数据:0 否 1 是
	private Integer persist;
	//是否需要认证:0 否 1 是
	private Integer auth;
	//是否公开:0 否 1 是
	private Integer open;
	//状态:0  已禁用 1 正在使用
	private Integer status;
	
}
