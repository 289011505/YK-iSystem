package com.yksys.isystem.common.model.register;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: 邮箱注册
 * @author: YuKai Fan
 * @create: 2020-04-09 11:02
 **/
@ApiModel(value = "邮箱注册对象", description = "邮箱注册")
@Data
public class EmailRegister extends AbstractRegister {
    //邮箱
    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String email;
}