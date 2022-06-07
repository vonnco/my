package com.vonco.netty.config;

import com.vonco.netty.protocol.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ke feng
 * @title: Config
 * @projectName my
 * @description: TODO
 * @date 2022/5/26 14:28
 */
public abstract class Config {
    static Properties properties;

    static {
        try {
            InputStream in = Config.class.getResourceAsStream("/application.properties");
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getServerPort(){
        String value = properties.getProperty("server.port");
        if (value == null) {
            return 8080;
        } else {
            return Integer.parseInt(value);
        }
    }

    public static Serializer.SerializerAlgorithm getSerializerAlgorithm(){
        String value = properties.getProperty("serializer.algorithm");
        if (value == null) {
            return Serializer.SerializerAlgorithm.Java;
        } else {
            return Serializer.SerializerAlgorithm.valueOf(value);
        }
    }
}
