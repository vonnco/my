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

    String INPUT3 = "input3";

    @Input(MySink.INPUT3)
    SubscribableChannel input3();
}