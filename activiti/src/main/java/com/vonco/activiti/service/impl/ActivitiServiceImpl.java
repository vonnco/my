package com.vonco.activiti.service.impl;

import com.vonco.activiti.model.Holiday;
import com.vonco.activiti.service.ActivitiService;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ke feng
 * @title: ActivitiServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2021/12/1 13:57
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;

    /**
     * 部署流程
     * @param name 流程定义名称
     * @param file 流程定义文件
     * @return
     */
    @Override
    public Deployment deployProcess(String name, File file) {
        Deployment deploy = null;
        try {
            deploy = repositoryService.createDeployment()
                    .addInputStream(file.getName(), new FileInputStream(file))
                    .name(name)
                    .deploy();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return deploy;
    }

    /**
     * 下载流程资源
     * @param processDefinitionId 流程定义id
     */
    @Override
    public void downloadProcessResource(String processDefinitionId) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = repositoryService.getProcessModel(processDefinitionId);
            fileOutputStream = new FileOutputStream(new File("D://test.bpmn"));
            IOUtils.copy(inputStream,fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询流程定义
     * @param processDefinitionId 流程定义id
     * @return
     */
    @Override
    public ProcessDefinition findProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = processRuntime.processDefinition(processDefinitionId);
        return processDefinition;
    }

    /**
     * 分页查询流程定义列表
     * @param page 页码
     * @param size 条数
     * @return
     */
    @Override
    public List<ProcessDefinition> findProcessDefinitionList(Integer page,Integer size) {
        Pageable pageable = Pageable.of(page, size);
        Page<ProcessDefinition> processDefinitions = processRuntime.processDefinitions(pageable);
        //可用流程定义数量
        int totalItems = processDefinitions.getTotalItems();
        //流程定义列表
        List<ProcessDefinition> processDefinitionList = processDefinitions.getContent();
        return processDefinitionList;
    }

    /**
     * 删除流程定义
     * @param deploymentId 流程部署id
     */
    @Override
    public void deleteProcessDefinition(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId,true);
    }

    /**
     * 启动流程实例
     * @param processDefinitionId 流程定义id
     * @param businessKey 业务标识
     * @return
     */
    @Override
    public ProcessInstance startProcessInstance(String processDefinitionId,String businessKey) {
        //流程变量
        Map<String,Object> map = new HashMap<>();
        Holiday holiday = new Holiday();
        holiday.setNum(3);
        map.put("holiday",holiday);
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start()
                .withProcessDefinitionId(processDefinitionId)
                .withBusinessKey(businessKey)
                .withVariables(map)
                .build()
        );
        return processInstance;
    }

    /**
     * 查询流程实例
     * @param processInstanceId 流程实例id
     * @return
     */
    @Override
    public ProcessInstance findProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = processRuntime.processInstance(processInstanceId);
        return processInstance;
    }

    /**
     * 查询流程实例列表
     */
    @Override
    public List<ProcessInstance> findProcessInstanceList() {
        Pageable pageable = Pageable.of(0, 10);
        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(pageable);
        //可用流程实例数量
        int totalItems = processInstancePage.getTotalItems();
        //可用流程实例
        List<ProcessInstance> processInstanceList = processInstancePage.getContent();
        return processInstanceList;
    }

    /**
     * 挂起流程实例
     * @param processInstanceId 流程实例id
     * @return
     */
    @Override
    public ProcessInstance suspendProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = processRuntime.suspend(ProcessPayloadBuilder.suspend(processInstanceId));
        return processInstance;
    }

    /**
     * 激活流程实例
     * @param processInstanceId 流程实例id
     * @return
     */
    @Override
    public ProcessInstance resumeProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = processRuntime.resume(ProcessPayloadBuilder.resume(processInstanceId));
        return processInstance;
    }

    /**
     * 删除流程实例
     * @param processInstanceId 流程实例id
     * @return
     */
    @Override
    public ProcessInstance deleteProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = processRuntime.delete(ProcessPayloadBuilder.delete(processInstanceId));
        return processInstance;
    }

    /**
     * 查询任务
     * @param taskId 任务id
     * @return
     */
    @Override
    public Task findTask(String taskId) {
        Task task = taskRuntime.task(taskId);
        return task;
    }

    /**
     * 查询任务列表
     * @param page 页码
     * @param size 条数
     * @return
     */
    @Override
    public List<Task> findTaskList(Integer page, Integer size) {
        Pageable pageable = Pageable.of(page, size);
        Page<Task> tasks = taskRuntime.tasks(pageable);
        //可用任务数量
        int totalItems = tasks.getTotalItems();
        //任务列表
        List<Task> taskList = tasks.getContent();
        return taskList;
    }

    /**
     * 获取流程实例变量
     * @param taskId 任务id
     * @return
     */
    @Override
    public List<VariableInstance> getVariables(String taskId) {
        List<VariableInstance> variables = taskRuntime.variables(TaskPayloadBuilder.variables().withTaskId(taskId).build());
        return variables;
    }

    /**
     * 拾取任务
     * @param taskId 任务id
     */
    @Override
    public Task claim(String taskId) {
        Task task = findTask(taskId);
        if (task != null && task.getAssignee() == null) {
            task = taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
        }
        return task;
    }

    /**
     * 完成任务
     * @param taskId 任务id
     * @param content 审核内容
     * @return
     */
    @Override
    public Task completeTask(String taskId, String content) {
        Task task = taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).withVariable("content", content).build());
        return task;
    }

    /**
     * 归还任务
     * @param taskId 任务id
     */
    @Override
    public void returnTask(String taskId) {
        Task task = findTask(taskId);
        if (task != null) {
            taskRuntime.release(TaskPayloadBuilder.release().withTaskId(taskId).build());
        }
    }
}
