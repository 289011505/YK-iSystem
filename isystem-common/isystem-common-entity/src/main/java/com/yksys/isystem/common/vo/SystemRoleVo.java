package com.yksys.isystem.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.SystemRole;
import org.springframework.beans.BeanUtils;


/**
 * 角色表 vo类
 *
 * @author YuKai Fan
 * @create 2020-01-16 14:11:47
 */
@Data
public class SystemRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //角色标识
    private String id;
    //角色名称
    private String roleName;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //角色编码
    private String roleCode;
    //权限id集合
    private List<String> authorityIds;

    public SystemRole convert() {
        SystemRoleVoConvert systemRoleVoConvert = new SystemRoleVoConvert();
        SystemRole systemRole = systemRoleVoConvert.convert(this);
        return systemRole;
    }

    private static class SystemRoleVoConvert extends Converter<SystemRoleVo, SystemRole> {

        @Override
        protected SystemRole doForward(SystemRoleVo systemRoleVo) {
            SystemRole systemRole = new SystemRole();
            BeanUtils.copyProperties(systemRoleVo, systemRole);
            return systemRole;
        }

        @Override
        protected SystemRoleVo doBackward(SystemRole systemRole) {
            return null;
        }
    }

}
