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
        Account a1 = new Account(10000);
        Account a2 = new Account(10000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a1.transfer(a2,randomMoney());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                a2.transfer(a1,randomMoney());
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a1："+a1.getMoney());
        System.out.println("a2："+a2.getMoney());
    }

    static Random random = new Random();

    public static int randomMoney(){
        return random.nextInt(10)+1;
    }
}
class Account{
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account account,int amount){
        synchronized (Account.class) {
            if (this.money >= amount) {
                this.money -= amount;
                account.setMoney(account.getMoney()+amount);
            }
        }
    }
}
