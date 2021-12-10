package com.vonco.activiti.service;

import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.task.model.Task;
import org.activiti.engine.repository.Deployment;

import java.io.File;
import java.util.List;

/**
 * @author ke feng
 * @title: ActivitiService
 * @projectName my
 * @description: TODO
 * @date 2021/12/1 13:57
 */
public interface ActivitiService {
    //部署流程
    Deployment deployProcess(String name, File file);
    //下载流程资源
    void downloadProcessResource(String processDefinitionId);
    //查询流程定义
    ProcessDefinition findProcessDefinition(String processDefinitionId);
    //分页查询流程定义列表
    List<ProcessDefinition> findProcessDefinitionList(Integer page,Integer size);
    //删除流程定义
    void deleteProcessDefinition(String deploymentId);
    //启动流程实例
    ProcessInstance startProcessInstance(String processDefinitionId,String businessKey);
    //查询流程实例
    ProcessInstance findProcessInstance(String processInstanceId);
    //查询流程实例列表
    List<ProcessInstance> findProcessInstanceList();
    //挂起流程实例
    ProcessInstance suspendProcessInstance(String processInstanceId);
    //激活流程实例
    ProcessInstance resumeProcessInstance(String processInstanceId);
    //删除流程实例
    ProcessInstance deleteProcessInstance(String processInstanceId);
    //查询任务
    Task findTask(String taskId);
    //查询任务列表
    List<Task> findTaskList(Integer page, Integer size);
    //获取流程实例变量
    List<VariableInstance> getVariables(String taskId);
    //拾取任务
    Task claim(String taskId);
    //完成任务
    Task completeTask(String taskId, String content);
    //归还任务
    void returnTask(String taskId);
}
