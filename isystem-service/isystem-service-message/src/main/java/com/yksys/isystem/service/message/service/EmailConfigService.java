package com.yksys.isystem.service.message.service;
import com.yksys.isystem.common.pojo.EmailConfig;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮箱配置表 service
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 **/
public interface EmailConfigService {
    /**
     * 新增邮箱配置表
     * @param emailConfig
     * @return
     */
    EmailConfig addEmailConfig(EmailConfig emailConfig);

    /**
     * 根据id查询邮箱配置表
     * @param id
     * @return
     */
    Map<String, Object> getEmailConfigById(String id);

    /**
     * 获取所有邮箱配置表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailConfigs(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有邮箱配置表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailConfigs(Map<String, Object> map);

    /**
     * 修改邮箱配置表
     * @param emailConfig
     */
    void editEmailConfig(EmailConfig emailConfig);

    /**
     * 根据id删除邮箱配置表(软删除)
     * @param id
     */
    void delEmailConfigById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delEmailConfigByIs(List<String> ids);

    /**
     * 根据id删除邮箱配置表(真删除)
     * @param id
     */
    void delEmailConfigRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delEmailConfigRealByIds(List<String> ids);
}
