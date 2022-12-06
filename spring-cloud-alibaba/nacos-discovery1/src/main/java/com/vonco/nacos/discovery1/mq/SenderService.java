package com.vonco.nacos.discovery1.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author ke feng
 * @title: SenderService
 * @projectName my
 * @description: TODO
 * @date 2022/10/27 16:21
 */
@Service
public class SenderService {
    @Autowired
    private MySource source;

    /**
     * 发送消息的方法
     *
     * @param message
     */
    public void sendMessage(String message) {
        /*Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, "tagStr");
        Message msg = MessageBuilder.createMessage(message, new MessageHeaders(headers));
        boolean send1 = source.output1().send(msg);*/
        boolean send1 = source.output1().send(MessageBuilder.withPayload(message).build());
        System.out.println("output1 result : " + send1);
        boolean send2 = source.output2().send(MessageBuilder.withPayload(message).build());
        System.out.println("output2 result : " + send2);
    }
}
