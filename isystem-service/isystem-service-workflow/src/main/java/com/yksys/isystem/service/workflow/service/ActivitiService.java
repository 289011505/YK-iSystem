package com.yksys.isystem.service.workflow.service;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.common.vo.ActivitiModelVo;
import org.activiti.engine.repository.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-12 16:19
 **/
public interface ActivitiService {

    /**
     * 获取所有模型列表(分页)
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<Model> getModels(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有模型列表
     * @param map
     * @return
     */
    List<Model> getModels(Map<String, Object> map);

    /**
     * 创建新的模型
     * @param request
     * @param response
     * @param activitiModelVo
     * @return
     */
    String createNewModel(HttpServletRequest request, HttpServletResponse response, ActivitiModelVo activitiModelVo);

    /**
     * 删除模型
     * @param id
     */
    void delModel(String id);

    /**
     * 批量删除
     * @param ids
     */
    void delModelByIds(String[] ids);

    /**
     * 同步用户, 角色数据 到Activiti表中
     */
    void synchronizeData();

    /**
     * 根据模型id部署
     * @param id
     */
    void deployByModelId(String id);
}