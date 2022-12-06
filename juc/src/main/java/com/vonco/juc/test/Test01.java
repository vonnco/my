package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test01
 * @projectName my
 * @description: TODO
 * @date 2022/8/11 10:53
 */
@Slf4j(topic = "c.Test01")
public class Test01 {
        public static void main(String[] args) throws InterruptedException {
            s1();
        }

    public static void s1() {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("准备茶具");
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("洗茶具");
                    TimeUnit.SECONDS.sleep(2);
                    log.debug("放茶叶");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "小王");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("洗水壶");
                    TimeUnit.SECONDS.sleep(2);
                    log.debug("烧水");
                    TimeUnit.SECONDS.sleep(5);
                    t2.join();
                    log.debug("泡茶");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "老王");
        t1.start();
        t2.start();
    }
}
