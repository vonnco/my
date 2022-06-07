package com.vonco.netty.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author ke feng
 * @title: RpcResponseMessage
 * @projectName my
 * @description: TODO
 * @date 2022/6/6 16:07
 */
@Data
@ToString(callSuper = true)
public class RpcResponseMessage extends Message{
    /**
     * 返回值
     */
    private Object returnValue;
    /**
     * 异常值
     */
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
