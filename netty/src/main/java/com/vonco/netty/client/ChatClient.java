package com.vonco.netty.client;

import com.vonco.netty.message.*;
import com.vonco.netty.protocol.MessageCodecSharable;
import com.vonco.netty.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ke feng
 * @title: Client
 * @projectName my
 * @description: TODO
 * @date 2022/5/19 11:18
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        LoggingHandler LOG_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
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
                            ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
                            ch.pipeline().addLast(new ChannelDuplexHandler(){
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    if (event.state() == IdleState.WRITER_IDLE) {
                                        ctx.writeAndFlush(new PingMessage());
                                    }
                                }
                            });
                            ch.pipeline().addLast("client handler",new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    if (msg instanceof LoginResponseMessage) {
                                        LoginResponseMessage message = (LoginResponseMessage) msg;
                                        if (message.isSuccess()) {
                                            atomicBoolean.set(true);
                                        }
                                        countDownLatch.countDown();
                                    }
                                    log.debug("{}",msg);
                                }
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    new Thread(() -> {
                                        Scanner scanner = new Scanner(System.in);
                                        System.out.println("请输入登录名");
                                        String username = scanner.nextLine();
                                        System.out.println("请输入密码");
                                        String password = scanner.nextLine();
                                        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                                        ctx.writeAndFlush(loginRequestMessage);
                                        try {
                                            countDownLatch.await();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (!atomicBoolean.get()) {
                                            ctx.channel().close();
                                            return;
                                        }
                                        while (true) {
                                            String command = scanner.nextLine();
                                            String[] s = command.split(" ");
                                            switch (s[0]) {
                                                case "send":
                                                    ctx.writeAndFlush(new ChatRequestMessage(username,s[1],s[2]));
                                                    break;
                                                case "gsend":
                                                    ctx.writeAndFlush(new GroupChatRequestMessage(username,s[1],s[2]));
                                                    break;
                                                case "gcreate":
                                                    Set<String> members = new HashSet<>(Arrays.asList(s[2].split(",")));
                                                    members.add(username);
                                                    ctx.writeAndFlush(new GroupCreateRequestMessage(s[1],members));
                                                    break;
                                                case "gmembers":
                                                    ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
                                                    break;
                                                case "gjoin":
                                                    ctx.writeAndFlush(new GroupJoinRequestMessage(username,s[1]));
                                                    break;
                                                case "gquit":
                                                    ctx.writeAndFlush(new GroupQuitRequestMessage(username,s[1]));
                                                    break;
                                                case "quit":
                                                    ctx.channel().close();
                                                    return;
                                            }
                                        }
                                    },"system in").start();
                                }
                            });
                        }
                    })
                    .connect(new InetSocketAddress("localhost", 8080));
            Channel channel = channelFuture.sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
