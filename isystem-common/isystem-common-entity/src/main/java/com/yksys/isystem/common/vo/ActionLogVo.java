package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.ActionLog;
import org.springframework.beans.BeanUtils;


/**
 * 操作日志表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-25 21:00:39
 */
@Data
public class ActionLogVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //日志标识
    private String id;
    //日志类型名称
    private String name;
    //日志类型
    private String type;
    //操作ip
    private String ipAddr;
    //操作时间
    private String actionTime;
    //项目名称
    private String projectName;
    //输入参数
    private String inputParam;
    //输出参数
    private String outputParam;
    //异常信息
    private String exceptionInfo;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;

    public ActionLog convert() {
        ActionLogVoConvert actionLogVoConvert = new ActionLogVoConvert();
        ActionLog actionLog = actionLogVoConvert.convert(this);
        return actionLog;
    }

    private static class ActionLogVoConvert extends Converter<ActionLogVo, ActionLog> {

        @Override
        protected ActionLog doForward(ActionLogVo actionLogVo) {
            ActionLog actionLog = new ActionLog();
            BeanUtils.copyProperties(actionLogVo, actionLog);
            return actionLog;
        }

        @Override
        protected ActionLogVo doBackward(ActionLog actionLog) {
            return null;
        }
    }

}
