package com.vonco.netty.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author ke feng
 * @title: PingMessage
 * @projectName my
 * @description: TODO
 * @date 2022/5/26 11:59
 */
@Data
@ToString(callSuper = true)
public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
