package com.yksys.isystem.common.model;

import com.yksys.isystem.common.pojo.SystemApi;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-10 22:55
 **/
@Data
public class AuthorityApi extends SystemApi implements Serializable {
    private static final long serialVersionUID = 1141444819706835737L;
    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 权限标识
     */
    private String authority;
}