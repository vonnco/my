package com.vonco.jwt.exception;

import com.vonco.jwt.model.response.ResultCode;

public class ExceptionCast {
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
