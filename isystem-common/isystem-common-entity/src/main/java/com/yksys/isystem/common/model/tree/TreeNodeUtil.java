package com.yksys.isystem.common.model.tree;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.utils.StringUtil;

import java.util.List;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-09 13:35
 **/
public class TreeNodeUtil {
    public static List getTreeNodeList(List treeNodeList) {
        List<TreeNode> list = Lists.newArrayList();
        List<TreeNode> trees = treeNodeList;
        for (TreeNode treeNode : trees) {
            if (StringUtil.isBlank(treeNode.getPid()) || "0".equals(treeNode.getPid())) {//如果pid为空表示是一级节点
                list.add(findChildren(treeNode, trees));
            }
        }

        return list;
    }

    //递归查找子节点
    private static TreeNode findChildren(TreeNode treeNode, List<TreeNode> treeNodes) {
        for (TreeNode tn : treeNodes) {
            if (StringUtil.isNotBlank(tn.getPid())) {
                String[] pids = tn.getPid().split(",");
                for (String pid : pids) {
                    if (pid.equals(treeNode.getId())) {
                        treeNode.getChildren().add(findChildren(tn, treeNodes));
                    }
                }
            }
        }
        return treeNode;
    }
}