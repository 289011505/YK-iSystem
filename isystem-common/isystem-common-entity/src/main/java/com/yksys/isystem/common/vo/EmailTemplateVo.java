package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.EmailTemplate;
import org.springframework.beans.BeanUtils;


/**
 * 邮件模板表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-30 20:49:36
 */
@Data
public class EmailTemplateVo implements Serializable {
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

    public EmailTemplate convert() {
        EmailTemplateVoConvert emailTemplateVoConvert = new EmailTemplateVoConvert();
        EmailTemplate emailTemplate = emailTemplateVoConvert.convert(this);
        return emailTemplate;
    }

    private static class EmailTemplateVoConvert extends Converter<EmailTemplateVo, EmailTemplate> {

        @Override
        protected EmailTemplate doForward(EmailTemplateVo emailTemplateVo) {
            EmailTemplate emailTemplate = new EmailTemplate();
            BeanUtils.copyProperties(emailTemplateVo, emailTemplate);
            return emailTemplate;
        }

        @Override
        protected EmailTemplateVo doBackward(EmailTemplate emailTemplate) {
            return null;
        }
    }

}
