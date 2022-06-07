package com.vonco.netty.handler;

import com.vonco.netty.message.RpcRequestMessage;
import com.vonco.netty.message.RpcResponseMessage;
import com.vonco.netty.server.service.HelloService;
import com.vonco.netty.server.service.ServicesFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * @author ke feng
 * @title: RpcRequestHandler
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 17:36
 */
@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequestMessage rpcRequestMessage) throws Exception {
        RpcResponseMessage message = new RpcResponseMessage();
        message.setSequenceId(rpcRequestMessage.getSequenceId());
        message.setMessageType(rpcRequestMessage.getMessageType());
        try {
            HelloService service = (HelloService) ServicesFactory.getService(Class.forName(rpcRequestMessage.getInterfaceName()));
            Method method = service.getClass().getMethod(rpcRequestMessage.getMethodName(), rpcRequestMessage.getParameterTypes());
            Object invoke = method.invoke(service, rpcRequestMessage.getParameterValue());
            message.setReturnValue(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            message.setExceptionValue(e);
        }
        channelHandlerContext.writeAndFlush(message);
    }
}
