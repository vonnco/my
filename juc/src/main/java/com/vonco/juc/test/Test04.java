package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ke feng
 * @title: Test04
 * @projectName my
 * @description: TODO
 * @date 2022/8/12 9:18
 */
@Slf4j(topic = "c.Test04")
public class Test04 {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    room.add();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    room.sub();
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(room.getNum());
    }
}

class Room{
    private int num = 0;

    public void add(){
        synchronized (this) {
            num++;
        }
    }

    public void sub(){
        synchronized (this) {
            num--;
        }
    }

    public int getNum(){
        synchronized (this) {
            return num;
        }
    }
}
