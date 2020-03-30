package com.yksys.isystem.common.pojo;

import java.io.Serializable;
import lombok.Data;


/**
 * 邮件模板表
 * 
 * @author YuKai Fan
 * @create 2020-03-30 20:49:36
 */
@Data
public class EmailTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//邮箱模板标识
		private String id;
		//模板名称
		private String name;
		//模板编码
		private String code;
		//邮件配置id
		private String configId;
		//模板
		private String template;
		//模板参数
		private String params;
		//备注
		private String remark;
		//状态:0  已禁用 1 正在使用
		private Integer status;
	
}
