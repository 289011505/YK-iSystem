package com.yksys.isystem.common.pojo;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 用户权限
 * @author: YuKai Fan
 * @create: 2019-12-18 09:14
 **/
@Data
public class SystemMenu{
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
    //打开方式:_self 窗口内,_blank 新窗口
    private String target;
    //排名
    private Integer sort;
    //等级
    private Integer level;
    //链接
    private String url;
    //图标
    private String icon;
    //服务id
    private String serviceId;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;
}