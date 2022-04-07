package com.vonco.camunda.service;

import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * @author ke feng
 * @title: ProcessDefinitionService
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:29
 */
public interface ProcessDefinitionService {
    /**
     * 根据流程部署id查询流程定义信息
     * @param deploymentId
     * @return
     */
    ProcessDefinition getProcessDefinitionByDeploymentId(String deploymentId);

    /**
     * 根据流程定义id查询流程定义信息
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition getProcessDefinitionById(String processDefinitionId);

    /**
     * 删除流程定义
     * @param processDefinitionId
     * @return
     */
    Boolean deleteProcessDefinition(String processDefinitionId, Boolean cascade);

    /**
     * 挂起流程定义
     * @param processDefinitionId
     * @return
     */
    Boolean suspendProcessDefinition(String processDefinitionId);

    /**
     * 激活流程定义
     * @param processDefinitionId
     * @return
     */
    Boolean activateProcessDefinition(String processDefinitionId);
}
