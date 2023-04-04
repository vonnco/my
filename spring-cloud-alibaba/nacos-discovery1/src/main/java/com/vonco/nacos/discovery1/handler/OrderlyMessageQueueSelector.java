package com.vonco.nacos.discovery1.handler;

import com.vonco.nacos.discovery1.NacosDiscovery1Application;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderlyMessageQueueSelector implements MessageQueueSelector {

   @Override
   public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
      Integer id = (Integer) ((MessageHeaders) arg).get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
      String tag = (String) ((MessageHeaders) arg).get(MessageConst.PROPERTY_TAGS);
      int index = id % NacosDiscovery1Application.tags.length % mqs.size();
      return mqs.get(index);
   }
}