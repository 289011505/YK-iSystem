package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.service.fileupload.configuration.FastDFSClient;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

/**
 * @program: yk-isystem
 * @description: 文件controller
 * @author: YuKai Fan
 * @create: 2020-02-15 16:42
 **/
@RestController
@RequestMapping("/api/fileupload")
public class FileUploadController {
    @Autowired
    private FastDFSClient fastDFSClient;
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 图片上传
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) throws IOException {
        Attachment attachment = attachmentService.addAttachment(request);
        return new Result(HttpStatus.OK.value(), "上传成功", attachment);
    }

    /**
     * 文件下载
     * @param fileUrl 文件url
     * @param response
     * @throws IOException
     */
    @PostMapping("/download")
    public void download(String fileUrl, HttpServletResponse response) throws IOException {
        byte[] data = fastDFSClient.download(fileUrl);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("test.jpg", "UTF-8"));

        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(data, outputStream);
    }
}