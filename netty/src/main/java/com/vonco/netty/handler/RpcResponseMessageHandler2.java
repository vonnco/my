package com.vonco.netty.handler;

import com.vonco.netty.message.RpcResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ke feng
 * @title: RpcResponseMessageHandler2
 * @projectName my
 * @description: TODO
 * @date 2022/6/7 13:46
 */
@ChannelHandler.Sharable
public class RpcResponseMessageHandler2 extends SimpleChannelInboundHandler<RpcResponseMessage> {
    public static final Map<Integer, Promise<Object>> PROMISES = new ConcurrentHashMap<>();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponseMessage rpcResponseMessage) throws Exception {
        Promise<Object> promise = PROMISES.remove(rpcResponseMessage.getSequenceId());
        if (promise != null) {
            Object returnValue = rpcResponseMessage.getReturnValue();
            Exception exceptionValue = rpcResponseMessage.getExceptionValue();
            if (exceptionValue == null) {
                promise.setSuccess(returnValue);
            } else {
                promise.setFailure(exceptionValue);
            }
        }
    }
}
