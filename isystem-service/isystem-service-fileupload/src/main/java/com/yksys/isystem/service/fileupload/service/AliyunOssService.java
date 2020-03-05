package com.yksys.isystem.service.fileupload.service;

import com.yksys.isystem.common.pojo.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-05 10:09
 **/
public interface AliyunOssService {
    /**
     * 新增
     * @param attachment
     * @return
     */
    Attachment addAttachment(Attachment attachment);

    /**
     * 上传文件
     * @param file
     * @return
     */
    Attachment addAttachment(MultipartFile file) throws IOException;

    /**
     * 上传文件
     * @param request
     * @return
     */
    Attachment addAttachment(HttpServletRequest request) throws IOException;
}