package com.vonco.camunda.service;

import com.vonco.camunda.model.ProcessInstanceRequest;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.util.List;

/**
 * @author ke feng
 * @title: ProcessInstanceService
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:27
 */
public interface ProcessInstanceService {
    /**
     * 启动流程实例
     * @param processInstanceRequest
     * @return
     */
    ProcessInstance startProcessInstance(ProcessInstanceRequest processInstanceRequest);

    /**
     * 查询流程实例
     * @param processInstanceRequest
     * @return
     */
    List<ProcessInstance> getProcessInstance(ProcessInstanceRequest processInstanceRequest);

    /**
     * 挂起流程实例
     * @param processInstanceId
     * @return
     */
    Boolean suspendProcessInstance(String processInstanceId);

    /**
     * 激活流程实例
     * @param processInstanceId
     * @return
     */
    Boolean activateProcessInstance(String processInstanceId);

    /**
     * 删除流程实例
     * @param processInstanceRequest
     * @return
     */
    Boolean deleteProcessInstance(ProcessInstanceRequest processInstanceRequest);
}
