package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.UserLeave;
import org.springframework.beans.BeanUtils;


/**
 * 用户请假申请表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-17 16:08:06
 */
@Data
public class UserLeaveVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //请假标识
    private String id;
    //用户id
    private String userId;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //请假原因
    private String reason;
    //请假天数
    private Integer leaveDays;
    //流程实例id
    private String processInstanceId;
    //用户请假路径
    private String urlPath;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //请假类型
    private Integer leaveType;

    public UserLeave convert() {
        UserLeaveVoConvert userLeaveVoConvert = new UserLeaveVoConvert();
        UserLeave userLeave = userLeaveVoConvert.convert(this);
        return userLeave;
    }

    private static class UserLeaveVoConvert extends Converter<UserLeaveVo, UserLeave> {

        @Override
        protected UserLeave doForward(UserLeaveVo userLeaveVo) {
            UserLeave userLeave = new UserLeave();
            BeanUtils.copyProperties(userLeaveVo, userLeave);
            return userLeave;
        }

        @Override
        protected UserLeaveVo doBackward(UserLeave userLeave) {
            return null;
        }
    }

}
