package com.vonco.netty.handler;

import com.vonco.netty.message.LoginRequestMessage;
import com.vonco.netty.message.LoginResponseMessage;
import com.vonco.netty.server.service.UserServiceFactory;
import com.vonco.netty.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ke feng
 * @title: LoginRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 9:25
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestMessage loginRequestMessage) throws Exception {
        String username = loginRequestMessage.getUsername();
        String password = loginRequestMessage.getPassword();
        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage message;
        if (login) {
            message = new LoginResponseMessage(true,"登录成功");
            SessionFactory.getSession().bind(channelHandlerContext.channel(),username);
        } else {
            message = new LoginResponseMessage(false,"用户名或者密码错误");
        }
        channelHandlerContext.writeAndFlush(message);
    }
}
