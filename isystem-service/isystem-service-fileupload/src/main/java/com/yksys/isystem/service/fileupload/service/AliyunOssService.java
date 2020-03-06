package com.yksys.isystem.service.fileupload.service;

import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.vo.BucketVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    Attachment addAttachment(MultipartFile file, BucketVo bucketVo) throws IOException;

    /**
     * 上传文件
     * @param file
     * @return
     */
    Attachment addAttachment(MultipartFile file, String ownId, BucketVo bucketVo) throws IOException;

    /**
     * 上传文件
     * @param request
     * @return
     */
    Attachment addAttachment(HttpServletRequest request) throws IOException;

    /**
     * 上传文件
     * @param request
     * @return
     */
    Attachment addAttachment(HttpServletRequest request, String ownId) throws IOException;

    /**
     * 批量上传
     * @param request
     * @return
     * @throws IOException
     */
    List<Attachment> addAttachments(HttpServletRequest request) throws IOException;
}