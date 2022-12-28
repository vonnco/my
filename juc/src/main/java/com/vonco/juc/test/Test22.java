package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ke feng
 * @title: Test22
 * @projectName my
 * @description: TODO
 * @date 2022/9/27 9:47
 */
@Slf4j(topic = "c.Test22")
public class Test22 {
    public static void main(String[] args) {
        SyncWait syncWait = new SyncWait(1, 5);
        new Thread(()->{syncWait.print("c",3,1);},"t3").start();
        new Thread(()->{syncWait.print("b",2,3);},"t2").start();
        new Thread(()->{syncWait.print("a",1,2);},"t1").start();
        SyncAwait syncAwait = new SyncAwait(5);
        Condition condition1 = syncAwait.newCondition();
        Condition condition2 = syncAwait.newCondition();
        Condition condition3 = syncAwait.newCondition();
        new Thread(()->{syncAwait.print("c",condition3,condition1);},"t3").start();
        new Thread(()->{syncAwait.print("b",condition2,condition3);},"t2").start();
        new Thread(()->{syncAwait.print("a",condition1,condition2);},"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        syncAwait.start(condition1);
        SyncPark syncPark = new SyncPark(5);
        Thread t1 = new Thread(() -> {
            syncPark.print("a");
        }, "t1");
        Thread t2 = new Thread(() -> {
            syncPark.print("b");
        }, "t2");
        Thread t3 = new Thread(() -> {
            syncPark.print("c");
        }, "t3");
        t3.start();
        t2.start();
        t1.start();
        syncPark.setThreads(t1,t2,t3);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        syncPark.start(t1);
    }
}

@Slf4j(topic = "c.SyncWait")
class SyncWait{
    private int flag;
    private int loopNumber;

    public SyncWait(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str,int curr,int next) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (flag != curr) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug(str);
                flag = next;
                this.notifyAll();
            }
        }
    }
}

@Slf4j(topic = "c.SyncAwait")
class SyncAwait extends ReentrantLock {
    private int loopNumber;

    public SyncAwait(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void start(Condition condition){
        lock();
        try {
            condition.signal();
        } finally {
            unlock();
        }
    }

    public void print(String str, Condition curr,Condition next){
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                try {
                    curr.await();
                    log.debug(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                unlock();
            }
        }
    }
}

@Slf4j(topic = "c.SyncPark")
class SyncPark{
    private int loopNumber;
    private Thread[] threads;

    public SyncPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void setThreads(Thread...threads){
        this.threads = threads;
    }

    public void print(String str){
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            log.debug(str);
            LockSupport.unpark(nextThread());
        }
    }

    private Thread nextThread() {
        Thread curr = Thread.currentThread();
        int index = 0;
        for (int i = 0; i < threads.length; i++) {
            if (threads[i] == curr) {
                index = i;
                break;
            }
        }
        if (index < threads.length - 1) {
            return threads[index+1];
        } else {
            return threads[0];
        }
    }

    public void start(Thread thread){
        LockSupport.unpark(thread);
    }
}
