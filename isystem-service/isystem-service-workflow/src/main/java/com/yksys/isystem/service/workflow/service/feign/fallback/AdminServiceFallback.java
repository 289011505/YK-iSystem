package com.yksys.isystem.service.workflow.service.feign.fallback;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.service.workflow.service.feign.AdminService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-16 09:34
 **/
@Component
public class AdminServiceFallback implements FallbackFactory<AdminService> {
    @Override
    public AdminService create(Throwable throwable) {
        return new AdminService() {
            @Override
            public Result getSystemUsersNoPage(Map<String, Object> map) {
                return Fallback.badGateway();
            }

            @Override
            public Result getSystemRolesNoPage(Map<String, Object> map) {
                return Fallback.badGateway();
            }

            @Override
            public Result getAllUserRoles() {
                return Fallback.badGateway();
            }
        };
    }
}