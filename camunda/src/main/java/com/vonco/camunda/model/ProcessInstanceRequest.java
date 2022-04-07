package com.vonco.camunda.model;

import lombok.Data;

import java.util.Map;

/**
 * @author ke feng
 * @title: ProcessInstanceRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:43
 */
@Data
public class ProcessInstanceRequest {
    //流程定义id
    private String processDefinitionId;
    //业务key
    private String businessKey;
    //流程变量
    private Map<String,Object> variables;
    //流程部署id
    private String deploymentId;
    //流程定义id
    private String processInstanceId;
    //删除原因
    private String deleteReason;
}
