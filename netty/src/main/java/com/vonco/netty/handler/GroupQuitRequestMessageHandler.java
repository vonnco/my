package com.vonco.netty.handler;

import com.vonco.netty.message.GroupQuitRequestMessage;
import com.vonco.netty.message.GroupQuitResponseMessage;
import com.vonco.netty.server.session.Group;
import com.vonco.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author ke feng
 * @title: GroupQuitRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 10:28
 */
@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupQuitRequestMessage groupQuitRequestMessage) throws Exception {
        String username = groupQuitRequestMessage.getUsername();
        String groupName = groupQuitRequestMessage.getGroupName();
        Group group = GroupSessionFactory.getGroupSession().removeMember(groupName, username);
        if (group != null) {
            channelHandlerContext.writeAndFlush(new GroupQuitResponseMessage(true,"已退出"+groupName));
            List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupQuitResponseMessage(false,username+"已退出"+groupName));
            }
        } else {
            channelHandlerContext.writeAndFlush(new GroupQuitResponseMessage(false,"组名不存在"));
        }
    }
}
