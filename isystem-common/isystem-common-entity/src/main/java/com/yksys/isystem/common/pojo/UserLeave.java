package com.yksys.isystem.common.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 用户请假申请表
 *
 * @author YuKai Fan
 * @create 2020-03-17 16:08:06
 */
@Data
public class UserLeave extends BaseTask implements Serializable {
    private static final long serialVersionUID = 1L;

    //请假标识
    private String id;
    //用户id
    private String userId;
    //请假原因
    private String reason;
    //请假天数
    private Integer leaveDays;
    //用户请假路径
    private String urlPath;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //请假类型
    private Integer leaveType;

}
