package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.AuthorityRole;
import com.yksys.isystem.common.pojo.SystemRole;
import com.yksys.isystem.service.admin.mapper.SystemRoleMapper;
import com.yksys.isystem.service.admin.service.SystemRoleService;
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
public class SystemRoleServiceImpl implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Override
    public SystemRole addSystemRole(SystemRole systemRole) {
        systemRole.setId(AppUtil.randomId());
        systemRole.setStatus(1);
        systemRoleMapper.addSystemRole(systemRole);
        return systemRole;
    }

    @Override
    public Map<String, Object> getSystemRoleById(String id) {
        return systemRoleMapper.getSystemRoleById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemRoles(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemRoles(map);
    }

    @Override
    public List<Map<String, Object>> getSystemRoles(Map<String, Object> map) {
        return systemRoleMapper.getSystemRoles(map);
    }

    @Override
    public void editSystemRole(SystemRole systemRole) {
        systemRoleMapper.editSystemRoleById(systemRole);
    }

    @Override
    public void delSystemRoleById(String id) {
        systemRoleMapper.delSystemRoleById(id);
    }

    @Override
    public void delSystemRoleByIs(List<String> ids) {
        systemRoleMapper.delSystemRoleByIds(ids);
    }

    @Override
    public void delSystemRoleRealById(String id) {
        systemRoleMapper.delSystemRoleRealById(id);
    }

    @Override
    public void delSystemRoleRealByIds(List<String> ids) {
        systemRoleMapper.delSystemRoleRealByIds(ids);
    }

    @Override
    public void assignRoleAuth(String roleId, List<String> authorityIds) {
        //先删除原有的权限, 再重新添加
        systemRoleMapper.delRoleAuth(roleId);
        if (!CollectionUtils.isEmpty(authorityIds)) {
            authorityIds.forEach(authorityId -> {
                AuthorityRole authorityRole = new AuthorityRole();
                authorityRole.setId(AppUtil.randomId());
                authorityRole.setAuthorityId(authorityId);
                authorityRole.setRoleId(roleId);
                systemRoleMapper.assignRoleAuth(authorityRole);
            });
        }
    }

}
