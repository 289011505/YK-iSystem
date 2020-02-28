package com.yksys.isystem.service.fileupload.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.service.fileupload.mapper.AttachmentMapper;
import com.yksys.isystem.service.fileupload.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Attachment addAttachment(Attachment attachment) {
        attachment.setId(AppUtil.randomId());
        attachment.setStatus(1);
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

}
