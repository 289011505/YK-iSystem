package com.yksys.isystem.common.model.register;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 注册实体抽象类
 * @author: YuKai Fan
 * @create: 2020-04-09 10:59
 **/
@ApiModel(value = "邮箱注册抽象对象", description = "抽象邮箱注册")
@Data
public abstract class AbstractRegister implements Serializable {
    private static final long serialVersionUID = 4869995870009859239L;

    //用户名
    @ApiModelProperty(value = "用户名", dataType = "String")
    @NotNull(message = "用户名不能为空")
    private String userName;

    //密码
    @ApiModelProperty(value = "密码", dataType = "String")
    @NotNull(message = "密码不能为空")
    private String password;

    //网页验证码
    @ApiModelProperty(value = "网页验证码", dataType = "String")
    private String captcha;

    //发送验证码
    @ApiModelProperty(value = "验证码", dataType = "String")
    @NotNull(message = "验证码不能为空")
    private String checkCode;
}