package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.UserActivity;
import com.yksys.isystem.service.admin.mapper.UserActivityMapper;
import com.yksys.isystem.service.admin.service.UserActivityService;
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
public class UserActivityServiceImpl implements UserActivityService {
    @Autowired
    private UserActivityMapper userActivityMapper;

    @Override
    public UserActivity addUserActivity(UserActivity userActivity) {
        userActivity.setId(AppUtil.randomId());
        userActivity.setStatus(1);
        userActivity.setUserId(AppSession.getCurrentUserId());
        userActivityMapper.addUserActivity(userActivity);
        return userActivity;
    }

    @Override
    public Map<String, Object> getUserActivityById(String id) {
        return userActivityMapper.getUserActivityById(id);
    }

    @Override
    public List<Map<String, Object>> getUserActivitys(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getUserActivitys(map);
    }

    @Override
    public List<Map<String, Object>> getUserActivitys(Map<String, Object> map) {
        return userActivityMapper.getUserActivitys(map);
    }

    @Override
    public void editUserActivity(UserActivity userActivity) {
        userActivityMapper.editUserActivityById(userActivity);
    }

    @Override
    public void delUserActivityById(String id) {
        userActivityMapper.delUserActivityById(id);
    }

    @Override
    public void delUserActivityByIs(List<String> ids) {
        userActivityMapper.delUserActivityByIds(ids);
    }

    @Override
    public void delUserActivityRealById(String id) {
        userActivityMapper.delUserActivityRealById(id);
    }

    @Override
    public void delUserActivityRealByIds(List<String> ids) {
        userActivityMapper.delUserActivityRealByIds(ids);
    }

}
