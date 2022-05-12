package com.vonco.netty.protocol;

import com.vonco.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author ke feng
 * @title: MessageCode
 * @projectName my
 * @description: TODO
 * @date 2022/4/15 15:12
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageCode extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        //魔数
        byteBuf.writeInt(6);
        //版本号
        byteBuf.writeByte(1);
        //序列化算法
        byteBuf.writeByte(0);
        //指令类型
        byteBuf.writeByte(message.getMessageType());
        //请求序号
        byteBuf.writeInt(message.getSequenceId());
        //填充无意义
        byteBuf.writeByte(0xff);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes = bos.toByteArray();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int magicNum = byteBuf.readInt();
        byte version = byteBuf.readByte();
        byte serializerType = byteBuf.readByte();
        byte messageType = byteBuf.readByte();
        int sequenceId = byteBuf.readInt();
        byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes,0,length);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        list.add(message);
    }
}
