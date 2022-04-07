package com.vonco.camunda.service.impl;

import com.vonco.camunda.model.ProcessInstanceRequest;
import com.vonco.camunda.service.ProcessInstanceService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ke feng
 * @title: ProcessInstanceServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:27
 */
@Service
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 启动流程实例
     * @param processInstanceRequest
     * @return
     */
    @Override
    public ProcessInstance startProcessInstance(ProcessInstanceRequest processInstanceRequest) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processInstanceRequest.getProcessDefinitionId(), processInstanceRequest.getBusinessKey(), processInstanceRequest.getVariables());
        return processInstance;
    }

    @Override
    public List<ProcessInstance> getProcessInstance(ProcessInstanceRequest processInstanceRequest) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        if (processInstanceRequest.getDeploymentId() != null) {
            query.deploymentId(processInstanceRequest.getDeploymentId());
        }
        if (processInstanceRequest.getProcessDefinitionId() != null) {
            query.processDefinitionId(processInstanceRequest.getProcessDefinitionId());
        }
        if (processInstanceRequest.getProcessInstanceId() != null) {
            query.processInstanceId(processInstanceRequest.getProcessInstanceId());
        }
        if (processInstanceRequest.getBusinessKey() != null) {
            query.processInstanceBusinessKey(processInstanceRequest.getBusinessKey());
        }
        List<ProcessInstance> processInstances = query.list();
        return processInstances;
    }

    /**
     * 挂起流程实例
     * @param processInstanceId
     * @return
     */
    @Override
    public Boolean suspendProcessInstance(String processInstanceId) {
        try {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 激活流程实例
     * @param processInstanceId
     * @return
     */
    @Override
    public Boolean activateProcessInstance(String processInstanceId) {
        try {
            runtimeService.activateProcessInstanceById(processInstanceId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除流程实例
     * @param processInstanceRequest
     * @return
     */
    @Override
    public Boolean deleteProcessInstance(ProcessInstanceRequest processInstanceRequest) {
        try {
            runtimeService.deleteProcessInstance(processInstanceRequest.getProcessInstanceId(),processInstanceRequest.getDeleteReason());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
