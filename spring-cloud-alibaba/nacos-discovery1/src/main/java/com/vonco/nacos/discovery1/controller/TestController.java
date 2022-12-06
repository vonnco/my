package com.vonco.nacos.discovery1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.vonco.nacos.discovery1.mq.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ke feng
 * @title: TestController
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 11:47
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SenderService senderService;

    @SentinelResource(value = "test1",blockHandler = "exceptionHandler")
    @GetMapping("/test01/{s}")
    public String test01(@PathVariable("s") String s){
        return s;
    }

    //返回类型要与方法返回类型相同
    public String exceptionHandler(String s, BlockException e) {
        String msg = null;
        if (e instanceof FlowException) {
            msg = "限流异常";
        } else if (e instanceof DegradeException) {
            msg = "降级异常";
        } else if (e instanceof ParamFlowException) {
            msg = "热点参数限流的异常";
        } else if (e instanceof SystemBlockException) {
            msg = "系统规则异常";
        } else if (e instanceof AuthorityException) {
            msg = "授权规则异常";
        }
        return msg;
    }

    @GetMapping("/sendMessage/{s}")
    public String sendMessage(@PathVariable("s") String s){
        senderService.sendMessage(s);
        return s;
    }
}
