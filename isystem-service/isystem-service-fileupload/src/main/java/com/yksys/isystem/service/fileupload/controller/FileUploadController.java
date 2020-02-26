package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.fileupload.configuration.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String url = fastDFSClient.uploadFile(file);
        return new Result(HttpStatus.OK.value(), "上传成功", url);
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