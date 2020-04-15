package com.yksys.isystem.common.model.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-15 11:22
 **/
@Data
public class SystemNoticeContent extends MessageContent implements Serializable {
    private static final long serialVersionUID = 3566707475114486318L;

    //接受者用户标识
    private String receiverId;
}