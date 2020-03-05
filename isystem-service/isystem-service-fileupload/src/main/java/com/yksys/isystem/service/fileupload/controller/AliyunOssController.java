package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private AttachmentService attachmentService;
}