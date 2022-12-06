package com.vonco.juc.test;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test23
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 11:13
 */
public class Test23 {
    static volatile boolean isTrue = true;
    public static void main(String[] args) {
        new Thread(()->{
            while (isTrue) {

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isTrue = false;
    }
}
