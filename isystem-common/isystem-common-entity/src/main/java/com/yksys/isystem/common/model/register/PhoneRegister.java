package com.yksys.isystem.common.model.register;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: 手机号注册
 * @author: YuKai Fan
 * @create: 2020-04-09 11:03
 **/
@ApiModel(value = "手机号注册对象", description = "手机注册")
@Data
public class PhoneRegister extends AbstractRegister {
    //手机号
    @ApiModelProperty(value = "手机号", dataType = "String")
    private String phone;
}