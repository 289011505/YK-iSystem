package com.yksys.isystem.service.fileupload.service;
import com.yksys.isystem.common.pojo.Attachment;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:  service
 * @author: YuKai Fan
 * @create: 2020-02-28 15:46:32
 **/
public interface AttachmentService {
    /**
     * 新增
     * @param attachment
     * @return
     */
    Attachment addAttachment(Attachment attachment);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Map<String, Object> getAttachmentById(String id);

    /**
     * 获取所有(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getAttachments(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getAttachments(Map<String, Object> map);

    /**
     * 修改
     * @param attachment
     */
    void editAttachment(Attachment attachment);

    /**
     * 根据id删除(软删除)
     * @param id
     */
    void delAttachmentById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delAttachmentByIs(List<String> ids);

    /**
     * 根据id删除(真删除)
     * @param id
     */
    void delAttachmentRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delAttachmentRealByIds(List<String> ids);
}
