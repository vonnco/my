package com.vonco.camunda.model;

import lombok.Data;

/**
 * @author ke feng
 * @title: TaskAttachment
 * @projectName my
 * @description: TODO
 * @date 2022/3/24 14:13
 */
@Data
public class TaskAttachment {
    //附件类型
    private String attachmentType;
    //任务id
    private String taskId;
    //流程实例id
    private String processInstanceId;
    //附件名称
    private String attachmentName;
    //附件描述
    private String attachmentDescription;
    //附件路径
    private String url;
}
