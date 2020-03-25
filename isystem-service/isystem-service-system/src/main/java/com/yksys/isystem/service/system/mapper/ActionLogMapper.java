package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.pojo.ActionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统操作日志表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface ActionLogMapper {
    /**
     * 新增操作日志表
     * @param actionLog 操作日志表实体
     * @return
     */
    int addActionLog(ActionLog actionLog);

    /**
     * 批量新增操作日志表
     * @param list 操作日志表集合
     */
    void addActionLogs(@Param(value = "list") List<ActionLog> list);

    /**
     * 根据id查询指定操作日志表
     * @param id  主键
     * @return
     */
    Map<String, Object> getActionLogById(String id);

    /**
     * 根据id修改操作日志表
     * @param actionLog 操作日志表实体
     * @return
     */
    int editActionLogById(ActionLog actionLog);

    /**
     * 批量修改操作日志表
     *
     * @param actionLog 操作日志表实体
     * @param ids 主键集合
     */
    void editActionLogByIds(@Param("map") ActionLog actionLog, @Param("list") List<String> ids);

    /**
     * 根据id删除操作日志表
     * @param id
     * @return
     */
    int delActionLogById(String id);

    /**
     * 批量删除操作日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delActionLogByIds(List<String> ids);

    /**
     * 真删除操作日志表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delActionLogRealById(String id);

    /**
     * 真批量删除操作日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delActionLogRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllActionLogReal();

    /**
     * 获取所有操作日志表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getActionLogs(Map<String, Object> map);
}
