package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test11
 * @projectName my
 * @description: TODO
 * @date 2022/8/25 9:48
 */
@Slf4j(topic = "c.Test11")
public class Test11 {
    public static void main(String[] args) {
        CommonObject commonObject = new CommonObject();
        new Thread(()->{
            log.debug("设置值");
            try {
                TimeUnit.SECONDS.sleep(1);
                commonObject.complete(null);
                TimeUnit.SECONDS.sleep(1);
                commonObject.complete(Arrays.asList("a","b","c"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        new Thread(()->{
            log.debug("获取值");
            Object list = commonObject.getObject(1500);
            if (list == null) {
                log.debug("没有获取到值");
            } else {
                log.debug("{}",list);
            }
        },"t1").start();
    }
}

class CommonObject{
    private Object object;

    public Object getObject(long time){
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while (object == null) {
                long waitTime = time - passedTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
        }
        return object;
    }

    public void complete(Object object){
        synchronized (this) {
            this.object = object;
            this.notifyAll();
        }
    }
}
