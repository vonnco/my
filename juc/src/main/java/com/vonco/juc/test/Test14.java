package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ke feng
 * @title: Test14
 * @projectName my
 * @description: TODO
 * @date 2022/9/21 14:42
 */
@Slf4j(topic = "c.Test14")
public class Test14 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            log.debug("开始了。。。。");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            log.debug("结束了");
        },"t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t1);
    }
}
