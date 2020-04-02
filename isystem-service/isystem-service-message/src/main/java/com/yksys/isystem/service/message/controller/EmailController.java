package com.yksys.isystem.service.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.EmailMessage;
import com.yksys.isystem.common.model.EmailTplMessage;
import com.yksys.isystem.service.message.dispatcher.MessageDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮件controller
 * @author: YuKai Fan
 * @create: 2020-03-30 10:50
 **/
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private MessageDispatcher messageDispatcher;

    /**
     * 发送邮件
     * @param recipients 接收人 多个用; 号隔开
     * @param cc 抄送人 多个用; 号隔开
     * @param subject 主题
     * @param content 内容
     * @param attachUrls 附件地址
     * @return
     */
    @PostMapping("/sendEmail")
    public Result sendEmail(@RequestParam(value = "recipients") String recipients,
                            @RequestParam(value = "cc") String cc,
                            @RequestParam(value = "subject") String subject,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "attachUrls", required = false) List<Map<String, String>> attachUrls) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setRecipients(StringUtil.delimitedListToStringArray(recipients, ";"));
        emailMessage.setCc(StringUtil.delimitedListToStringArray(cc, ";"));
        emailMessage.setSubject(subject);
        emailMessage.setContent(content);
        emailMessage.setAttachUrls(attachUrls);
        messageDispatcher.dispatch(emailMessage);
        return new Result(HttpStatus.OK.value(), "发送成功");
    }

    /**
     * 发送模板邮件
     * @param recipients 接收人 多个用; 号隔开
     * @param cc 抄送人 多个用; 号隔开
     * @param subject 主题
     * @param content 内容
     * @param attachUrls 附件地址
     * @return
     */
    @PostMapping("/sendTplEmail")
    public Result sendTplEmail(@RequestParam(value = "recipients") String recipients,
                            @RequestParam(value = "cc") String cc,
                            @RequestParam(value = "subject") String subject,
                            @RequestParam(value = "tplCode") String tplCode,
                            @RequestParam(value = "tplParams") String tplParams,
                            @RequestParam(value = "attachUrls", required = false) List<Map<String, String>> attachUrls) {
        EmailTplMessage message = new EmailTplMessage();
        message.setRecipients(StringUtil.delimitedListToStringArray(recipients, ";"));
        message.setCc(StringUtil.delimitedListToStringArray(cc, ";"));
        message.setSubject(subject);
        message.setAttachUrls(attachUrls);
        message.setTplCode(tplCode);
        message.setTplParams(JSONObject.parseObject(tplParams));
        messageDispatcher.dispatch(message);
        return new Result(HttpStatus.OK.value(), "发送成功");
    }
}