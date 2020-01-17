package com.yksys.isystem.common.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 角色权限表
 *
 * @author YuKai Fan
 * @create 2020-01-14 15:16:26
 */
@Data
public class AuthorityRole implements Serializable {
    private static final long serialVersionUID = 1L;

    //角色标识
    private String roleId;
    //权限标识
    private String authorityId;
    //唯一标识
    private String id;

}
