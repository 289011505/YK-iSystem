package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.service.fileupload.service.AliyunOssService;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-05 11:12
 **/
@RestController
@RequestMapping("/api/oss")
public class AliyunOssController {
    @Autowired
    private AliyunOssService aliyunOssService;

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) throws IOException {
        List<Attachment> attachments = aliyunOssService.addAttachments(request);
        return null;
    }
}