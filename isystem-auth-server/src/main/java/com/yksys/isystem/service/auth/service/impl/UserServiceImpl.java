package com.yksys.isystem.service.auth.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.constants.ComConstants;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.YkUserDetails;
import com.yksys.isystem.common.core.security.oauth2.client.Oauth2ClientProperties;
import com.yksys.isystem.common.model.SystemUserInfo;
import com.yksys.isystem.service.auth.service.SystemUserInfoService;
import com.yksys.isystem.service.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-10 17:26
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SystemUserInfoService systemUserInfoService;
    @Autowired
    private Oauth2ClientProperties clientProperties;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("account", account);
        SystemUserInfo systemUserInfo = systemUserInfoService.getLoginUserInfo(map);
        if (systemUserInfo == null) {
            throw new UsernameNotFoundException("用户" + account + "不存在!");
        }
        YkUserDetails userDetails = new YkUserDetails();
        userDetails.setUserId(systemUserInfo.getId());
        userDetails.setAuthorities(systemUserInfo.getAuthorities());
        userDetails.setAccount(systemUserInfo.getAccount());
        userDetails.setPassword(systemUserInfo.getPassword());
        userDetails.setUserIcon(systemUserInfo.getUserIcon());
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(!systemUserInfo.getStatus().equals(ComConstants.ACCOUNT_STATUS_LOCKED));
        userDetails.setEnabled(systemUserInfo.getStatus().equals(ComConstants.ACCOUNT_STATUS_NORMAL));
        userDetails.setClientId(clientProperties.getOauth2().get("auth").getClientId());
        userDetails.setUsername(systemUserInfo.getUserName());
        userDetails.setRoleCode(Lists.newArrayList(systemUserInfo.getRole().getRoleCode()));

        return userDetails;
    }
}