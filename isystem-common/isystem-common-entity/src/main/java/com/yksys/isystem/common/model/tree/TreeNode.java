package com.yksys.isystem.common.model.tree;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yk-isystem
 * @description: 树节点
 * @author: YuKai Fan
 * @create: 2020-03-08 15:05
 **/
@Data
public class TreeNode implements Serializable {
    private static final long serialVersionUID = -8612833223848423094L;
    //唯一标识
    private String id;
    //名称
    private String name;
    //父id
    private String pid;
    //层级
    private String level;
    //子集合
    private List<TreeNode> children = Lists.newArrayList();

}