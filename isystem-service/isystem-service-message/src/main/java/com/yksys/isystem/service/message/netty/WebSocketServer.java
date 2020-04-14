package com.yksys.isystem.service.message.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: yk-isystem
 * @description: WebSocket服务
 * @author: YuKai Fan
 * @create: 2020-04-14 20:47
 **/
@Slf4j
@Component
public class WebSocketServer {
    //服务端口号
    private static final String SERVER_PORT = "9090";

    /**
     * 定义单例
     */
    private static class SingletonWsServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    /**
     * 获取单例
     * @return
     */
    public static WebSocketServer getInstance() {
        return SingletonWsServer.instance;
    }

    //主服务
    private EventLoopGroup mainGroup;
    //子服务
    private EventLoopGroup subGroup;
    //服务启动器
    private ServerBootstrap serverBootstrap;
    //通道
    private ChannelFuture channelFuture;

    public WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketInitializer());
    }

    /**
     * 启动服务
     */
    public void start() {
        log.info("Netty WebSocket Server 开始启动!!");
        try {
            this.channelFuture = serverBootstrap.bind(Integer.valueOf(SERVER_PORT));
            log.info("Netty WebSocket Server 启动成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Netty WebSocket Server 启动失败: {}", e.getMessage());
        }
    }

    /**
     * 关闭服务
     */
    public void shutdown() {
        if (channelFuture == null || !channelFuture.channel().isOpen()) {
            return;
        }

        try {
            channelFuture.channel().close();

            Future<?> subGroupFuture = this.subGroup.shutdownGracefully();
            Future<?> mainGroupFuture = this.mainGroup.shutdownGracefully();
            mainGroupFuture.await();
            subGroupFuture.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("Netty WebSocket Server 关闭异常: {}", e.getMessage());
        }

        log.info("Netty WebSocket Server 已关闭");
    }
}