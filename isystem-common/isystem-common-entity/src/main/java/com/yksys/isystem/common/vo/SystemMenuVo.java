package com.yksys.isystem.common.vo;

import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.SystemMenu;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;


/**
 * 系统菜单表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-08 13:52:22
 */
@Data
public class SystemMenuVo implements Serializable {
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
    //服务id
    private String serviceId;
    //api
    private SystemApiVo systemApiVo;
    //authority
    private SystemAuthorityVo systemAuthorityVo;

    public SystemMenu convert() {
        SystemMenuVoConvert systemMenuVoConvert = new SystemMenuVoConvert();
        SystemMenu systemMenu = systemMenuVoConvert.convert(this);
        return systemMenu;
    }

    private static class SystemMenuVoConvert extends Converter<SystemMenuVo, SystemMenu> {

        @Override
        protected SystemMenu doForward(SystemMenuVo systemMenuVo) {
            SystemMenu systemMenu = new SystemMenu();
            BeanUtils.copyProperties(systemMenuVo, systemMenu);
            return systemMenu;
        }

        @Override
        protected SystemMenuVo doBackward(SystemMenu systemMenu) {
            return null;
        }
    }

}
