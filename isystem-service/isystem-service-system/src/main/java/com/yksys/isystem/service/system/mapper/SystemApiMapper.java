package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.pojo.SystemApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统网关api接口表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface SystemApiMapper {
    /**
     * 新增网关api接口表
     * @param systemApi 网关api接口表实体
     * @return
     */
    int addSystemApi(SystemApi systemApi);

    /**
     * 批量新增网关api接口表
     * @param list 网关api接口表集合
     */
    void addSystemApis(@Param(value = "list") List<SystemApi> list);

    /**
     * 根据id查询指定网关api接口表
     * @param id  主键
     * @return
     */
    Map<String, Object> getSystemApiById(String id);

    /**
     * 根据id修改网关api接口表
     * @param user 网关api接口表实体
     * @return
     */
    int editSystemApiById(SystemApi systemApi);

    /**
     * 批量修改网关api接口表
     *
     * @param systemApi 网关api接口表实体
     * @param ids 主键集合
     */
    void editSystemApiByIds(@Param("map") SystemApi systemApi, @Param("list") List<String> ids);

    /**
     * 根据id删除网关api接口表
     * @param id
     * @return
     */
    int delSystemApiById(String id);

    /**
     * 批量删除网关api接口表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemApiByIds(List<String> ids);

    /**
     * 真删除网关api接口表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delSystemApiRealById(String id);

    /**
     * 真批量删除网关api接口表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemApiRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllSystemApiReal();

    /**
     * 获取所有网关api接口表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getSystemApis(Map<String, Object> map);
}
