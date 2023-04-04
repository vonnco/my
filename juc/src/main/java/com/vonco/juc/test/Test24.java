package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test24
 * @projectName my
 * @description: TODO
 * @date 2022/12/29 10:13
 */
@Slf4j(topic = "c.Test24")
public class Test24 {
    public static void main(String[] args) {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        twoPhaseTermination.start();
    }
}
@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitorThread;
    private volatile boolean stop = false;
    private boolean isStart = false;

    public void start(){
        synchronized (this) {
            if (isStart) {
                return;
            }
            isStart = true;
        }
        monitorThread = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.debug("收尾操作。。。");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("进行监控。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monitorThread.start();
    }

    public void stop(){
        stop = true;
    }
}

