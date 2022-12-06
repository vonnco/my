package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ke feng
 * @title: Test02
 * @projectName my
 * @description: TODO
 * @date 2022/8/11 11:45
 */
@Slf4j
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Test02.class) {
                    while (true) {

                    }
                }
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Test02.class) {
                    log.debug("running");
                }
            }
        },"t5");
        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        },"t6");
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        System.out.println(t1.getState());// NEW
        System.out.println(t2.getState());// RUNNABLE
        System.out.println(t3.getState());// TIMED_WAITING
        System.out.println(t4.getState());// WAITING
        System.out.println(t5.getState());// BLOCKED
        System.out.println(t6.getState());// TERMINATED
    }
}
