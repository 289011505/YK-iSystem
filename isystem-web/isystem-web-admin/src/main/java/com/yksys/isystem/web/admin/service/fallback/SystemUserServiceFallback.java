package com.yksys.isystem.web.admin.service.fallback;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.web.admin.service.SystemUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-02 14:11
 **/
@Component
public class SystemUserServiceFallback implements FallbackFactory<SystemUserService> {
    @Override
    public SystemUserService create(Throwable throwable) {
        return new SystemUserService() {
            @Override
            public Result upload(HttpServletRequest request) throws IOException {
                return Fallback.badGateway();
            }

            @Override
            public Result editSystemUser(SystemUserVo systemUserVo) {
                return Fallback.badGateway();
            }

            @Override
            public Result getSystemUsers(Map<String, Object> map) {
                return Fallback.badGateway();
            }
        };
    }
}