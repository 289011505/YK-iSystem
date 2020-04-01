package com.yksys.isystem.service.message.exchanger;

import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.model.BaseMessage;
import com.yksys.isystem.common.model.EmailMessage;
import com.yksys.isystem.common.pojo.EmailConfig;
import com.yksys.isystem.service.message.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
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

    @Override
    public boolean support(Object message) {
        return message instanceof EmailMessage;
    }

    @Override
    public boolean exchange(BaseMessage message) {
        EmailMessage emailMessage = (EmailMessage) message;
        EmailConfig emailConfig = new EmailConfig("smtp","smtp.qq.com", "2424331381@qq.com", "zvfmkmmbbqvhebga", 1);
        JavaMailSenderImpl javaMailSender = buildMailSender(emailConfig);
        try {
            emailService.sendSimpleMail(javaMailSender, emailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
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
}