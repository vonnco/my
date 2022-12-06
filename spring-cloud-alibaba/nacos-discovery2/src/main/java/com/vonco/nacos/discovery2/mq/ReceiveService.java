package com.vonco.nacos.discovery2.mq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author ke feng
 * @title: ReceiveService
 * @projectName my
 * @description: TODO
 * @date 2022/10/27 16:21
 */
@Service
public class ReceiveService {

    @StreamListener(value = MySink.INPUT1)
    public void listener1(String message) {
        System.out.println("test-group1=" + message);
    }

    @StreamListener(value = MySink.INPUT2)
    public void listener2(String message) {
        System.out.println("test-group2=" + message);
    }

    @StreamListener(value = MySink.INPUT3)
    public void listener3(String message) {
        System.out.println("test-group3=" + message);
    }
}
