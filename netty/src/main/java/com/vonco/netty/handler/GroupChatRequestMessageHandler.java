package com.vonco.netty.handler;

import com.vonco.netty.message.GroupChatRequestMessage;
import com.vonco.netty.message.GroupChatResponseMessage;
import com.vonco.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ke feng
 * @title: GroupChatRequestMessageHandler
 * @projectName my
 * @description: TODO
 * @date 2022/5/24 9:59
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupChatRequestMessage groupChatRequestMessage) throws Exception {
        String from = groupChatRequestMessage.getFrom();
        String groupName = groupChatRequestMessage.getGroupName();
        String content = groupChatRequestMessage.getContent();
        List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
        if (CollectionUtils.isEmpty(channels)) {
            channelHandlerContext.writeAndFlush(new GroupChatResponseMessage(false,"组名不存在"));
        } else {
            for (Channel channel : channels) {
                channel.writeAndFlush(new GroupChatResponseMessage(from,content));
            }
        }
    }
}
