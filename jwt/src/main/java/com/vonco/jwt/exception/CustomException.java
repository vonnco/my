package com.vonco.jwt.exception;

import com.vonco.jwt.model.response.ResultCode;

public class CustomException extends RuntimeException{
    private ResultCode resultCode;
    public CustomException(ResultCode resultCode){
        //异常信息为错误代码+异常信息
        super("错误代码："+resultCode.code()+"错误信息："+resultCode.msg());
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }
}
