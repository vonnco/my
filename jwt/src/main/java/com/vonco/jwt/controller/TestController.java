package com.vonco.jwt.controller;

import com.vonco.jwt.model.response.CommonCode;
import com.vonco.jwt.model.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author ke feng
 * @title: UserController
 * @projectName my
 * @description: TODO
 * @date 2023/3/28 13:36
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/test01",name = "test01")
    public ResponseResult test01() {
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
