package com.vonco.camunda.model;

import lombok.Data;

import java.util.Date;

/**
 * @author ke feng
 * @title: DeploymentRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/21 16:04
 */
@Data
public class DeploymentRequest {
    //流程部署id
    private String id;
    //流程部署名称
    private String name;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
}
