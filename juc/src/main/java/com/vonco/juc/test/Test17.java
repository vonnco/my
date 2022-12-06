package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ke feng
 * @title: Test17
 * @projectName my
 * @description: TODO
 * @date 2022/9/22 13:44
 */
public class Test17 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Person("张三", c1, c2).start();
        new Person("李四", c2, c3).start();
        new Person("王五", c3, c4).start();
        new Person("赵六", c4, c5).start();
        new Person("钱七", c5, c1).start();
    }
}

class Chopstick extends ReentrantLock {
    private String name;

    public Chopstick(String name) {
        this.name = name;
    }
}

@Slf4j(topic = "c.Person")
class Person extends Thread{

    private Chopstick left;
    private Chopstick right;

    public Person(String name,Chopstick left,Chopstick right){
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
            /*synchronized (left) {
                synchronized (right) {
                    eat();
                }
            }*/
        }
    }

    public void eat(){
        log.debug("eatting");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
