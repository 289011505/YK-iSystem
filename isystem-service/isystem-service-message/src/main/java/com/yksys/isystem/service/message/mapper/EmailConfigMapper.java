package com.yksys.isystem.service.message.mapper;

import com.yksys.isystem.common.pojo.EmailConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统邮箱配置表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface EmailConfigMapper {
    /**
     * 新增邮箱配置表
     * @param emailConfig 邮箱配置表实体
     * @return
     */
    int addEmailConfig(EmailConfig emailConfig);

    /**
     * 批量新增邮箱配置表
     * @param list 邮箱配置表集合
     */
    void addEmailConfigs(@Param(value = "list") List<EmailConfig> list);

    /**
     * 根据id查询指定邮箱配置表
     * @param id  主键
     * @return
     */
    Map<String, Object> getEmailConfigById(String id);

    /**
     * 根据id修改邮箱配置表
     * @param emailConfig 邮箱配置表实体
     * @return
     */
    int editEmailConfigById(EmailConfig emailConfig);

    /**
     * 批量修改邮箱配置表
     *
     * @param emailConfig 邮箱配置表实体
     * @param ids 主键集合
     */
    void editEmailConfigByIds(@Param("map") EmailConfig emailConfig, @Param("list") List<String> ids);

    /**
     * 根据id删除邮箱配置表
     * @param id
     * @return
     */
    int delEmailConfigById(String id);

    /**
     * 批量删除邮箱配置表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailConfigByIds(List<String> ids);

    /**
     * 真删除邮箱配置表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delEmailConfigRealById(String id);

    /**
     * 真批量删除邮箱配置表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailConfigRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllEmailConfigReal();

    /**
     * 获取所有邮箱配置表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getEmailConfigs(Map<String, Object> map);
}
