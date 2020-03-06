package com.yksys.isystem.service.fileupload.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.file.FileUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.service.fileupload.configuration.FastDFSClient;
import com.yksys.isystem.service.fileupload.mapper.AttachmentMapper;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-03 20:05
 **/
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private FastDFSClient fastDFSClient;

    @Override
    public Attachment addAttachment(Attachment attachment) {
        attachment.setId(AppUtil.randomId());
        attachment.setStatus(1);
        attachment.setAttachName(attachment.getId());
        attachmentMapper.addAttachment(attachment);
        return attachment;
    }

    @Override
    public Map<String, Object> getAttachmentById(String id) {
        return attachmentMapper.getAttachmentById(id);
    }

    @Override
    public List<Map<String, Object>> getAttachments(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getAttachments(map);
    }

    @Override
    public List<Map<String, Object>> getAttachments(Map<String, Object> map) {
        return attachmentMapper.getAttachments(map);
    }

    @Override
    public void editAttachment(Attachment attachment) {
        attachmentMapper.editAttachmentById(attachment);
    }

    @Override
    public void delAttachmentById(String id) {
        attachmentMapper.delAttachmentById(id);
    }

    @Override
    public void delAttachmentByIs(List<String> ids) {
        attachmentMapper.delAttachmentByIds(ids);
    }

    @Override
    public void delAttachmentRealById(String id) {
        attachmentMapper.delAttachmentRealById(id);
    }

    @Override
    public void delAttachmentRealByIds(List<String> ids) {
        attachmentMapper.delAttachmentRealByIds(ids);
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
                    String fileName = entry.getValue().getOriginalFilename();
                    String url = fastDFSClient.uploadFile(entry.getValue());
                    Attachment attachment = addAttachment(entry.getKey(), fileName, entry.getValue().getSize(), url);
                    return attachment;
                }
            }
        }
        return null;
    }

    @Override
    public Attachment addAttachment(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String url = fastDFSClient.uploadFile(file);
        Attachment attachment = addAttachment("file", fileName, file.getSize(), url);
        return attachment;
    }

    private Attachment addAttachment(String fileKey, String fileName, long attachSize, String url) {
        Attachment attachment = new Attachment();
        //根据后缀名判断文件类型
        if (-1 == fileName.lastIndexOf('.')) {
            attachment.setAttachOriginTitle(fileName);
        } else {
            attachment.setAttachOriginTitle(fileName.substring(0, fileName.lastIndexOf('.')));
            attachment.setAttachPostfix(fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()));
            attachment.setAttachType(FileUtil.getTypeFlag(FileUtil.getType(attachment.getAttachPostfix())));
        }
        attachment.setAttachSize(attachSize);
        attachment.setAttachUtily(fileKey);
        attachment.setAttachUrl(url);
        return this.addAttachment(attachment);
    }

}
