package com.yksys.isystem.service.message.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.pojo.EmailConfig;
import com.yksys.isystem.service.message.mapper.EmailConfigMapper;
import com.yksys.isystem.service.message.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public EmailConfig addEmailConfig(EmailConfig emailConfig) {
        emailConfig.setId(AppUtil.randomId());
        emailConfig.setStatus(1);
        emailConfigMapper.addEmailConfig(emailConfig);

        redisUtil.set(RedisConstants.EMAIL_CONFIG + emailConfig.getId(), emailConfig);
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

        Map<String, Object> emailConfigById = emailConfigMapper.getEmailConfigById(emailConfig.getId());
        redisUtil.set(RedisConstants.EMAIL_TEMPLATE + emailConfig.getId(), MapUtil.mapToObject(EmailConfig.class, emailConfigById, false));
    }

    @Override
    public void delEmailConfigById(String id) {
        emailConfigMapper.delEmailConfigById(id);
        redisUtil.del(RedisConstants.EMAIL_CONFIG + id);
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

    @Override
    public void loadCacheConfig() {
        List<Map<String, Object>> emailConfigs = emailConfigMapper.getEmailConfigs(null);
//        List<EmailConfig> list = Lists.newArrayList();
//        emailConfigs.forEach(emailConfig -> {
//            EmailConfig ec = MapUtil.mapToObject(EmailConfig.class, emailConfig, false);
//            list.add(ec);
//        });
        redisUtil.set(RedisConstants.EMAIL_CONFIG + "list", emailConfigs);
    }

    @Override
    public List<EmailConfig> getCacheConfig() throws Exception {
        List<Map<String, Object>> emailConfigs = (List<Map<String, Object>>) redisUtil.get(RedisConstants.EMAIL_CONFIG + "list");
        List<EmailConfig> list = Lists.newArrayList();
        emailConfigs.forEach(emailConfig -> {
            EmailConfig ec = MapUtil.mapToObject(EmailConfig.class, emailConfig, false);
            list.add(ec);
        });
        return list;
    }

}
