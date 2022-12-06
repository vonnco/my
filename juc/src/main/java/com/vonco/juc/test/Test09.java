package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test09
 * @projectName my
 * @description: TODO
 * @date 2022/8/24 10:13
 */
@Slf4j(topic = "c.Test09")
public class Test09 {
    static final Object OBJECT = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (OBJECT) {
                log.debug("开始了。。。。");
                try {
                    Thread.sleep(2000);
                    log.debug("结束了。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        new Thread(()->{
            synchronized (OBJECT) {
                log.debug("开始了。。。。");
                try {
                    OBJECT.wait();
                    log.debug("结束了。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
        TimeUnit.SECONDS.sleep(4);
        synchronized (OBJECT) {
            OBJECT.notifyAll();
        }
    }
}
