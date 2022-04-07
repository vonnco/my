package com.vonco.camunda.model;

import lombok.Data;

/**
 * @author ke feng
 * @title: ProcessInstanceRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/22 14:43
 */
@Data
public class HistoricProcessInstanceRequest {
    //流程定义id
    private String processDefinitionId;
    //业务key
    private String businessKey;
    //流程定义id
    private String processInstanceId;
}
