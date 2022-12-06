package com.vonco.nacos.config.controller;

import com.vonco.nacos.config.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ke feng
 * @title: NacosController
 * @projectName my
 * @description: TODO
 * @date 2022/9/27 17:02
 */
@RestController
@RequestMapping(value = "/nacos")
public class NacosController {

    @Autowired
    private Config config;

    @GetMapping(value = "/config")
    public Config getConfig(){
        return config;
    }
}
