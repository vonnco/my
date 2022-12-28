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
        TicketWindow ticketWindow = new TicketWindow(10000);
        List<Thread> threads = new ArrayList<>();
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                int count = ticketWindow.sellTicket(randomAmount());
                sellCount.add(count);
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("剩余票数："+ticketWindow.getCount());
        System.out.println("卖票数："+sellCount.stream().mapToInt(c->c).sum());
    }

    static Random random = new Random();

    public static int randomAmount(){
        return random.nextInt(5)+1;
    }
}
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized int sellTicket(int amount){
        if (count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
