package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test16
 * @projectName my
 * @description: TODO
 * @date 2022/9/22 11:33
 */
@Slf4j(topic = "c.Test16")
public class Test16 {
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    log.debug("lock A");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        log.debug("lock B");
                    }
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    log.debug("lock B");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A) {
                        log.debug("lock A");
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
