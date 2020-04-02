package com.yksys.isystem.service.message.exchanger;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.BaseMessage;
import com.yksys.isystem.common.model.EmailMessage;
import com.yksys.isystem.common.model.EmailTplMessage;
import com.yksys.isystem.common.pojo.EmailConfig;
import com.yksys.isystem.common.pojo.EmailLog;
import com.yksys.isystem.common.pojo.EmailTemplate;
import com.yksys.isystem.service.message.service.EmailConfigService;
import com.yksys.isystem.service.message.service.EmailLogService;
import com.yksys.isystem.service.message.service.EmailService;
import com.yksys.isystem.service.message.service.EmailTemplateService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: YK-iSystem
 * @description: 邮件转换
 * @author: YuKai Fan
 * @create: 2020-03-30 11:40
 **/
@Component
public class EmailExchanger implements MessageExchanger {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailConfigService emailConfigService;
    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private EmailLogService emailLogService;

    @Override
    public boolean support(Object message) {
        return message instanceof EmailMessage;
    }

    @Override
    public boolean exchange(BaseMessage message) {
        EmailMessage emailMessage = (EmailMessage) message;
        EmailConfig emailConfig = null;
        EmailTemplate emailTemplate = null;
        Map<String, EmailConfig> configMap = Maps.newHashMap();
        Integer result = 0;
        String error = null;

        try {
            //加载缓存中的邮件配置
            List<EmailConfig> configList = emailConfigService.getCacheConfig();
            if (CollectionUtils.isEmpty(configList)) {
                return false;
            }
            configList.forEach(config -> {
                if (config.getIsDefault().intValue() == 1) {
                    //添加默认配置
                    configMap.put("default", config);
                }

                configMap.put(config.getId(), config);
            });

            //处理模板消息
            if (message instanceof EmailTplMessage) {
                EmailTplMessage tplMessage = (EmailTplMessage) message;
                List<Map<String, Object>> template = emailTemplateService.getEmailTemplates(ImmutableMap.of("code", tplMessage.getTplCode()));
                if (CollectionUtils.isEmpty(template)) {
                    return false;
                }
                emailTemplate = MapUtil.mapToObject(EmailTemplate.class, template.get(0), false);
                //根据配置id获取 邮件配置
                emailConfig = configMap.get(emailTemplate.getConfigId());
                String content = velocityTemplate(tplMessage.getTplParams(), emailTemplate.getTemplate());
                emailMessage.setContent(content);
            }

            if (emailConfig == null) {
                emailConfig = configMap.get("default");
            }

            //邮件发送
            JavaMailSenderImpl javaMailSender = buildMailSender(emailConfig);
            emailService.sendSimpleMail(javaMailSender, emailMessage);
            logger.error("发送成功:{}", emailMessage.toString());

            result = 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            error = e.getMessage();
            logger.error("发送失败:{}", e);
        } finally {
            //保存发送日志
            Map<String, Object> logMap = Maps.newHashMap();
            logMap.put("template", emailTemplate);
            logMap.put("config", emailConfig);
            EmailLog emailLog = new EmailLog();
            emailLog.setRecipients(StringUtil.arrayToDelimitedString(emailMessage.getRecipients(), ";"));
            emailLog.setCc(StringUtil.arrayToDelimitedString(emailMessage.getCc(), ";"));
            emailLog.setSubject(emailMessage.getSubject());
            emailLog.setContent(emailMessage.getContent());
            emailLog.setError(error);
            emailLog.setResult(result);
            emailLog.setSendNum(1);
            emailLog.setTplId(emailTemplate != null ? emailTemplate.getId() : null);
            emailLog.setConfig(JSONObject.toJSONString(logMap));
            emailLogService.addEmailLog(emailLog);
        }
        return false;
    }

    private JavaMailSenderImpl buildMailSender(EmailConfig emailConfig) {
        if (emailConfig == null) {
            throw new ParameterException("缺少默认邮件服务器配置");
        }
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfig.getSmtpHost());
        javaMailSender.setUsername(emailConfig.getSmtpUserName());
        javaMailSender.setPassword(emailConfig.getSmtpPassword());
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    /**
     * velocity处理模板
     * @param params
     * @param templateStr
     * @return
     */
    private String  velocityTemplate(Map params, String templateStr) {
        try {
//            StringWriter writer = new StringWriter();
//            VelocityContext context = new VelocityContext(params);
//            Velocity.evaluate(context, writer, "", templateStr);
            //设置velocity资源加载器
            Properties prop = new Properties();
            prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            Velocity.init(prop);
            VelocityContext context = new VelocityContext(params);
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate("emailTemplate/registerEmail.html.vm", "UTF-8");
            tpl.merge(context, sw);

            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}