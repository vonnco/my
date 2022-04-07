package com.vonco.camunda.model;

import lombok.Data;

/**
 * @author ke feng
 * @title: HistoryRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 15:36
 */
@Data
public class HistoryRequest {
    //流程实例id
    private String processInstanceId;
    //流程定义id
    private String processDefinitionId;
}
