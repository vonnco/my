package com.vonco.netty.protocol;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ke feng
 * @title: Serializer
 * @projectName my
 * @description: TODO
 * @date 2022/5/26 14:04
 */
public interface Serializer {
    //序列化
    <T> byte[] serialize(T object);
    //反序列化
    <T> T deserialize(Class<T> clazz,byte[] bytes);

    enum SerializerAlgorithm implements Serializer{
        Java {
            @Override
            public <T> byte[] serialize(T object) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    return bos.toByteArray();
                } catch (IOException e) {
                    throw new RuntimeException("序列化失败",e);
                }
            }

            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    return (T)ois.readObject();
                } catch (Exception e) {
                    throw new RuntimeException("反序列化失败",e);
                }
            }
        },
        Json {
            @Override
            public <T> byte[] serialize(T object) {
                String json = new Gson().toJson(object);
                return json.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                String s = new String(bytes,StandardCharsets.UTF_8);
                return new Gson().fromJson(s,clazz);
            }
        }
    }
}
