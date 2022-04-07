package com.vonco.camunda.service;

import com.vonco.camunda.model.TaskAttachment;
import com.vonco.camunda.model.TaskComment;
import com.vonco.camunda.model.TaskRequest;
import org.camunda.bpm.engine.task.Attachment;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;

import java.util.List;

/**
 * @author ke feng
 * @title: TaskService
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 10:34
 */
public interface WorkflowTaskService {
    /**
     * 查询任务
     * @param taskRequest
     * @return
     */
    List<Task> getTask(TaskRequest taskRequest);

    /**
     * 拾取任务
     * @param taskRequest
     * @return
     */
    Boolean claim(TaskRequest taskRequest);

    /**
     * 完成任务
     * @param taskRequest
     * @return
     */
    Boolean complete(TaskRequest taskRequest);

    /**
     * 添加附件
     * @param taskAttachment
     * @return
     */
    Attachment addAttachment(TaskAttachment taskAttachment);

    /**
     * 添加批注
     * @param taskComment
     * @return
     */
    Comment addComment(TaskComment taskComment);

    /**
     * 查询附件
     * @param taskId
     * @return
     */
    List<Attachment> getAttachment(String taskId);

    /**
     * 查询批注
     * @param taskId
     * @return
     */
    List<Comment> getComment(String taskId);
}
