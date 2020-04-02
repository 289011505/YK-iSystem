package com.yksys.isystem.common.model;

import lombok.Data;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮件模板消息
 * @author: YuKai Fan
 * @create: 2020-04-02 10:44
 **/
@Data
public class EmailTplMessage extends EmailMessage {

    //模板编号
    private String tplCode;
    //模板参数
    private Map<String, Object> tplParams;
}