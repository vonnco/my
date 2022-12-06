package com.vonco.juc.test;

import java.util.Random;

/**
 * @author ke feng
 * @title: Test08
 * @projectName my
 * @description: TODO
 * @date 2022/8/18 11:00
 */
public class Test08 {

    public static void main(String[] args) throws InterruptedException {
        Account a1 = new Account(1000);
        Account a2 = new Account(1000);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    a1.transfer(a2, getMoney());
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    a2.transfer(a1, getMoney());
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a1.getMoney());
        System.out.println(a2.getMoney());
    }

    static Random random = new Random();
    public static Integer getMoney(){
        return random.nextInt(100)+1;
    }

}

class Account {

    private Integer money;

    public Account(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void transfer(Account account, Integer amount){
        synchronized (Account.class) {
            if (this.getMoney() > amount)
            this.money = this.getMoney() - amount;
            account.setMoney(account.getMoney()+amount);
        }
    }
}
