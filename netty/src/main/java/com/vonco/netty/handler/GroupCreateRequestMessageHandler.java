package com.vonco.netty.handler;

import com.vonco.netty.message.GroupChatResponseMessage;
import com.vonco.netty.message.GroupCreateRequestMessage;
import com.vonco.netty.message.GroupCreateResponseMessage;
import com.vonco.netty.server.session.Group;
import com.vonco.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

/**
 * @author ke feng
 * @title: GroupCreateRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 10:10
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupCreateRequestMessage groupCreateRequestMessage) throws Exception {
        String groupName = groupCreateRequestMessage.getGroupName();
        Set<String> members = groupCreateRequestMessage.getMembers();
        Group group = GroupSessionFactory.getGroupSession().createGroup(groupName, members);
        if (group == null) {
            channelHandlerContext.writeAndFlush(new GroupCreateResponseMessage(true,groupName+"创建成功"));
            List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupChatResponseMessage(true,"您已被拉入"+groupName));
            }
        } else {
            channelHandlerContext.writeAndFlush(new GroupChatResponseMessage(false,"组已存在"));
        }
    }
}
