package com.yksys.isystem.service.message.service;

import com.yksys.isystem.common.model.EmailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-30 16:24
 **/
public interface EmailService {

    /**
     * 发送邮件
     * @param javaMailSender
     * @param emailMessage
     */
    void sendSimpleMail(JavaMailSenderImpl javaMailSender, EmailMessage emailMessage) throws MessagingException;
}