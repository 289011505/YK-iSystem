package com.yksys.isystem.service.fileupload.mapper;

import com.yksys.isystem.common.pojo.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface AttachmentMapper {
    /**
     * 新增
     * @param attachment 实体
     * @return
     */
    int addAttachment(Attachment attachment);

    /**
     * 批量新增
     * @param list 集合
     */
    void addAttachments(@Param(value = "list") List<Attachment> list);

    /**
     * 根据id查询指定
     * @param id  主键
     * @return
     */
    Map<String, Object> getAttachmentById(String id);

    /**
     * 根据id修改
     * @param attachment 实体
     * @return
     */
    int editAttachmentById(Attachment attachment);

    /**
     * 批量修改
     *
     * @param attachment 实体
     * @param ids 主键集合
     */
    void editAttachmentByIds(@Param("map") Attachment attachment, @Param("list") List<String> ids);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int delAttachmentById(String id);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delAttachmentByIds(List<String> ids);

    /**
     * 真删除
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delAttachmentRealById(String id);

    /**
     * 真批量删除
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delAttachmentRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllAttachmentReal();

    /**
     * 获取所有.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getAttachments(Map<String, Object> map);
}
