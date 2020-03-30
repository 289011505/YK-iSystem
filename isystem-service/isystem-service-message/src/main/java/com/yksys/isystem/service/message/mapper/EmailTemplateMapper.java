package com.yksys.isystem.service.message.mapper;

import com.yksys.isystem.common.pojo.EmailTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统邮件模板表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface EmailTemplateMapper {
    /**
     * 新增邮件模板表
     * @param emailTemplate 邮件模板表实体
     * @return
     */
    int addEmailTemplate(EmailTemplate emailTemplate);

    /**
     * 批量新增邮件模板表
     * @param list 邮件模板表集合
     */
    void addEmailTemplates(@Param(value = "list") List<EmailTemplate> list);

    /**
     * 根据id查询指定邮件模板表
     * @param id  主键
     * @return
     */
    Map<String, Object> getEmailTemplateById(String id);

    /**
     * 根据id修改邮件模板表
     * @param emailTemplate 邮件模板表实体
     * @return
     */
    int editEmailTemplateById(EmailTemplate emailTemplate);

    /**
     * 批量修改邮件模板表
     *
     * @param emailTemplate 邮件模板表实体
     * @param ids 主键集合
     */
    void editEmailTemplateByIds(@Param("map") EmailTemplate emailTemplate, @Param("list") List<String> ids);

    /**
     * 根据id删除邮件模板表
     * @param id
     * @return
     */
    int delEmailTemplateById(String id);

    /**
     * 批量删除邮件模板表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailTemplateByIds(List<String> ids);

    /**
     * 真删除邮件模板表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delEmailTemplateRealById(String id);

    /**
     * 真批量删除邮件模板表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailTemplateRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllEmailTemplateReal();

    /**
     * 获取所有邮件模板表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getEmailTemplates(Map<String, Object> map);
}
