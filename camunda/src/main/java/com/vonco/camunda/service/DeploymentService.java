package com.vonco.camunda.service;

import com.vonco.camunda.model.DeployRequest;
import com.vonco.camunda.model.DeploymentRequest;
import org.camunda.bpm.engine.repository.Deployment;

import java.util.List;

/**
 * @author ke feng
 * @title: CamundaService
 * @projectName my
 * @description: TODO
 * @date 2022/3/18 17:12
 */
public interface DeploymentService {
    /**
     * 部署流程
     * @param deployRequest
     * @return
     */
    Deployment deployProcess(DeployRequest deployRequest);

    /**
     * 查询流程部署信息
     * @param deploymentRequest
     * @return
     */
    List<Deployment> getDeployment(DeploymentRequest deploymentRequest);

    /**
     * 下载流程部署文件
     * @param deploymentId
     * @return
     */
    String downloadProcessResource(String deploymentId);

    /**
     * 删除部署流程
     * @param deploymentId
     * @param cascade false：如果存在实例未完成不能删除；true：如果存在实例未完成也删除
     */
    Boolean deleteDeployment(String deploymentId, Boolean cascade);
}
