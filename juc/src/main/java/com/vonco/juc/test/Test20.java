package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ke feng
 * @title: Test20
 * @projectName my
 * @description: TODO
 * @date 2022/9/26 10:21
 */
@Slf4j(topic = "c.Test20")
public class Test20 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition waterRoom = lock.newCondition();
    static Condition mikeRoom = lock.newCondition();
    static volatile boolean hasWater = false;
    static volatile boolean hasMike = false;
    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try {
                log.debug("等待水");
                while (!hasWater) {
                    try {
                        waterRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("等到了水");
            } finally {
                lock.unlock();
            }
        },"张三").start();
        new Thread(()->{
            lock.lock();
            try {
                log.debug("等待牛奶");
                while (!hasMike) {
                    try {
                        mikeRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("等到了牛奶");
            } finally {
                lock.unlock();
            }
        },"李四").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                log.debug("送来了水");
                hasWater = true;
                waterRoom.signal();
            } finally {
              lock.unlock();
            }
        },"王五").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                log.debug("送来了牛奶");
                hasMike = true;
                mikeRoom.signal();
            } finally {
                lock.unlock();
            }
        },"赵六").start();
    }
}
