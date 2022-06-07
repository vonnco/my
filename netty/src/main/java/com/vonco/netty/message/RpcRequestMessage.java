package com.vonco.netty.message;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.ToString;

/**
 * @author ke feng
 * @title: RpcRequestMessage
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 15:58
 */
@Data
@ToString(callSuper = true)
public class RpcRequestMessage extends Message{
    /**
     * 调用的接口全限定名，服务端根据它找到实现
     */
    @Expose
    private String interfaceName;
    /**
     * 调用接口中的方法名
     */
    @Expose
    private String methodName;
    /**
     * 方法返回类型
     */
    @Expose
    private Class<?> returnType;
    /**
     * 方法参数类型数组
     */
    @Expose
    private Class[] parameterTypes;
    /**
     * 方法参数值数组
     */
    @Expose
    private Object[] parameterValue;

    public RpcRequestMessage(String interfaceName, String methodName, Class<?> returnType, Class[] parameterTypes, Object[] parameterValue) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameterValue = parameterValue;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_REQUEST;
    }
}
