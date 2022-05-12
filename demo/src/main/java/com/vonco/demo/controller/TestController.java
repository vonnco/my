package com.vonco.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ke feng
 * @title: TestController
 * @projectName my
 * @description: TODO
 * @date 2022/5/12 15:07
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello world";
    }
}
