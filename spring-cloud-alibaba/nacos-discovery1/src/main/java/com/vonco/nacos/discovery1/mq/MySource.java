package com.vonco.nacos.discovery1.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author ke feng
 * @title: MySource
 * @projectName my
 * @description: TODO
 * @date 2022/10/28 13:58
 */
public interface MySource {

    String OUTPUT1 = "output1";

    @Output(MySource.OUTPUT1)
    MessageChannel output1();

    String PRODUCER_OUT_0 = "producer-out-0";

    @Output(MySource.PRODUCER_OUT_0)
    MessageChannel producerOut0();
}
