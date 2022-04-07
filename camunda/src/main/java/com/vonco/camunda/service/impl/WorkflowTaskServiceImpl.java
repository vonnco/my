package com.vonco.camunda.service.impl;

import com.vonco.camunda.model.Condition;
import com.vonco.camunda.model.TaskAttachment;
import com.vonco.camunda.model.TaskComment;
import com.vonco.camunda.model.TaskRequest;
import com.vonco.camunda.service.WorkflowTaskService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Attachment;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ke feng
 * @title: TaskServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 10:35
 */
@Service
public class WorkflowTaskServiceImpl implements WorkflowTaskService {
    @Autowired
    private TaskService taskService;

    @Override
    public List<Task> getTask(TaskRequest taskRequest) {
        TaskQuery query = taskService.createTaskQuery().active();
        if (taskRequest.getProcessDefinitionKeys() != null) {
            query.processDefinitionKeyIn(taskRequest.getProcessDefinitionKeys().toArray(new String[taskRequest.getProcessDefinitionKeys().size()]));
        }
        if (taskRequest.getProcessInstanceId() != null) {
            query.processInstanceId(taskRequest.getProcessInstanceId());
        }
        if (taskRequest.getTaskId() != null) {
            query.taskId(taskRequest.getTaskId());
        }
        if (taskRequest.getBusinessKey() != null) {
            query.processInstanceBusinessKey(taskRequest.getBusinessKey());
        }
        if (taskRequest.getTaskAssignee() != null) {
            query.taskAssignee(taskRequest.getTaskAssignee());
        }
        if (taskRequest.getTaskCandidateGroups() != null) {
            query.taskCandidateGroupIn(taskRequest.getTaskCandidateGroups());
        }
        if (taskRequest.getConditions() != null) {
            query = query.or();
            for (Condition condition : taskRequest.getConditions()) {
                switch (condition.getConditionType()) {
                    case "eq":
                        if (condition.getVariableType() == 0) {
                            query.taskVariableValueEquals(condition.getVariableName(),condition.getVariableName());
                        } else {
                            query.processVariableValueEquals(condition.getVariableName(),condition.getVariableName());
                        }
                    case "like":
                        if (condition.getVariableType() == 0) {
                            query.taskVariableValueLike(condition.getVariableName(),condition.getVariableName());
                        } else {
                            query.processVariableValueLike(condition.getVariableName(),condition.getVariableName());
                        }
                    case "ge":
                        if (condition.getVariableType() == 0) {
                            query.taskVariableValueGreaterThanOrEquals(condition.getVariableName(),condition.getVariableName());
                        } else {
                            query.processVariableValueGreaterThanOrEquals(condition.getVariableName(),condition.getVariableName());
                        }
                    case "le":
                        if (condition.getVariableType() == 0) {
                            query.taskVariableValueLessThanOrEquals(condition.getVariableName(),condition.getVariableName());
                        } else {
                            query.processVariableValueLessThanOrEquals(condition.getVariableName(),condition.getVariableName());
                        }
                    case "in":
                        if (condition.getVariableType() == 0) {
                            query.taskVariableValueEquals(condition.getVariableName(),condition.getVariableName());
                        } else {
                            query.processVariableValueEquals(condition.getVariableName(),condition.getVariableName());
                        }
                    default:
                        break;
                }
            }
            query = query.endOr();
        }
        List<Task> tasks = query.list();
        return tasks;
    }

    /**
     * 拾取任务
     * @param taskRequest
     * @return
     */
    @Override
    public Boolean claim(TaskRequest taskRequest) {
        try {
            taskService.claim(taskRequest.getTaskId(),taskRequest.getTaskAssignee());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 完成任务
     * @param taskRequest
     * @return
     */
    @Override
    public Boolean complete(TaskRequest taskRequest) {
        try {
            if (taskRequest.getTaskVariables() != null) {
                taskService.setVariablesLocal(taskRequest.getTaskId(),taskRequest.getTaskVariables());
            }
            taskService.complete(taskRequest.getTaskId(),taskRequest.getVariables());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加附件
     * @param taskAttachment
     * @return
     */
    @Override
    public Attachment addAttachment(TaskAttachment taskAttachment) {
        Attachment attachment = taskService.createAttachment(taskAttachment.getAttachmentType(), taskAttachment.getTaskId(), taskAttachment.getProcessInstanceId(), taskAttachment.getAttachmentName(), taskAttachment.getAttachmentDescription(), taskAttachment.getUrl());
        return attachment;
    }

    /**
     * 添加批注
     * @param taskComment
     * @return
     */
    @Override
    public Comment addComment(TaskComment taskComment) {
        Comment comment = taskService.createComment(taskComment.getTaskId(),taskComment.getProcessInstanceId(),taskComment.getMessage());
        return comment;
    }

    /**
     * 查询附件
     * @param taskId
     * @return
     */
    @Override
    public List<Attachment> getAttachment(String taskId) {
        return taskService.getTaskAttachments(taskId);
    }

    /**
     * 查询批注
     * @param taskId
     * @return
     */
    @Override
    public List<Comment> getComment(String taskId) {
        return taskService.getTaskComments(taskId);
    }
}
