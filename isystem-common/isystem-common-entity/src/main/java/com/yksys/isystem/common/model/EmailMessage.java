package com.yksys.isystem.common.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮件消息model
 * @author: YuKai Fan
 * @create: 2020-03-30 11:11
 **/
@Data
public class EmailMessage extends BaseMessage {
    //id
    private String id;

    //收件人
    private String[] recipients;

    //抄送人
    private String [] cc;

    //邮件主题
    private String subject;

    //邮件内容
    private String content;

    //附件路径集合
    private List<Map<String, String>> attachUrls;
}