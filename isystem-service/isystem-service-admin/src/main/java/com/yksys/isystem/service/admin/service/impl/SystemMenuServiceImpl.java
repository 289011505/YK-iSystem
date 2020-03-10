package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.model.tree.SystemMenuTreeNode;
import com.yksys.isystem.common.model.tree.TreeNodeUtil;
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
    public void delSystemMenuByIds(List<String> ids) {
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

    @Override
    public List<SystemMenuTreeNode> getSystemMenusNodeList(Map<String, Object> map) {
        List<SystemMenuTreeNode> systemMenus = systemMenuMapper.getSystemMenusNodeList(map);
        return TreeNodeUtil.getTreeNodeList(systemMenus);
    }

}
