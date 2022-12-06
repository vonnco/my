package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ke feng
 * @title: Test03
 * @projectName my
 * @description: TODO
 * @date 2022/8/11 11:58
 */
@Slf4j(topic = "c.Test03")
public class Test03 {
    public static void main(String[] args) {
        log.debug("开始运行");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("开始运行");
                try {
                    Thread.sleep(2000);
                    log.debug("结束运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.setDaemon(true);
        t1.start();
        log.debug("结束运行");
    }
}
