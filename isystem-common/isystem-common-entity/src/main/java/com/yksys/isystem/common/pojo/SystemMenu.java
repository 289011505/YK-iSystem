package com.yksys.isystem.common.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 系统菜单表
 *
 * @author YuKai Fan
 * @create 2020-03-08 13:52:22
 */
@Data
public class SystemMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    //菜单标识
    private String id;
    //菜单名称
    private String menuName;
    //菜单编码
    private String menuCode;
    //菜单描述
    private String menuDesc;
    //上级菜单id
    private String pid;
    //路径前缀
    private String scheme;
    //打开方式:_self窗口内,_blank新窗口
    private String target;
    //序号
    private Integer sort;
    //等级
    private Integer level;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //链接
    private String url;
    //图标class
    private String icon;
    private String serviceId;

}
