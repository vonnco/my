package com.vonco.camunda.model;

import lombok.Data;

/**
 * @author ke feng
 * @title: TaskComment
 * @projectName my
 * @description: TODO
 * @date 2022/3/24 14:27
 */
@Data
public class TaskComment {
    //任务id
    private String taskId;
    //流程实例id
    private String processInstanceId;
    //内容
    private String message;
}
