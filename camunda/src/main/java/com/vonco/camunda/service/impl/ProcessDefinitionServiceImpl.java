package com.vonco.camunda.service.impl;

import com.vonco.camunda.service.ProcessDefinitionService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ke feng
 * @title: ProcessDefinitionServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:29
 */
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 根据流程部署id查询流程定义信息
     * @param deploymentId
     * @return
     */
    @Override
    public ProcessDefinition getProcessDefinitionByDeploymentId(String deploymentId) {
        return repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
    }

    /**
     * 根据流程定义id查询流程定义信息
     * @param processDefinitionId
     * @return
     */
    @Override
    public ProcessDefinition getProcessDefinitionById(String processDefinitionId) {
        //方法一
        //ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
        //方法二
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

    /**
     * 删除流程定义
     * @param processDefinitionId
     * @return
     */
    @Override
    public Boolean deleteProcessDefinition(String processDefinitionId, Boolean cascade) {
        try {
            repositoryService.deleteProcessDefinition(processDefinitionId,cascade);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 挂起流程定义
     * @param processDefinitionId
     * @return
     */
    @Override
    public Boolean suspendProcessDefinition(String processDefinitionId) {
        try {
            repositoryService.suspendProcessDefinitionById(processDefinitionId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 激活流程定义
     * @param processDefinitionId
     * @return
     */
    @Override
    public Boolean activateProcessDefinition(String processDefinitionId) {
        try {
            repositoryService.activateProcessDefinitionById(processDefinitionId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
