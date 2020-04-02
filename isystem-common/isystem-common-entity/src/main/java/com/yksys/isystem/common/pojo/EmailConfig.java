package com.yksys.isystem.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 邮箱配置类
 * @author: YuKai Fan
 * @create: 2020-03-30 16:48
 **/
@Data
public class EmailConfig implements Serializable {
    private static final long serialVersionUID = -1467337107675175838L;

    //id
    private String id;

    //配置名称
    private String name;

    //协议
    private String protocol;

    //发件服务器域名
    private String smtpHost;

    //发件服务器账户
    private String smtpUserName;

    //发件服务器密码
    private String smtpPassword;

    //是否默认
    private Integer isDefault;

    //状态
    private Integer status;

    //备注
    private String remark;

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