package com.yksys.isystem.service.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.ActionLog;
import com.yksys.isystem.service.system.mapper.ActionLogMapper;
import com.yksys.isystem.service.system.service.ActionLogService;
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
public class ActionLogServiceImpl implements ActionLogService {
    @Autowired
    private ActionLogMapper actionLogMapper;

    @Override
    public ActionLog addActionLog(ActionLog actionLog) {
        actionLog.setId(AppUtil.randomId());
        actionLog.setStatus(1);
        actionLogMapper.addActionLog(actionLog);
        return actionLog;
    }

    @Override
    public Map<String, Object> getActionLogById(String id) {
        return actionLogMapper.getActionLogById(id);
    }

    @Override
    public List<Map<String, Object>> getActionLogs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getActionLogs(map);
    }

    @Override
    public List<Map<String, Object>> getActionLogs(Map<String, Object> map) {
        return actionLogMapper.getActionLogs(map);
    }

    @Override
    public void editActionLog(ActionLog actionLog) {
        actionLogMapper.editActionLogById(actionLog);
    }

    @Override
    public void delActionLogById(String id) {
        actionLogMapper.delActionLogById(id);
    }

    @Override
    public void delActionLogByIs(List<String> ids) {
        actionLogMapper.delActionLogByIds(ids);
    }

    @Override
    public void delActionLogRealById(String id) {
        actionLogMapper.delActionLogRealById(id);
    }

    @Override
    public void delActionLogRealByIds(List<String> ids) {
        actionLogMapper.delActionLogRealByIds(ids);
    }

    @Override
    public void addActionLogByRedis() {

    }

}
