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
        new Thread(() -> {
            log.debug("设置值");
            try {
                TimeUnit.SECONDS.sleep(1);
                commonObject.complete(null);
                TimeUnit.SECONDS.sleep(1);
                commonObject.complete(Arrays.asList("1","2","3"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(() -> {
            log.debug("获取值");
            Object object = commonObject.getObject(2500);
            if (object == null) {
                log.debug("未获取到值");
            } else {
                log.debug("获取到值：{}",object);
            }
        },"t2").start();
    }
}

class CommonObject{

    private Object object;

    public Object getObject(long time) {
        synchronized (this) {
            long beginTime = System.currentTimeMillis();
            long passTime = 0;
            while (object == null) {
                long waitTime = time - passTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passTime = System.currentTimeMillis() - beginTime;
            }
            return object;
        }
    }

    public void complete(Object object){
        synchronized (this) {
            this.object = object;
            this.notifyAll();
        }
    }
}
