package com.vonco.netty.handler;

import com.vonco.netty.message.GroupMembersRequestMessage;
import com.vonco.netty.message.GroupMembersResponseMessage;
import com.vonco.netty.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

/**
 * @author ke feng
 * @title: GroupMembersRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 10:25
 */
@ChannelHandler.Sharable
public class GroupMembersRequestMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMembersRequestMessage groupMembersRequestMessage) throws Exception {
        String groupName = groupMembersRequestMessage.getGroupName();
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(groupName);
        channelHandlerContext.writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
