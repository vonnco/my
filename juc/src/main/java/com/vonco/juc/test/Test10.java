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
    static Object object = new Object();
    static boolean hasWater = false;
    static boolean hasMike = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object) {
                log.debug("有水没：{}",hasWater);
                while (!hasWater) {
                    try {
                        log.debug("没水，开始等待");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有水没：{}",hasWater);
                if (hasWater) {
                    log.debug("有水，开始工作");
                } else {
                    log.debug("没水，工作失败");
                }
            }
        },"张三").start();
        new Thread(() -> {
            synchronized (object) {
                log.debug("有牛奶没：{}",hasMike);
                while (!hasMike) {
                    try {
                        log.debug("没牛奶，开始等待");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有牛奶没：{}",hasMike);
                if (hasMike) {
                    log.debug("有牛奶，开始工作");
                } else {
                    log.debug("没牛奶，工作失败");
                }
            }
        },"李四").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (object) {
                log.debug("送来了牛奶");
                hasMike = true;
                object.notifyAll();
            }
        },"王五").start();
    }
}
