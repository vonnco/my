package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test05
 * @projectName my
 * @description: TODO
 * @date 2022/8/16 10:22
 */
@Slf4j(topic = "c.Test05")
public class Test05 {

    public synchronized void a(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }

    private synchronized void c(){
        log.debug("3");
    }

    public static void main(String[] args) {
        Test05 test05 = new Test05();
        Test05 test051 = new Test05();
        new Thread(() -> test05.a()).start();
        new Thread(() -> test051.b()).start();
    }
}
