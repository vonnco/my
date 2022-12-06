package com.vonco.nacos.discovery1.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonco.nacos.discovery1.model.ResponseMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ke feng
 * @title: CustomUrlBlockHandler
 * @projectName my
 * @description: TODO
 * @date 2022/9/29 15:42
 */
@Component
public class CustomUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        ResponseMessage responseMessage = null;
        if (e instanceof FlowException) {
            responseMessage = ResponseMessage.builder().code("0001").msg("限流异常").build();
        } else if (e instanceof DegradeException) {
            responseMessage = ResponseMessage.builder().code("0002").msg("降级异常").build();
        } else if (e instanceof ParamFlowException) {
            responseMessage = ResponseMessage.builder().code("0003").msg("热点参数限流的异常").build();
        } else if (e instanceof SystemBlockException) {
            responseMessage = ResponseMessage.builder().code("0004").msg("系统规则异常").build();
        } else if (e instanceof AuthorityException) {
            responseMessage = ResponseMessage.builder().code("0005").msg("授权规则异常").build();
        }
        // http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        new ObjectMapper().writeValue(httpServletResponse.getWriter(),responseMessage);
    }
}
