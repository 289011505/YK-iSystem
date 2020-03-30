package com.yksys.isystem.service.message.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.EmailConfig;
import com.yksys.isystem.service.message.mapper.EmailConfigMapper;
import com.yksys.isystem.service.message.service.EmailConfigService;
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
public class EmailConfigServiceImpl implements EmailConfigService {
    @Autowired
    private EmailConfigMapper emailConfigMapper;

    @Override
    public EmailConfig addEmailConfig(EmailConfig emailConfig) {
        emailConfig.setId(AppUtil.randomId());
        emailConfig.setStatus(1);
        emailConfigMapper.addEmailConfig(emailConfig);
        return emailConfig;
    }

    @Override
    public Map<String, Object> getEmailConfigById(String id) {
        return emailConfigMapper.getEmailConfigById(id);
    }

    @Override
    public List<Map<String, Object>> getEmailConfigs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getEmailConfigs(map);
    }

    @Override
    public List<Map<String, Object>> getEmailConfigs(Map<String, Object> map) {
        return emailConfigMapper.getEmailConfigs(map);
    }

    @Override
    public void editEmailConfig(EmailConfig emailConfig) {
        emailConfigMapper.editEmailConfigById(emailConfig);
    }

    @Override
    public void delEmailConfigById(String id) {
        emailConfigMapper.delEmailConfigById(id);
    }

    @Override
    public void delEmailConfigByIs(List<String> ids) {
        emailConfigMapper.delEmailConfigByIds(ids);
    }

    @Override
    public void delEmailConfigRealById(String id) {
        emailConfigMapper.delEmailConfigRealById(id);
    }

    @Override
    public void delEmailConfigRealByIds(List<String> ids) {
        emailConfigMapper.delEmailConfigRealByIds(ids);
    }

}
