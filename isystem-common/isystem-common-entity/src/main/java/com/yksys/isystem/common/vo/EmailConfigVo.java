package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.EmailConfig;
import org.springframework.beans.BeanUtils;


/**
 * 邮箱配置表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-30 20:49:36
 */
@Data
public class EmailConfigVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //邮箱配置标识
    private String id;
    //配置名称
    private String name;
    //协议类型
    private String protocol;
    //发件服务器域名
    private String smtpHost;
    //发件服务器账户
    private String smtpUserName;
    //发件服务器验证码
    private String smtpPassword;
    //状态: 1 默认 0 不默认
    private Integer isDefault;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;

    public EmailConfig convert() {
        EmailConfigVoConvert emailConfigVoConvert = new EmailConfigVoConvert();
        EmailConfig emailConfig = emailConfigVoConvert.convert(this);
        return emailConfig;
    }

    private static class EmailConfigVoConvert extends Converter<EmailConfigVo, EmailConfig> {

        @Override
        protected EmailConfig doForward(EmailConfigVo emailConfigVo) {
            EmailConfig emailConfig = new EmailConfig();
            BeanUtils.copyProperties(emailConfigVo, emailConfig);
            return emailConfig;
        }

        @Override
        protected EmailConfigVo doBackward(EmailConfig emailConfig) {
            return null;
        }
    }

}
