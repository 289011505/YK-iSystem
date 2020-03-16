package com.yksys.isystem.service.workflow.service.feign;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.workflow.service.feign.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-16 09:34
 **/
@FeignClient(value = "isystem-service-admin", fallbackFactory = AdminServiceFallback.class)
public interface AdminService {

    /**
     * 获取用户列表
     * @param map
     * @return
     */
    @GetMapping("/api/systemUser/getSystemUsers/noPage")
    Result<List<Map<String, Object>>> getSystemUsersNoPage(@RequestParam Map<String, Object> map);

    /**
     * 获取角色列表
     * @param map
     * @return
     */
    @GetMapping("/api/systemRole/getSystemRoles/noPage")
    Result<List<Map<String, Object>>> getSystemRolesNoPage(@RequestParam Map<String, Object> map);

    /**
     * 获取角色用户关系列表
     * @return
     */
    @GetMapping("/api/systemUser/getAllUserRoles")
    Result<List<Map<String, Object>>> getAllUserRoles();
}