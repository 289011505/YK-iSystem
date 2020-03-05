package com.yksys.isystem.service.fileupload.service.impl;

import com.yksys.isystem.common.core.utils.*;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.fileupload.mapper.AttachmentMapper;
import com.yksys.isystem.service.fileupload.oss.AliyunOssUtil;
import com.yksys.isystem.service.fileupload.service.AliyunOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-05 10:32
 **/
@Service
public class AliyunOssServiceImpl implements AliyunOssService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public Attachment addAttachment(Attachment attachment) {
        attachment.setStatus(1);
        attachment.setAttachName(attachment.getId());
        attachmentMapper.addAttachment(attachment);
        return attachment;
    }

    @Override
    public Attachment addAttachment(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public Attachment addAttachment(HttpServletRequest request) throws IOException {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
            if (CollectionUtils.isEmpty(fileMap) || CollectionUtils.isEmpty(fileMap.keySet())) {
                return null;
            }
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                if (StringUtil.isBlank(entry.getKey())) {
                    continue;
                }

                if (!entry.getValue().isEmpty()) {
                    Map<String, Object> parameterMap = MapUtil.getParameterMap(request);
                    BucketVo bucketVo = MapUtil.mapToObject(BucketVo.class, parameterMap, false);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(entry.getValue().getBytes());

                    String fileName = entry.getValue().getOriginalFilename();
                    Attachment attachment = addAttachment(entry.getKey(), fileName, entry.getValue().getSize());

                    String url = AliyunOssUtil.uploadSimpleByByte(bucketVo, byteArrayInputStream, attachment.getRemark());
                    attachment.setAttachUrl(url);
                    return this.addAttachment(attachment);
                }
            }
        }
        return null;
    }

    private Attachment addAttachment(String fileKey, String fileName, long attachSize) {
        Attachment attachment = new Attachment();
        attachment.setId(AppUtil.randomId());
        //根据后缀名判断文件类型
        if (-1 == fileName.lastIndexOf('.')) {
            attachment.setAttachOriginTitle(fileName);
            attachment.setAttachType(6);
        } else {
            attachment.setAttachOriginTitle(fileName.substring(0, fileName.lastIndexOf('.')));
            attachment.setAttachPostfix(fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()));
            attachment.setAttachType(FileUtil.getTypeFlag(FileUtil.getType(attachment.getAttachPostfix())));
        }
        attachment.setRemark(FileUtil.getFilePath(attachment.getAttachType(), attachment.getId(), attachment.getAttachPostfix()));
        attachment.setAttachSize(attachSize);
        attachment.setAttachUtily(fileKey);
        return attachment;
    }
}