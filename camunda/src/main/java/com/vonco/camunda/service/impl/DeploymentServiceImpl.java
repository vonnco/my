package com.vonco.camunda.service.impl;

import com.vonco.camunda.model.DeployRequest;
import com.vonco.camunda.model.DeploymentRequest;
import com.vonco.camunda.service.DeploymentService;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.DeploymentQuery;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ke feng
 * @title: CamundaServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/3/18 17:12
 */
@Service
public class DeploymentServiceImpl implements DeploymentService {
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 部署流程
     * @param deployRequest
     * @return
     */
    @Override
    public Deployment deployProcess(DeployRequest deployRequest) {
        Deployment deployment = null;
        try {
            DeploymentBuilder builder = repositoryService.createDeployment();
            if (deployRequest.getPath() != null) {
                String path = deployRequest.getPath();
                builder.addClasspathResource(path);
            } else if (deployRequest.getFile() != null) {
                MultipartFile file = deployRequest.getFile();
                builder.addInputStream(file.getOriginalFilename(),file.getInputStream());
            }
            builder.name(deployRequest.getName());
            deployment = builder.deploy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deployment;
    }

    /**
     * 查询流程部署信息
     * @param deploymentRequest
     * @return
     */
    @Override
    public List<Deployment> getDeployment(DeploymentRequest deploymentRequest) {
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        if (deploymentRequest.getId() != null) {
            query.deploymentId(deploymentRequest.getId());
        }
        if (deploymentRequest.getName() != null) {
            query.deploymentName(deploymentRequest.getName());
        }
        if (deploymentRequest.getEndTime() != null) {
            query.deploymentBefore(deploymentRequest.getEndTime());
        }
        if (deploymentRequest.getStartTime() != null) {
            query.deploymentAfter(deploymentRequest.getStartTime());
        }
        List<Deployment> deployments = query.list();
        return deployments;
    }

    /**
     * 下载流程部署文件
     * @param deploymentId
     * @return
     */
    @Override
    public String downloadProcessResource(String deploymentId) {
        FileOutputStream fileOutputStream = null;
        String path = "D://test.bpmn";
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
            String resourceName = processDefinition.getResourceName();
            InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
            fileOutputStream = new FileOutputStream(path);
            IOUtils.copy(inputStream,fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return path;
        }
    }

    /**
     * 删除部署流程
     * @param deploymentId
     */
    @Override
    public Boolean deleteDeployment(String deploymentId, Boolean cascade) {
        try {
            repositoryService.deleteDeployment(deploymentId,cascade);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
