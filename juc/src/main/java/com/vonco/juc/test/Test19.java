package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ke feng
 * @title: Test19
 * @projectName my
 * @description: TODO
 * @date 2022/9/24 10:03
 */
@Slf4j(topic = "c.Test19")
public class Test19 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        /*Thread t1 = new Thread(() -> {
            try {
                log.debug("进行加锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("锁被打断");
                e.printStackTrace();
                return;
            }
            try {
                log.debug("获取到了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }*/
        Thread t1 = new Thread(() -> {
            log.debug("开始获取锁");
            try {
                if (!lock.tryLock(1,TimeUnit.SECONDS)) {
                    log.debug("获取锁失败");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.debug("获取锁成功");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void method1() {
        lock.lock();
        try {
            log.debug("进入方法1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    private static void method2() {
        lock.lock();
        try {
            log.debug("进入方法2");
        } finally {
            lock.unlock();
        }
    }
}
