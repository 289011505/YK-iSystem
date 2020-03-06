package com.yksys.isystem.service.fileupload.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.fileupload.service.AliyunOssService;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    /**
     * 阿里云oss文件上传
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadOSS")
    public Result uploadOSS(HttpServletRequest request) throws IOException {
        Attachment attachment = aliyunOssService.addAttachment(request);
        if (attachment == null) {
            throw new ParameterException("上传失败");
        }
        return new Result(HttpStatus.OK.value(), "上传成功", attachment);
    }

    /**
     * 阿里云oss文件上传
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadOSSByOwnId")
    public Result uploadOSSByOwnId(HttpServletRequest request) throws IOException {
        Map<String, Object> parameterMap = MapUtil.getParameterMap(request);
        String ownerId = parameterMap.get("ownerId").toString();
        Attachment attachment = aliyunOssService.addAttachment(request, ownerId);
        if (attachment == null) {
            throw new ParameterException("上传失败");
        }
        return new Result(HttpStatus.OK.value(), "上传成功", attachment);
    }

    /**
     * 阿里云oss文件上传
     * @param file
     * @param bucketVo
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadOSSByFile")
    public Result uploadOSSByFile(@RequestPart("file") MultipartFile file, @RequestPart("bucketVo") BucketVo bucketVo) throws IOException {
        Attachment attachment = aliyunOssService.addAttachment(file, bucketVo);
        if (attachment == null) {
            throw new ParameterException("上传失败");
        }
        return new Result(HttpStatus.OK.value(), "上传成功", attachment);
    }
}