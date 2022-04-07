package com.vonco.camunda.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author ke feng
 * @title: TaskRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 10:51
 */
@Data
public class TaskRequest {
    //流程定义key
    private List<String> processDefinitionKeys;
    //流程实例id
    private String processInstanceId;
    //业务key
    private String businessKey;
    //任务id
    private String taskId;
    //用户
    private String taskAssignee;
    //用户组
    private List<String> taskCandidateGroups;
    //流程变量
    private Map<String,Object> variables;
    //任务变量
    private Map<String,Object> taskVariables;
    //条件参数
    private List<Condition> conditions;
}
