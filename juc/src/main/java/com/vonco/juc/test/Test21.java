package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ke feng
 * @title: Test21
 * @projectName my
 * @description: TODO
 * @date 2022/9/27 9:38
 */
@Slf4j(topic = "c.Test21")
public class Test21 {

    static boolean t2Run = false;
    static Object object = new Object();

    public static void main(String[] args) {
        /*Thread t1 = new Thread(() -> {
            synchronized (object) {
                while (!t2Run) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (object) {
                log.debug("2");
                t2Run = true;
                object.notifyAll();
            }
        }, "t2");
        t1.start();
        t2.start();*/

        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        Thread t2 = new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t2");
        t1.start();
        t2.start();
    }
}
