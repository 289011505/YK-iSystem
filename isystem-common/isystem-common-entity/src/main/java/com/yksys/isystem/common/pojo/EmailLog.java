package com.yksys.isystem.common.pojo;

import java.io.Serializable;
import lombok.Data;


/**
 * 邮件日志表
 * 
 * @author YuKai Fan
 * @create 2020-03-30 20:49:36
 */
@Data
public class EmailLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//邮箱日志标识
		private String id;
		//邮件主题名称
		private String subject;
		//接收人邮箱
		private String recipients;
		//抄送人邮箱
		private String cc;
		//邮件内容
		private String content;
		//附件路径
		private String attachment;
		//发送次数
		private Integer sendNum;
		//错误信息
		private String error;
		//结果 0：失败 1：成功
		private Integer result;
		//发送配置
		private String config;
		//模板id
		private String tplId;
		//备注
		private String remark;
		//状态:0  已禁用 1 正在使用
		private Integer status;
	
}
