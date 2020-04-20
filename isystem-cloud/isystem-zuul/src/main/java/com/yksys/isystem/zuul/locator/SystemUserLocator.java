package com.yksys.isystem.zuul.locator;

import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.model.AuthorityResource;
import com.yksys.isystem.zuul.service.AuthorityService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 系统用户加载器
 * @author: YuKai Fan
 * @create: 2020-04-20 13:52
 **/
public class SystemUserLocator {
    private AuthorityService authorityService;
    private RedisUtil redisUtil;

    public SystemUserLocator(AuthorityService authorityService, RedisUtil redisUtil) {
        this.authorityService = authorityService;
        this.redisUtil = redisUtil;
    }

    public void loadSystemUserToRedis() {
        Result<List<Map<String, Object>>> result = authorityService.getAllSystemUserInfos();
        List<Map<String, Object>> list = result.getData();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> redisUtil.set(RedisConstants.SYSTEM_USER_INFO_LIST + item.get("id"), item));
        }
    }
}