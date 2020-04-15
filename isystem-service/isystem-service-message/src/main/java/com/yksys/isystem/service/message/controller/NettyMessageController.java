package com.yksys.isystem.service.message.controller;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.constants.MsgTypeEnum;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.model.message.SystemNoticeContent;
import com.yksys.isystem.service.message.netty.service.ActionService;
import com.yksys.isystem.service.message.netty.service.factory.ActionStrategyFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: YK-iSystem
 * @description: 实时消息发送
 * @author: YuKai Fan
 * @create: 2020-04-15 17:08
 **/
@RestController
@RequestMapping("/api/message")
public class NettyMessageController {

    @PostMapping("/")
    public Result sendSystemNotice() {
        ActionService actionService = ActionStrategyFactory.getActionByType(MsgTypeEnum.SYSTEM_NOTICE.getMsgType());

        //获取系统所有用户信息

        SystemNoticeContent systemNoticeContent = new SystemNoticeContent();
        systemNoticeContent.setReceiverId("1");
        systemNoticeContent.setMsgType(MsgTypeEnum.SYSTEM_NOTICE.getMsgType());
        systemNoticeContent.setAction(MsgActionConstant.SEND);
        systemNoticeContent.setContent("用户1收到");
        actionService.doSend(systemNoticeContent);

        SystemNoticeContent systemNoticeContent2 = new SystemNoticeContent();
        systemNoticeContent2.setReceiverId("2");
        systemNoticeContent2.setAction(MsgActionConstant.SEND);
        systemNoticeContent2.setMsgType(MsgTypeEnum.SYSTEM_NOTICE.getMsgType());
        systemNoticeContent2.setContent("用户2收到");
        actionService.doSend(systemNoticeContent2);

        return new Result(HttpStatus.OK.value(), "发送成功");
    }
}