package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.exception.ParameterException;
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

        //添加权限角色关系
        if (CollectionUtils.isEmpty(systemRole.getAuthorityIds())) {
            throw new ParameterException("权限集合为空, 请选择权限菜单!");
        }
        addAuthorityRole(systemRole);
        return systemRole;
    }

    private void addAuthorityRole(SystemRole systemRole) {
        List<AuthorityRole> list = Lists.newArrayList();
        systemRole.getAuthorityIds().forEach(authorityId -> {
            AuthorityRole authorityRole = new AuthorityRole();
            authorityRole.setRoleId(systemRole.getId());
            authorityRole.setAuthorityId(authorityId);
            list.add(authorityRole);
        });
        systemRoleMapper.addAuthorityRoles(list);
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
        List<Map<String, Object>> systemRoles = systemRoleMapper.getSystemRoles(map);
        systemRoles.forEach(systemRole -> {
            systemRole.put("authorityIds", systemRoleMapper.getAuthorityRolesByRoleId(systemRole.get("id").toString()));
        });
        return systemRoles;
    }

    @Override
    public void editSystemRole(SystemRole systemRole) {
        systemRoleMapper.editSystemRoleById(systemRole);

        //删除权限, 再添加权限
        systemRoleMapper.delAuthorityRolesByRoleId(systemRole.getId());
        if (!CollectionUtils.isEmpty(systemRole.getAuthorityIds())) {
            addAuthorityRole(systemRole);
        }
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
