package com.vonco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author ke feng
 * @title: Test13
 * @projectName my
 * @description: TODO
 * @date 2022/8/31 9:20
 */
public class Test13 {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            Message message = new Message(i, "内容：" + i);
            new Thread(() -> {
                messageQueue.put(message);
            },"生产者"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            messageQueue.take();
        },"消费者").start();
    }
}

@Slf4j(topic = "c.MessageQueue")
class MessageQueue {
    private LinkedList<Message> messages = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity){
        this.capacity = capacity;
    }

    public Message take(){
        synchronized (messages) {
            while (messages.isEmpty()) {
                try {
                    log.debug("消息队列为空，等待");
                    messages.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("消费者消费消息");
            Message message = messages.removeFirst();
            messages.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (messages) {
            while (messages.size() == capacity) {
                try {
                    log.debug("消息队列已满，等待");
                    messages.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("生产者生产消息");
            messages.addLast(message);
            messages.notifyAll();
        }
    }
}

final class Message {
    private int id;
    private Object object;

    public Message(int id,Object object) {
        this.id = id;
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }
}