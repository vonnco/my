package com.vonco.camunda.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
    * @title: AutoSync
    * @projectName my
    * @description: TODO
    * @author ke feng
    * @date 2022/3/23 17:17
    */
public class AutoSync implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("存储数据："+delegateExecution.getBusinessKey());
    }
}
