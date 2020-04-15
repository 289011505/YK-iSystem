package com.yksys.isystem.service.message.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.yksys.isystem.common.core.utils.WebUtil;
import com.yksys.isystem.service.message.netty.service.ActionService;
import com.yksys.isystem.service.message.netty.service.factory.ActionStrategyFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 21:14
 **/
@Slf4j
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
        String action = jsonObject.getString("action");
        ActionService actionService = ActionStrategyFactory.getActionByType(msgType);
        if (actionService == null) {
            log.error("获取操作服务实例为空, actionService: {}", actionService);
            return;
        }
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
        MessageHandler.saveChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        MessageHandler.removeChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后, 关闭连接并移除
        MessageHandler.removeChannel(ctx.channel());
    }
}