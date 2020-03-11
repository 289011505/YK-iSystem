package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.SystemAuthority;
import org.springframework.beans.BeanUtils;


/**
 * 系统权限表 vo类
 *
 * @author YuKai Fan
 * @create 2020-01-16 14:11:47
 */
@Data
public class SystemAuthorityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //权限标识
    private String id;
    //权限编码
    private String authority;
    //api接口id
    private String apiId;
    //菜单标识
    private String menuId;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private int status;

    public SystemAuthority convert() {
        SystemAuthorityVoConvert systemAuthorityVoConvert = new SystemAuthorityVoConvert();
        SystemAuthority systemAuthority = systemAuthorityVoConvert.convert(this);
        return systemAuthority;
    }

    private static class SystemAuthorityVoConvert extends Converter<SystemAuthorityVo, SystemAuthority> {

        @Override
        protected SystemAuthority doForward(SystemAuthorityVo systemAuthorityVo) {
            SystemAuthority systemAuthority = new SystemAuthority();
            BeanUtils.copyProperties(systemAuthorityVo, systemAuthority);
            return systemAuthority;
        }

        @Override
        protected SystemAuthorityVo doBackward(SystemAuthority systemAuthority) {
            return null;
        }
    }

}
