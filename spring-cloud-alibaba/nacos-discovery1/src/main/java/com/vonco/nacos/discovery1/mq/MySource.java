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

    String OUTPUT2 = "output2";

    @Output(MySource.OUTPUT2)
    MessageChannel output2();
}
