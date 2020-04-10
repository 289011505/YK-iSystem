package com.yksys.isystem.service.auth.service.impl;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.constants.ComConstants;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.security.UserAuthority;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.model.AuthorityApi;
import com.yksys.isystem.common.model.AuthorityMenu;
import com.yksys.isystem.common.model.AuthorityResource;
import com.yksys.isystem.common.model.SystemUserInfo;
import com.yksys.isystem.common.pojo.SystemRole;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.pojo.UserRole;
import com.yksys.isystem.service.auth.mapper.SystemUserInfoMapper;
import com.yksys.isystem.service.auth.service.SystemUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-24 14:56
 **/
@Service
public class SystemUserInfoServiceImpl implements SystemUserInfoService {
    @Autowired
    private SystemUserInfoMapper systemUserInfoMapper;

    @Override
    public SystemUserInfo getLoginUserInfo(Map<String, Object> map) {
        Map<String, Object> systemUserInfoMap = systemUserInfoMapper.getSystemUserInfos(map);
        if (CollectionUtils.isEmpty(systemUserInfoMap)) {
            throw new ParameterException("该用户信息错误, 请联系管理员");
        }
        SystemUserInfo systemUserInfo = MapUtil.mapToObject(SystemUserInfo.class, systemUserInfoMap, false);
        //获取用户角色集合
        SystemRole systemRole = getUserRoles(systemUserInfo.getId());
        //用户权限列表
        List<UserAuthority> userAuthorities = getUserAuthorities(systemUserInfo.getId(), systemRole.getRoleCode());
        systemUserInfo.setRole(systemRole);
        systemUserInfo.setAuthorities(userAuthorities);
        return systemUserInfo;
    }

    @Override
    public SystemRole getUserRoles(String userId) {
        List<SystemRole> userRoles = systemUserInfoMapper.getUserRoles(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new ParameterException("该用户角色设置错误!, 请重新设置");
        }
        return userRoles.get(0);
    }

    @Override
    public List<UserAuthority> getUserAuthorities(String userId, String roleCode) {
        if (ComConstants.ROOT_ADMIN.equals(roleCode)) {
            //返回所有权限
            return systemUserInfoMapper.getUserAuthorities();
        }
        List<UserAuthority> list = Lists.newArrayList();
        SystemRole systemRole = getUserRoles(userId);
        List<UserAuthority> authoritiesByRoleId = systemUserInfoMapper.getUserAuthoritiesByRoleId(systemRole.getId());
        if (!CollectionUtils.isEmpty(authoritiesByRoleId)) {
            list.addAll(authoritiesByRoleId);
        }

        if (!CollectionUtils.isEmpty(list)) {
            //集合去重
            list.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(UserAuthority::getAuthorityId))),
                    ArrayList::new));
        }
        return list;
    }

    @Override
    public List<AuthorityMenu> getAuthorityMenuByUserId(String userId, String roleCode) {
        if (ComConstants.ROOT_ADMIN.equals(roleCode)) {
            //返回所有权限
            return systemUserInfoMapper.getAuthorityMenus();
        }
        List<AuthorityMenu> list = Lists.newArrayList();
        SystemRole systemRole = getUserRoles(userId);
        List<AuthorityMenu> authorityMenuList = systemUserInfoMapper.getUserAuthorityMenusByRoleId(systemRole.getId());
        if (!CollectionUtils.isEmpty(authorityMenuList)) {
            list.addAll(authorityMenuList);
        }
        if (!CollectionUtils.isEmpty(list)) {
            //集合去重
            list.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(AuthorityMenu::getAuthorityId))),
                    ArrayList::new));
        }
        return list;
    }

    @Override
    public List<AuthorityApi> getAuthorityApisByUserId(String userId, String roleCode) {
        if (ComConstants.ROOT_ADMIN.equals(roleCode)) {
            //返回所有权限
            return systemUserInfoMapper.getAuthorityApis();
        }
        List<AuthorityApi> list = Lists.newArrayList();
        SystemRole systemRole = getUserRoles(userId);
        List<AuthorityApi> authorityApis = systemUserInfoMapper.getUserAuthorityApisByRoleId(systemRole.getId());
        if (!CollectionUtils.isEmpty(authorityApis)) {
            list.addAll(authorityApis);
        }
        if (!CollectionUtils.isEmpty(list)) {
            //集合去重
            list.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(AuthorityApi::getAuthorityId))),
                    ArrayList::new));
        }
        return list;
    }

    @Override
    public List<AuthorityResource> getAuthorityResources() {
        return systemUserInfoMapper.getAuthorityResources();
    }

    @Override
    public boolean checkUserIsExist(Map<String, Object> map) {
        int i = systemUserInfoMapper.checkUserIsExist(map);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void addSystemUser(SystemUser systemUser) {
        systemUser.setId(AppUtil.randomId());
        systemUser.setStatus(1);

        systemUserInfoMapper.addSystemUser(systemUser);

        //添加角色 默认注册会员
        UserRole userRole = new UserRole();
        userRole.setUserId(systemUser.getId());
        userRole.setRoleId(ComConstants.REGISTER_ID);
        systemUserInfoMapper.addUserRole(userRole);
    }


}