package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ke feng
 * @title: Test18
 * @projectName my
 * @description: TODO
 * @date 2022/9/22 14:03
 */
@Slf4j(topic = "c.Test18")
public class Test18 {
    private static volatile int count = 10;

    public static void main(String[] args) {
        new Thread(()->{
           while (count > 0) {
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               count ++;
               log.debug("count：{}",count);
           }
        },"t1").start();
        new Thread(()->{
            while (count < 20) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count --;
                log.debug("count：{}",count);
            }
        },"t2").start();
    }
}
