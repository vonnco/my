package com.vonco.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author ke feng
 * @title: Test07
 * @projectName my
 * @description: TODO
 * @date 2022/8/16 14:36
 */
public class Test07 {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(2000);
        List<Thread> threads = new ArrayList<>();
        List<Integer> count = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer sell = ticketWindow.sell(getAmount());
                    count.add(sell);
                }
            });
            threads.add(thread);
            thread.start();
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("剩余票数："+ticketWindow.getCount());
        System.out.println(count.size());
        System.out.println("卖票数："+count.stream().mapToInt(c->c).sum());
    }

    static Random random = new Random();

    public static Integer getAmount(){
        return random.nextInt(5)+1;
    }
}

class TicketWindow {

    private Integer count;

    public TicketWindow(Integer count){
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public synchronized Integer sell(Integer amount){
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
