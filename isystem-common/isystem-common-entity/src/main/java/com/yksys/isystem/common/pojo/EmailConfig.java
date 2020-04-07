package com.yksys.isystem.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 邮箱配置类
 * @author: YuKai Fan
 * @create: 2020-03-30 16:48
 **/
@Data
@ApiModel(value = "邮件配置pojo对象", description = "邮件发送配置")
public class EmailConfig implements Serializable {
    private static final long serialVersionUID = -1467337107675175838L;

    //邮箱配置标识
    @ApiModelProperty(value = "邮箱配置id标识", dataType = "String")
    private String id;
    //配置名称
    @ApiModelProperty(value = "配置名称", dataType = "String")
    private String name;
    //协议类型
    @ApiModelProperty(value = "协议类型", dataType = "String")
    private String protocol;
    //发件服务器域名
    @ApiModelProperty(value = "发件服务器域名", dataType = "String")
    private String smtpHost;
    //发件服务器账户
    @ApiModelProperty(value = "发件服务器账户", dataType = "String")
    private String smtpUserName;
    //发件服务器验证码
    @ApiModelProperty(value = "发件服务器验证码", dataType = "String")
    private String smtpPassword;
    //状态: 1 默认 0 不默认
    @ApiModelProperty(value = "状态: 1 默认 0 不默认", dataType = "int")
    private Integer isDefault;
    //备注
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
    //状态:0  已禁用 1 正在使用
    @ApiModelProperty(value = "状态:0  已禁用 1 正在使用", dataType = "int")
    private Integer status;

    public EmailConfig(String protocol, String smtpHost, String smtpUserName, String smtpPassword, Integer isDefault) {
        this.protocol = protocol;
        this.smtpHost = smtpHost;
        this.smtpUserName = smtpUserName;
        this.smtpPassword = smtpPassword;
        this.isDefault = isDefault;
    }

    public EmailConfig() {
    }
}