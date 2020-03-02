package com.yksys.isystem.web.admin.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.web.admin.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description: 用户controller
 * @author: YuKai Fan
 * @create: 2020-03-02 14:10
 **/
@RestController
@RequestMapping("/api/systemUser")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 更新用户头像
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/updateUserIcon")
    public Result updateUserIcon(HttpServletRequest request) throws IOException {
//        Result<Attachment> result = systemUserService.upload(request);
//        if (result != null && result.getCode() == 200) {
//            Attachment attachment = result.getData();
//            if (attachment != null) {
//                SystemUserVo systemUserVo = new SystemUserVo();
//                systemUserVo.setId(AppSession.getCurrentUser().getUserId());
//                systemUserVo.setUserIcon(attachment.getId());
//                systemUserService.editSystemUser(systemUserVo);
//
//                return new Result(HttpStatus.OK.value(), "更新成功", systemUserVo);
//            }
//        }
//        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
        return systemUserService.getSystemUsers(null);
    }
}