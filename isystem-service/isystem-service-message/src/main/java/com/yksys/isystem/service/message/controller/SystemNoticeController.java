package com.yksys.isystem.service.message.controller;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.constants.MsgTypeEnum;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.model.message.SystemNoticeContent;
import com.yksys.isystem.service.message.netty.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: yk-isystem
 * @description: 系统通知controller
 * @author: YuKai Fan
 * @create: 2020-04-15 20:46
 **/
@RestController
@RequestMapping("/api/systemNotice")
public class SystemNoticeController {
    @Autowired
    @Qualifier("systemNoticeActionImpl")
    private ActionService systemNoticeActionService;

    @PostMapping("/realTimeMessage")
    public Result realTimeMessage(@RequestBody Map<String, Object> params) {
        //获取缓存中的所有userId todo
        SystemNoticeContent systemNoticeContent = new SystemNoticeContent();
        systemNoticeContent.setAction(MsgActionConstant.SEND);
        systemNoticeContent.setContent(params);
        systemNoticeContent.setReceiverId(null);
        systemNoticeContent.setMsgType(MsgTypeEnum.SYSTEM_NOTICE.getMsgType());

        systemNoticeActionService.doSend(systemNoticeContent);
        return new Result(HttpStatus.OK.value(), "发送成功");
    }
}