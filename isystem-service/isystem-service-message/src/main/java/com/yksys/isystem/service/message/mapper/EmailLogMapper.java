package com.yksys.isystem.service.message.mapper;

import com.yksys.isystem.common.pojo.EmailLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统邮件日志表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface EmailLogMapper {
    /**
     * 新增邮件日志表
     * @param emailLog 邮件日志表实体
     * @return
     */
    int addEmailLog(EmailLog emailLog);

    /**
     * 批量新增邮件日志表
     * @param list 邮件日志表集合
     */
    void addEmailLogs(@Param(value = "list") List<EmailLog> list);

    /**
     * 根据id查询指定邮件日志表
     * @param id  主键
     * @return
     */
    Map<String, Object> getEmailLogById(String id);

    /**
     * 根据id修改邮件日志表
     * @param emailLog 邮件日志表实体
     * @return
     */
    int editEmailLogById(EmailLog emailLog);

    /**
     * 批量修改邮件日志表
     *
     * @param emailLog 邮件日志表实体
     * @param ids 主键集合
     */
    void editEmailLogByIds(@Param("map") EmailLog emailLog, @Param("list") List<String> ids);

    /**
     * 根据id删除邮件日志表
     * @param id
     * @return
     */
    int delEmailLogById(String id);

    /**
     * 批量删除邮件日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailLogByIds(List<String> ids);

    /**
     * 真删除邮件日志表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delEmailLogRealById(String id);

    /**
     * 真批量删除邮件日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delEmailLogRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllEmailLogReal();

    /**
     * 获取所有邮件日志表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getEmailLogs(Map<String, Object> map);
}
