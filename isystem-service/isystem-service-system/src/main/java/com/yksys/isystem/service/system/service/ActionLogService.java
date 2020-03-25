package com.yksys.isystem.service.system.service;
import com.yksys.isystem.common.pojo.ActionLog;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 操作日志表 service
 * @author: YuKai Fan
 * @create: 2020-03-25 21:00:39
 **/
public interface ActionLogService {
    /**
     * 新增操作日志表
     * @param actionLog
     * @return
     */
    ActionLog addActionLog(ActionLog actionLog);

    /**
     * 根据id查询操作日志表
     * @param id
     * @return
     */
    Map<String, Object> getActionLogById(String id);

    /**
     * 获取所有操作日志表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getActionLogs(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有操作日志表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getActionLogs(Map<String, Object> map);

    /**
     * 修改操作日志表
     * @param actionLog
     */
    void editActionLog(ActionLog actionLog);

    /**
     * 根据id删除操作日志表(软删除)
     * @param id
     */
    void delActionLogById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delActionLogByIs(List<String> ids);

    /**
     * 根据id删除操作日志表(真删除)
     * @param id
     */
    void delActionLogRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delActionLogRealByIds(List<String> ids);

    /**
     * 从redis中添加操作日志
     */
    void addActionLogByRedis();
}
