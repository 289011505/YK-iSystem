package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.TodoTask;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 待办事项表 vo类
 *
 * @author YuKai Fan
 * @create 2020-04-16 11:37:57
 */
@Data
@ApiModel(value = "待办事项表vo对象", description = "待办事项表")
public class TodoTaskVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //待办事项标识
    @ApiModelProperty(value = "待办事项标识", dataType = "String")
    private String id;
    //待办事项标题
    @ApiModelProperty(value = "待办事项标题", dataType = "String")
    private String title;
    //用户id
    @ApiModelProperty(value = "用户id", dataType = "String")
    private String userId;
    //备注
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
    //状态:0  已删除 1 未完成 2 已完成
    @ApiModelProperty(value = "状态:0  已删除 1 未完成 2 已完成", dataType = "Integer")
    private Integer status;

    public TodoTask convert() {
        TodoTaskVoConvert todoTaskVoConvert = new TodoTaskVoConvert();
        TodoTask todoTask = todoTaskVoConvert.convert(this);
        return todoTask;
    }

    private static class TodoTaskVoConvert extends Converter<TodoTaskVo, TodoTask> {

        @Override
        protected TodoTask doForward(TodoTaskVo todoTaskVo) {
            TodoTask todoTask = new TodoTask();
            BeanUtils.copyProperties(todoTaskVo, todoTask);
            return todoTask;
        }

        @Override
        protected TodoTaskVo doBackward(TodoTask todoTask) {
            return null;
        }
    }

}
