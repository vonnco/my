package com.vonco.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j(topic = "c.Test")
class JucApplicationTests {

    @Test
    void test1() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t1.start();
        log.debug("running");
    }

    @Test
    void test2() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        Thread t2 = new Thread(r, "t2");
        t2.start();
        log.debug("running");
    }

    @Test
    void test3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                log.debug("running");
                return 100;
            }
        });
        Thread t3 = new Thread(task, "t3");
        t3.start();
        log.debug("{}",task.get());
    }

    @Test
    void test4() throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        }, "t1");
        //t1.run();
        t1.start();
    }

    @Test
    void test5() throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(2000);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        //打断线程
        t1.interrupt();
    }

    @Test
    void test6() throws ExecutionException, InterruptedException {
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (;;){
                    System.out.println("t1-----"+count++);
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (;;){
                    //Thread.yield();
                    System.out.println("             t2------"+count++);
                }
            }
        };
        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

    @Test
    void test7() {

    }
}
