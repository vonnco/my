package com.vonco.nacos.discovery2.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String INPUT1 = "input1";

    @Input(MySink.INPUT1)
    SubscribableChannel input1();

    String INPUT2 = "input2";

    @Input(MySink.INPUT2)
    SubscribableChannel input2();

    String CONSUMER_IN_0 = "consumer-in-0";

    @Input(MySink.CONSUMER_IN_0)
    SubscribableChannel consumerIn0();
}