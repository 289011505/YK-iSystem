package com.yksys.isystem.service.message.service.impl;

import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.EmailMessage;
import com.yksys.isystem.service.message.service.EmailService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-30 16:25
 **/
@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendSimpleMail(JavaMailSenderImpl javaMailSender, EmailMessage emailMessage) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(emailMessage.getRecipients());
        if (StringUtil.isNotBlank(emailMessage.getCc()) && emailMessage.getCc().length > 0) {
            helper.setCc(emailMessage.getCc());
        }
        helper.setFrom(javaMailSender.getUsername());
        helper.setSubject(emailMessage.getSubject());
        helper.setText(emailMessage.getContent(), true);
        javaMailSender.send(message);
    }
}