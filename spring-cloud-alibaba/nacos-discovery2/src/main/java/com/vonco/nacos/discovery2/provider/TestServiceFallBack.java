package com.vonco.nacos.discovery2.provider;

import org.springframework.stereotype.Component;

/**
 * @author ke feng
 * @title: TestServiceFallBack
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 13:56
 */
@Component
public class TestServiceFallBack implements TestService{
    @Override
    public String test01(String s) {
        return "test fallback";
    }
}
