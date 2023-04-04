package com.vonco.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ke feng
 * @title: UserService
 * @projectName my
 * @description: TODO
 * @date 2023/2/20 10:54
 */
public class UserServiceImpl{

    public void add() {
        System.out.println("add user......");
    }

    public void update() {
        System.out.println("update user......");
    }
}

class MyCglibProxy implements MethodInterceptor {

    private Object target;

    public MyCglibProxy(Object target){
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置操作......");
        Object object = null;
        try {
            if (method.getName().equals("add")) {
                object = methodProxy.invoke(target,objects);
            } else {
                object = methodProxy.invoke(target,objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("后置操作......");
        }
        return object;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
}