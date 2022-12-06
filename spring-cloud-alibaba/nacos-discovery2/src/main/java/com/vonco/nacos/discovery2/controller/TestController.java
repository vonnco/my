package com.vonco.nacos.discovery2.controller;

import com.vonco.nacos.discovery2.provider.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ke feng
 * @title: TestController
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 14:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test01")
    public String test01(){
        return testService.test01("测试")+2;
    }
}
