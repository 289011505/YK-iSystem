package com.yksys.isystem.service.message.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @program: yk-isystem
 * @description: WebSocket服务初始化
 * @author: YuKai Fan
 * @create: 2020-04-14 21:02
 **/
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //webSocket基于Http协议, 所以要有Http相应的编解码器
        pipeline.addLast(new HttpServerCodec());
        //对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合, 集合成FullHttpRequest或FullHttpResponse
        //几乎在Netty中的编程, 都会用到此handle
        pipeline.addLast(new HttpObjectAggregator(65536));

        /**
         * webSocket服务处理的协议, 用于指定给客户连接的路由: /ws
         * 本handle会处理一些繁重的操作:
         * 握手动作: handshaking(close, ping, pong) ping + pong = 心跳
         * 对于webSocket来说, 数据已frames进行传输的, 不同的数据类型对应的frames不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler
        pipeline.addLast(null);

        //针对客户端，如果在1分钟内没有向服务端发送读写心跳(ALL), 则主动断开
        //如果是读空闲或写空闲, 则不作处理
        pipeline.addLast(new IdleStateHandler(0, 0, 60 * 5));
        //自定义状态检测 处理心跳检测
        pipeline.addLast(null);

    }
}