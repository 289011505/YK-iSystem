package com.yksys.isystem.service.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.SystemApi;
import com.yksys.isystem.service.system.mapper.SystemApiMapper;
import com.yksys.isystem.service.system.service.SystemApiService;
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
public class SystemApiServiceImpl implements SystemApiService {
    @Autowired
    private SystemApiMapper systemApiMapper;

    @Override
    public SystemApi addSystemApi(SystemApi systemApi) {
        systemApi.setId(AppUtil.randomId());
        systemApi.setStatus(1);
        systemApiMapper.addSystemApi(systemApi);
        return systemApi;
    }

    @Override
    public Map<String, Object> getSystemApiById(String id) {
        return systemApiMapper.getSystemApiById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemApis(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemApis(map);
    }

    @Override
    public List<Map<String, Object>> getSystemApis(Map<String, Object> map) {
        return systemApiMapper.getSystemApis(map);
    }

    @Override
    public void editSystemApi(SystemApi systemApi) {
        systemApiMapper.editSystemApiById(systemApi);
    }

    @Override
    public void delSystemApiById(String id) {
        systemApiMapper.delSystemApiById(id);
    }

    @Override
    public void delSystemApiByIs(List<String> ids) {
        systemApiMapper.delSystemApiByIds(ids);
    }

    @Override
    public void delSystemApiRealById(String id) {
        systemApiMapper.delSystemApiRealById(id);
    }

    @Override
    public void delSystemApiRealByIds(List<String> ids) {
        systemApiMapper.delSystemApiRealByIds(ids);
    }

}
