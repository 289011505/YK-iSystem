package com.yksys.isystem.service.message.netty.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.core.utils.WebUtil;
import com.yksys.isystem.common.model.message.ChatMessageContent;
import com.yksys.isystem.common.model.message.MessageContent;
import com.yksys.isystem.service.message.netty.action.ActionService;
import com.yksys.isystem.service.message.netty.action.ActionStrategyFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 21:14
 **/
public class Handler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        if (o instanceof TextWebSocketFrame) {
            handleTextMessage(ctx, (TextWebSocketFrame) o);

        } else if (o instanceof FullHttpRequest) {

            handleHttpMessage(ctx, (FullHttpRequest) o);
        } else if (o instanceof BinaryWebSocketFrame) {

            handleBinaryMessage(ctx, (WebSocketFrame) o);
        }
    }

    /**
     * 处理Http消息
     * @param ctx
     * @param msg
     */
    protected void handleHttpMessage(ChannelHandlerContext ctx, FullHttpRequest msg) {
        Channel channel = ctx.channel();
        if (msg instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) msg;
            Map<String, String> parameterMap = WebUtil.getParameterMap(request);
        }
    }

    /**
     * 处理文本消息
     * @param ctx
     * @param msg
     */
    protected void handleTextMessage(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String text = msg.text();
        Channel channel = ctx.channel();
        //获取消息内容
        JSONObject jsonObject = JSONObject.parseObject(text);
        String msgType = jsonObject.getString("msgType");
        ActionService actionService = ActionStrategyFactory.getActionByType(msgType);
        actionService.doHandle(actionService.getMessageContent(text), channel);
    }

    /**
     * 处理二进制消息
     * @param ctx
     * @param msg
     */
    protected void handleBinaryMessage(ChannelHandlerContext ctx, WebSocketFrame msg) {

    };

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}