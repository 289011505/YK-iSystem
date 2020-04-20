package com.yksys.isystem.zuul.service;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.model.AuthorityResource;
import com.yksys.isystem.zuul.service.fallback.AuthorityServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-27 10:08
 **/
@Component
@FeignClient(value = "isystem-auth-server", fallbackFactory = AuthorityServiceFallback.class)
public interface AuthorityService {

    /**
     * 获取所有访问权限列表
     * @return
     */
    @GetMapping("/api/systemUserInfo/getAuthorityResources")
    Result<List<AuthorityResource>> getAuthorityResources();

    /**
     * 获取所有用户信息(id, phone)
     * @return
     */
    @GetMapping("/api/systemUserInfo/getAllSystemUserInfos")
    Result<List<Map<String, Object>>> getAllSystemUserInfos();

}