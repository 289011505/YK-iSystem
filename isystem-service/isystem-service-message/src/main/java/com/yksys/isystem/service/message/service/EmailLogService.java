package com.yksys.isystem.service.message.service;
import com.yksys.isystem.common.pojo.EmailLog;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 邮件日志表 service
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 **/
public interface EmailLogService {
    /**
     * 新增邮件日志表
     * @param emailLog
     * @return
     */
    EmailLog addEmailLog(EmailLog emailLog);

    /**
     * 根据id查询邮件日志表
     * @param id
     * @return
     */
    Map<String, Object> getEmailLogById(String id);

    /**
     * 获取所有邮件日志表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailLogs(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有邮件日志表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getEmailLogs(Map<String, Object> map);

    /**
     * 修改邮件日志表
     * @param emailLog
     */
    void editEmailLog(EmailLog emailLog);

    /**
     * 根据id删除邮件日志表(软删除)
     * @param id
     */
    void delEmailLogById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delEmailLogByIs(List<String> ids);

    /**
     * 根据id删除邮件日志表(真删除)
     * @param id
     */
    void delEmailLogRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delEmailLogRealByIds(List<String> ids);
}
