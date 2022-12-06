package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test10
 * @projectName my
 * @description: TODO
 * @date 2022/8/24 15:22
 */
@Slf4j(topic = "c.Test10")
public class Test10 {
    static Object OBJECT = new Object();
    static boolean hasWater = false;
    static boolean hasMike = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (OBJECT) {
                log.debug("有水没：{}",hasWater);
                while (!hasWater) {
                    try {
                        OBJECT.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有水没：{}",hasWater);
                if (hasWater) {
                    log.debug("有水啦");
                } else {
                    log.debug("没水呀");
                }
            }
        },"张三").start();
        new Thread(()->{
            synchronized (OBJECT) {
                log.debug("有牛奶没：{}",hasMike);
                while (!hasMike) {
                    try {
                        OBJECT.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有牛奶没：{}",hasMike);
                if (hasMike) {
                    log.debug("有牛奶啦");
                } else {
                    log.debug("没牛奶啦");
                }
            }
        },"王五").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            synchronized (OBJECT) {
                hasMike = true;
                log.debug("牛奶到了");
                OBJECT.notifyAll();
            }

        },"李四").start();
    }
}
