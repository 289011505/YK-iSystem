package com.yksys.isystem.service.message.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.pojo.EmailTemplate;
import com.yksys.isystem.service.message.mapper.EmailTemplateMapper;
import com.yksys.isystem.service.message.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {
    @Autowired
    private EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplate addEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplate.setId(AppUtil.randomId());
        emailTemplate.setStatus(1);
        emailTemplateMapper.addEmailTemplate(emailTemplate);

        return emailTemplate;
    }

    @Override
    public Map<String, Object> getEmailTemplateById(String id) {
        return emailTemplateMapper.getEmailTemplateById(id);
    }

    @Override
    public List<Map<String, Object>> getEmailTemplates(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getEmailTemplates(map);
    }

    @Override
    public List<Map<String, Object>> getEmailTemplates(Map<String, Object> map) {
        return emailTemplateMapper.getEmailTemplates(map);
    }

    @Override
    public void editEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateMapper.editEmailTemplateById(emailTemplate);
    }

    @Override
    public void delEmailTemplateById(String id) {
        emailTemplateMapper.delEmailTemplateById(id);
    }

    @Override
    public void delEmailTemplateByIs(List<String> ids) {
        emailTemplateMapper.delEmailTemplateByIds(ids);
    }

    @Override
    public void delEmailTemplateRealById(String id) {
        emailTemplateMapper.delEmailTemplateRealById(id);
    }

    @Override
    public void delEmailTemplateRealByIds(List<String> ids) {
        emailTemplateMapper.delEmailTemplateRealByIds(ids);
    }

}
