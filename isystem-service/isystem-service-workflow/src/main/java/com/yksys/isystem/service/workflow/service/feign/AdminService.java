package com.yksys.isystem.service.workflow.service.feign;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.workflow.service.feign.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-14 11:31
 **/
@FeignClient(value = "isystem-service-admin", fallbackFactory = AdminServiceFallback.class)
public interface AdminService {

    /**
     * 获取用户集合
     * @param map
     * @return
     */
    @GetMapping("/api/systemUser/getSystemUsers/noPage")
    Result getSystemUsersNoPage(@RequestParam Map<String, Object> map);

    /**
     * 获取角色集合
     * @param map
     * @return
     */
    @GetMapping("/api/systemRole/getSystemRoles/noPage")
    Result getSystemRolesNoPage(@RequestParam Map<String, Object> map);
}