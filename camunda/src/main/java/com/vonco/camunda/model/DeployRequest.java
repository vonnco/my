package com.vonco.camunda.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ke feng
 * @title: DeployRequest
 * @projectName my
 * @description: TODO
 * @date 2022/3/21 16:20
 */
@Data
public class DeployRequest {
    //流程部署名称
    private String name;
    //流程部署文件路径
    private String path;
    //流程部署文件
    private MultipartFile file;
}
