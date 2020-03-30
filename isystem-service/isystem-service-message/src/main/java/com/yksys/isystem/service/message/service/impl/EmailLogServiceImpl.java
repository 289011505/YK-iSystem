package com.yksys.isystem.service.message.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.EmailLog;
import com.yksys.isystem.service.message.mapper.EmailLogMapper;
import com.yksys.isystem.service.message.service.EmailLogService;
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
public class EmailLogServiceImpl implements EmailLogService {
    @Autowired
    private EmailLogMapper emailLogMapper;

    @Override
    public EmailLog addEmailLog(EmailLog emailLog) {
        emailLog.setId(AppUtil.randomId());
        emailLog.setStatus(1);
        emailLogMapper.addEmailLog(emailLog);
        return emailLog;
    }

    @Override
    public Map<String, Object> getEmailLogById(String id) {
        return emailLogMapper.getEmailLogById(id);
    }

    @Override
    public List<Map<String, Object>> getEmailLogs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getEmailLogs(map);
    }

    @Override
    public List<Map<String, Object>> getEmailLogs(Map<String, Object> map) {
        return emailLogMapper.getEmailLogs(map);
    }

    @Override
    public void editEmailLog(EmailLog emailLog) {
        emailLogMapper.editEmailLogById(emailLog);
    }

    @Override
    public void delEmailLogById(String id) {
        emailLogMapper.delEmailLogById(id);
    }

    @Override
    public void delEmailLogByIs(List<String> ids) {
        emailLogMapper.delEmailLogByIds(ids);
    }

    @Override
    public void delEmailLogRealById(String id) {
        emailLogMapper.delEmailLogRealById(id);
    }

    @Override
    public void delEmailLogRealByIds(List<String> ids) {
        emailLogMapper.delEmailLogRealByIds(ids);
    }

}
