package com.vonco.netty.server.service;

/**
 * @author ke feng
 * @title: HelloServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 17:53
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        System.out.println(name+"sayHello");
        return "成功";
    }
}
