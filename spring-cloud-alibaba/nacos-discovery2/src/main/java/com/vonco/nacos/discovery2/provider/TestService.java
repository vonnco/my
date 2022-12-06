package com.vonco.nacos.discovery2.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ke feng
 * @title: NacosDiscovery1Provider
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 13:49
 */
@FeignClient(name = "nacos-discovery1")
public interface TestService {

    @GetMapping("/test/test01/{s}")
    String test01(@PathVariable("s") String s);
}
