package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.service.admin.mapper.SystemUserMapper;
import com.yksys.isystem.service.admin.service.SystemUserService;
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
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser addSystemUser(SystemUser systemUser) {
        systemUser.setId(AppUtil.randomId());
        systemUser.setStatus(1);
        systemUserMapper.addSystemUser(systemUser);
        return systemUser;
    }

    @Override
    public Map<String, Object> getSystemUserById(String id) {
        return systemUserMapper.getSystemUserById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemUsers(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemUsers(map);
    }

    @Override
    public List<Map<String, Object>> getSystemUsers(Map<String, Object> map) {
        return systemUserMapper.getSystemUsers(map);
    }

    @Override
    public void editSystemUser(SystemUser systemUser) {
        systemUserMapper.editSystemUserById(systemUser);
    }

    @Override
    public void delSystemUserById(String id) {
        systemUserMapper.delSystemUserById(id);
    }

    @Override
    public void delSystemUserByIs(List<String> ids) {
        systemUserMapper.delSystemUserByIds(ids);
    }

    @Override
    public void delSystemUserRealById(String id) {
        systemUserMapper.delSystemUserRealById(id);
    }

    @Override
    public void delSystemUserRealByIds(List<String> ids) {
        systemUserMapper.delSystemUserRealByIds(ids);
    }

}
