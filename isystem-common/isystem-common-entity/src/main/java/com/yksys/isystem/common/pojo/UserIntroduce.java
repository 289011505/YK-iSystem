package com.yksys.isystem.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 用户简介表
 *
 * @author YuKai Fan
 * @create 2020-04-16 21:06:52
 */
@Data
@ApiModel(value = "用户简介表pojo对象", description = "用户简介表")
public class UserIntroduce implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户简介标识
    @ApiModelProperty(value = "用户简介标识", dataType = "String")
    private String id;
    //用户标识
    @ApiModelProperty(value = "用户标识", dataType = "String")
    private String userId;
    //一句简介
    @ApiModelProperty(value = "一句简介", dataType = "String")
    private String oneWord;
    //技能
    @ApiModelProperty(value = "技能", dataType = "List")
    private List<Map<String, Object>> skillsList;
    //技能
    @ApiModelProperty(value = "技能", dataType = "String")
    private String skills;
    //介绍
    @ApiModelProperty(value = "介绍", dataType = "String")
    private String introduce;
    //备注
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
    //状态:0  已禁用 1 正在使用
    @ApiModelProperty(value = "状态:0  已禁用 1 正在使用", dataType = "Integer")
    private Integer status;

}
