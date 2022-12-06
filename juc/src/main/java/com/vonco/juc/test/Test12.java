package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test12
 * @projectName my
 * @description: TODO
 * @date 2022/8/30 15:17
 */
public class Test12 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Integer id : MaxBox.getIds()) {
            new Postman(id,"内容："+id).start();
        }
    }

}

class GuardedObject{
    private int id;
    private Object response;

    public GuardedObject(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Object getResponse(long timeOut){
        synchronized (this) {
            long startTime = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null) {
                long waitTime = startTime - passedTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - startTime;
            }
            return response;
        }
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}

class MaxBox{
    private static Map<Integer,GuardedObject> map = new Hashtable<>();
    private static int i = 1;

    public static synchronized int generateId(){
        return i++;
    }

    public static GuardedObject getGuardedObject(int id) {
        return map.remove(id);
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject guardedObject = new GuardedObject(generateId());
        map.put(guardedObject.getId(),guardedObject);
        return guardedObject;
    }

    public static Set<Integer> getIds(){
        return map.keySet();
    }
}

@Slf4j(topic = "c.people")
class People extends Thread{
    @Override
    public void run() {
        GuardedObject guardedObject = MaxBox.createGuardedObject();
        log.debug("开始收信id：{}",guardedObject.getId());
        Object response = guardedObject.getResponse(2000);
        log.debug("收信id：{},内容：{}",guardedObject.getId(),response);
    }
}

@Slf4j(topic = "c.Postman")
class Postman extends Thread{
    private int id;
    private String mail;

    public Postman(int id,String mail) {
        this.id = id;
        this.mail = mail;
    }
    @Override
    public void run() {
        GuardedObject guardedObject = MaxBox.getGuardedObject(id);
        log.debug("送信id：{}，内容：{}",guardedObject.getId(),mail);
        guardedObject.setResponse(mail);
    }
}
