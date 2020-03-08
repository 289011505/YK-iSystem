package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.SystemApi;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 网关api接口表 service
 * @author: YuKai Fan
 * @create: 2020-01-15 20:26:43
 **/
public interface SystemApiService {
    /**
     * 新增网关api接口表
     * @param systemApi
     * @return
     */
    SystemApi addSystemApi(SystemApi systemApi);

    /**
     * 根据id查询网关api接口表
     * @param id
     * @return
     */
    Map<String, Object> getSystemApiById(String id);

    /**
     * 获取所有网关api接口表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemApis(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有网关api接口表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemApis(Map<String, Object> map);

    /**
     * 修改网关api接口表
     * @param systemApi
     */
    void editSystemApi(SystemApi systemApi);

    /**
     * 根据id删除网关api接口表(软删除)
     * @param id
     */
    void delSystemApiById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemApiByIs(List<String> ids);

    /**
     * 根据id删除网关api接口表(真删除)
     * @param id
     */
    void delSystemApiRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemApiRealByIds(List<String> ids);
}
