package com.vonco.netty.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ke feng
 * @title: ServicesFactory
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 17:28
 */
public class ServicesFactory{
    static Properties properties;
    static Map<Class<?>,Object> map = new ConcurrentHashMap<>();

    static {
        try {
            InputStream in = ServicesFactory.class.getResourceAsStream("/application.properties");
            properties = new Properties();
            properties.load(in);
            Set<String> names = properties.stringPropertyNames();
            for (String name : names) {
                if (name.endsWith("Service")) {
                    Class<?> interfaceClass = Class.forName(name);
                    Class<?> instanceClass = Class.forName(properties.getProperty(name));
                    map.put(interfaceClass,instanceClass.newInstance());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getService(Class<T> interfaceClass){
        return (T) map.get(interfaceClass);
    }
}
