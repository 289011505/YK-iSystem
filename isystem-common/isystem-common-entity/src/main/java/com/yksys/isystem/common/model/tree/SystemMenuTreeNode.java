package com.yksys.isystem.common.model.tree;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-09 08:50
 **/
@Data
public class SystemMenuTreeNode extends TreeNode implements Serializable {
    private static final long serialVersionUID = 7890744126456915342L;

    //菜单名称
    private String menuName;
    //菜单编码
    private String menuCode;
    //菜单描述
    private String menuDesc;
    //路径前缀
    private String scheme;
    //打开方式:_self窗口内,_blank新窗口
    private String target;
    //序号
    private Integer sort;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //链接
    private String url;
    //图标class
    private String icon;
    private String serviceId;
    private String authorityId;
    private String authority;
    private String apiId;
    private String apiName;
    private String pMenuName;
}