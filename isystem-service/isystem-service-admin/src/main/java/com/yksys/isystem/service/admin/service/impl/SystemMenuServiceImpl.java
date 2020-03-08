package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.pojo.SystemMenu;
import com.yksys.isystem.service.admin.mapper.SystemMenuMapper;
import com.yksys.isystem.service.admin.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class SystemMenuServiceImpl implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public SystemMenu addSystemMenu(SystemMenu systemMenu) {
        systemMenu.setId(AppUtil.randomId());
        systemMenu.setStatus(1);
        systemMenuMapper.addSystemMenu(systemMenu);
        return systemMenu;
    }

    @Override
    public Map<String, Object> getSystemMenuById(String id) {
        return systemMenuMapper.getSystemMenuById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemMenus(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemMenus(map);
    }

    public static List getTreeNodeList(List<Map<String, Object>> treeNodeList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        for (Map<String, Object> treeNode : treeNodeList) {
            if (StringUtil.isBlank(treeNode.get("pid"))) {//如果pid为空表示是一级节点
                list.add(findChildren(treeNode, treeNodeList));
            }
        }

        return list;
    }

    //递归查找子节点
    private static Map<String, Object> findChildren(Map<String, Object> treeNode, List<Map<String, Object> > treeNodes) {
        for (Map<String, Object> tn : treeNodes) {
            if (StringUtil.isNotBlank(tn.get("pid"))) {
                String[] pids = tn.get("pid").toString().split(",");
                for (String pid : pids) {
                    if (pid.equals(treeNode.get("id"))) {
                        treeNode.get("children").add(findChildren(tn, treeNodes));
                    }
                }
            }
        }
        return treeNode;
    }

    @Override
    public List<Map<String, Object>> getSystemMenus(Map<String, Object> map) {
        List<Map<String, Object>> systemMenus = systemMenuMapper.getSystemMenus(map);
        return systemMenus;
    }

    @Override
    public void editSystemMenu(SystemMenu systemMenu) {
        systemMenuMapper.editSystemMenuById(systemMenu);
    }

    @Override
    public void delSystemMenuById(String id) {
        systemMenuMapper.delSystemMenuById(id);
    }

    @Override
    public void delSystemMenuByIs(List<String> ids) {
        systemMenuMapper.delSystemMenuByIds(ids);
    }

    @Override
    public void delSystemMenuRealById(String id) {
        systemMenuMapper.delSystemMenuRealById(id);
    }

    @Override
    public void delSystemMenuRealByIds(List<String> ids) {
        systemMenuMapper.delSystemMenuRealByIds(ids);
    }

}
