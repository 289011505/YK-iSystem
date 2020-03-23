package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.model.TaskInfo;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-23 15:30
 **/
public interface TaskMapper {
    /**
     * 查询任务列表
     * @param map
     * @return
     */
    List<TaskInfo> getJobs(Map<String, Object> map);
}