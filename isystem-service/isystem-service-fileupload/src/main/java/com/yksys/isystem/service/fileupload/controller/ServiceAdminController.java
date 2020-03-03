package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.security.YkUserDetails;
import com.yksys.isystem.common.core.utils.WebUtil;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import com.yksys.isystem.service.fileupload.service.feign.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-03 15:25
 **/
@RestController
@RequestMapping("/api/admin")
public class ServiceAdminController {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private RedisTokenStore redisTokenStore;

    /**
     * 更新用户头像
     *
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/updateUserIcon")
    public Result updateUserIcon(HttpServletRequest request) throws IOException {
        Attachment attachment = attachmentService.addAttachment(request);
        if (attachment != null) {
            SystemUserVo systemUserVo = new SystemUserVo();
            YkUserDetails currentUser = AppSession.getCurrentUser();
            systemUserVo.setId(currentUser.getUserId());
            systemUserVo.setUserIcon(attachment.getId());
            systemUserService.editSystemUser(systemUserVo);

            //更新当前登录的用户
            currentUser.setUserIcon(attachment.getId());
            currentUser.setUserIconUrl(attachment.getAttachUrl());
            AppSession.updateCurrentUser(redisTokenStore, currentUser);

            return new Result(HttpStatus.OK.value(), "更新成功", attachment);
        }
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
    }
}