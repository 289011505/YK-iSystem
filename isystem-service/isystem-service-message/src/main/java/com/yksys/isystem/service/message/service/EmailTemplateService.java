package com.yksys.isystem.service.message.service;
import com.yksys.isystem.common.pojo.EmailTemplate;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮件模板表 service
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 **/
public interface EmailTemplateService {
    /**
     * 新增邮件模板表
     * @param emailTemplate
     * @return
     */
    EmailTemplate addEmailTemplate(EmailTemplate emailTemplate);

    /**
     * 根据id查询邮件模板表
     * @param id
     * @return
     */
    Map<String, Object> getEmailTemplateById(String id);

    /**
     * 获取所有邮件模板表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailTemplates(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有邮件模板表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailTemplates(Map<String, Object> map);

    /**
     * 修改邮件模板表
     * @param emailTemplate
     */
    void editEmailTemplate(EmailTemplate emailTemplate);

    /**
     * 根据id删除邮件模板表(软删除)
     * @param id
     */
    void delEmailTemplateById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delEmailTemplateByIs(List<String> ids);

    /**
     * 根据id删除邮件模板表(真删除)
     * @param id
     */
    void delEmailTemplateRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delEmailTemplateRealByIds(List<String> ids);
}
