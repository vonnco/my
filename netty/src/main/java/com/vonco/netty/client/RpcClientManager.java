package com.vonco.netty.client;

import com.vonco.netty.handler.RpcResponseMessageHandler2;
import com.vonco.netty.message.RpcRequestMessage;
import com.vonco.netty.protocol.MessageCodecSharable;
import com.vonco.netty.protocol.ProcotolFrameDecoder;
import com.vonco.netty.server.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import org.springframework.cglib.proxy.Proxy;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ke feng
 * @title: RpcClientManager
 * @projectName my
 * @description: TODO
 * @date 2022/6/7 13:54
 */
public class RpcClientManager {
    public static void main(String[] args) {
        HelloService service = getProxyService(HelloService.class);
        System.out.println(service.sayHello("zhangsan"));
    }

    private static AtomicInteger atomicLong = new AtomicInteger(0);

    public static <T> T getProxyService(Class<T> serviceClass){
        ClassLoader classLoader = serviceClass.getClassLoader();
        Class<?>[] interfaces = new Class[]{serviceClass};
        Object o = Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            Integer sequenceId = atomicLong.incrementAndGet();
            RpcRequestMessage message = new RpcRequestMessage(serviceClass.getName(), method.getName(), method.getReturnType(), method.getParameterTypes(), args);
            message.setSequenceId(sequenceId);
            getChannel().writeAndFlush(message);
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler2.PROMISES.put(sequenceId,promise);
            promise.await();
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return (T) o;
    }

    private static Channel channel = null;
    private static final Object LOCK = new Object();

    private static Channel getChannel(){
        if (channel != null) {
            return channel;
        }
        synchronized (LOCK) {
            if (channel != null) {
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    private static void initChannel(){
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        LoggingHandler LOG_HANDLER = new LoggingHandler();
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler2 RPC_RESPONSE_HANDLER = new RpcResponseMessageHandler2();
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
            channel = channelFuture.sync().channel();
            channel.closeFuture().addListener(future -> {
                group.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
