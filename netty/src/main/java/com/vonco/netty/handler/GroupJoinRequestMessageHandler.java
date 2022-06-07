package com.vonco.netty.handler;

import com.vonco.netty.message.GroupJoinRequestMessage;
import com.vonco.netty.message.GroupJoinResponseMessage;
import com.vonco.netty.server.session.Group;
import com.vonco.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author ke feng
 * @title: GroupJoinRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 10:19
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupJoinRequestMessage groupJoinRequestMessage) throws Exception {
        String username = groupJoinRequestMessage.getUsername();
        String groupName = groupJoinRequestMessage.getGroupName();
        Group group = GroupSessionFactory.getGroupSession().joinMember(groupName, username);
        if (group != null) {
            channelHandlerContext.writeAndFlush(new GroupJoinResponseMessage(true,"成功加入"+groupName));
            List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupJoinResponseMessage(true,username+"加入了"+groupName));
            }
        } else {
            channelHandlerContext.writeAndFlush(new GroupJoinResponseMessage(false,"组名不存在"));
        }
    }
}
