package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.UserActivity;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 用户动态表 vo类
 *
 * @author YuKai Fan
 * @create 2020-04-17 09:45:03
 */
@Data
@ApiModel(value = "用户动态表vo对象", description = "用户动态表")
public class UserActivityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户动态标识
    @ApiModelProperty(value = "用户动态标识", dataType = "String")
    private String id;
    //用户标识
    @ApiModelProperty(value = "用户标识", dataType = "String")
    private String userId;
    //动态内容
    @ApiModelProperty(value = "动态内容", dataType = "String")
    private String content;
    //动态图片
    @ApiModelProperty(value = "动态图片", dataType = "String")
    private String activityImg;
    //转发数
    @ApiModelProperty(value = "转发数", dataType = "Integer")
    private Integer forwardNum;
    //评论数
    @ApiModelProperty(value = "评论数", dataType = "Integer")
    private Integer commentNum;
    //点赞数
    @ApiModelProperty(value = "点赞数", dataType = "Integer")
    private Integer likeNum;
    //序号
    @ApiModelProperty(value = "序号", dataType = "Integer")
    private Integer sort;
    //类型
    @ApiModelProperty(value = "类型", dataType = "Integer")
    private Integer type;
    //备注
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
    //状态:0  已禁用 1 正在使用
    @ApiModelProperty(value = "状态:0  已禁用 1 正在使用", dataType = "Integer")
    private Integer status;

    public UserActivity convert() {
        UserActivityVoConvert userActivityVoConvert = new UserActivityVoConvert();
        UserActivity userActivity = userActivityVoConvert.convert(this);
        return userActivity;
    }

    private static class UserActivityVoConvert extends Converter<UserActivityVo, UserActivity> {

        @Override
        protected UserActivity doForward(UserActivityVo userActivityVo) {
            UserActivity userActivity = new UserActivity();
            BeanUtils.copyProperties(userActivityVo, userActivity);
            return userActivity;
        }

        @Override
        protected UserActivityVo doBackward(UserActivity userActivity) {
            return null;
        }
    }

}
