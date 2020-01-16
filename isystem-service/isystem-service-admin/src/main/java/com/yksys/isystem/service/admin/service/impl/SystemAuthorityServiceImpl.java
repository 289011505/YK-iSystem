package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.SystemAuthority;
import com.yksys.isystem.service.admin.mapper.SystemAuthorityMapper;
import com.yksys.isystem.service.admin.service.SystemAuthorityService;
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
public class SystemAuthorityServiceImpl implements SystemAuthorityService {
    @Autowired
    private SystemAuthorityMapper systemAuthorityMapper;

    @Override
    public SystemAuthority addSystemAuthority(SystemAuthority systemAuthority) {
        systemAuthority.setId(AppUtil.randomId());
        systemAuthority.setStatus(1);
        systemAuthorityMapper.addSystemAuthority(systemAuthority);
        return systemAuthority;
    }

    @Override
    public Map<String, Object> getSystemAuthorityById(String id) {
        return systemAuthorityMapper.getSystemAuthorityById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemAuthorities(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemAuthorities(map);
    }

    @Override
    public List<Map<String, Object>> getSystemAuthorities(Map<String, Object> map) {
        return systemAuthorityMapper.getSystemAuthorities(map);
    }

    @Override
    public void editSystemAuthority(SystemAuthority systemAuthority) {
        systemAuthorityMapper.editSystemAuthorityById(systemAuthority);
    }

    @Override
    public void delSystemAuthorityById(String id) {
        systemAuthorityMapper.delSystemAuthorityById(id);
    }

    @Override
    public void delSystemAuthorityByIs(List<String> ids) {
        systemAuthorityMapper.delSystemAuthorityByIds(ids);
    }

    @Override
    public void delSystemAuthorityRealById(String id) {
        systemAuthorityMapper.delSystemAuthorityRealById(id);
    }

    @Override
    public void delSystemAuthorityRealByIds(List<String> ids) {
        systemAuthorityMapper.delSystemAuthorityRealByIds(ids);
    }

}
