package com.vonco.netty.client;

import com.vonco.netty.handler.RpcResponseMessageHandler;
import com.vonco.netty.message.RpcRequestMessage;
import com.vonco.netty.protocol.MessageCodecSharable;
import com.vonco.netty.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author ke feng
 * @title: RpcClient
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 16:13
 */
@Slf4j
public class RpcClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        LoggingHandler LOG_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_RESPONSE_HANDLER= new RpcResponseMessageHandler();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(LOG_HANDLER);
                            ch.pipeline().addLast(MESSAGE_CODEC);
                            ch.pipeline().addLast(RPC_RESPONSE_HANDLER);
                        }
                    }).connect(new InetSocketAddress("localhost", 8080));
            Channel channel = channelFuture.sync().channel();
            channel.writeAndFlush(new RpcRequestMessage("com.vonco.netty.server.service.HelloService", "sayHello", String.class, new Class[]{String.class}, new Object[]{"张三"}))
                            .addListener(promise->{
                                if (!promise.isSuccess()) {
                                    Throwable cause = promise.cause();
                                    log.debug("error",cause);
                                }
                            });
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
