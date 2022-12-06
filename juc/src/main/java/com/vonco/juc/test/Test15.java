package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test15
 * @projectName my
 * @description: TODO
 * @date 2022/9/22 11:19
 */
public class Test15 {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(new Runnable() {
            @Override
            public void run() {
                bigRoom.study();
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                bigRoom.sleep();
            }
        },"t2").start();
    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom{
    private final Object A = new Object();
    private final Object B = new Object();
    public void sleep(){
        synchronized (A) {
            log.debug("睡觉2小时");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void study(){
        synchronized (B) {
            log.debug("学习1小时");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
