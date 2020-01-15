package com.yksys.isystem.common.vo;

import java.io.Serializable;

import com.google.common.base.Converter;
import lombok.Data;
import com.yksys.isystem.common.pojo.SystemApi;
import org.springframework.beans.BeanUtils;


/**
 * 网关api接口表 vo类
 * 
 * @author YuKai Fan
 * @create 2020-01-15 20:59:15
 */
@Data
public class SystemApiVo implements Serializable {
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
	
	public SystemApi convert() {
			SystemApiVoConvert systemApiVoConvert = new SystemApiVoConvert();
		SystemApi systemApi = systemApiVoConvert.convert(this);
		return systemApi;
	}

	private static class SystemApiVoConvert extends Converter<SystemApiVo, SystemApi> {

		@Override
		protected SystemApi doForward(SystemApiVo systemApiVo) {
			SystemApi systemApi = new SystemApi();
			BeanUtils.copyProperties(systemApiVo, systemApi);
			return systemApi;
		}

		@Override
		protected SystemApiVo doBackward(SystemApi systemApi) {
			return null;
		}
	}

}
