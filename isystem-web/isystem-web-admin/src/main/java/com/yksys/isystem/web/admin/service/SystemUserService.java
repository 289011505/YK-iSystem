package com.yksys.isystem.web.admin.service;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.web.admin.service.fallback.SystemUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
@FeignClient(value = "isystem-service-admin", fallbackFactory = SystemUserServiceFallback.class)
public interface SystemUserService {

    /**
     * 用户上传
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/api/fileupload/upload")
    Result upload(HttpServletRequest request) throws IOException;

    /**
     * 修改用户信息
     * @param systemUserVo
     * @return
     */
    @PutMapping("/api/systemUser/editSystemUser")
    Result editSystemUser(@RequestBody SystemUserVo systemUserVo);

    @GetMapping("/api/systemUser/getSystemUsers/noPage")
    Result getSystemUsers(@RequestParam Map<String, Object> map);
}