package com.vonco.camunda.service;

import com.vonco.camunda.model.HistoricProcessInstanceRequest;
import com.vonco.camunda.model.HistoryRequest;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;

import java.util.List;

/**
 * @author ke feng
 * @title: WorkflowHistoryService
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 14:09
 */
public interface WorkflowHistoryService {
    /**
     * 查询历史流程实例
     * @return
     */
    List<HistoricProcessInstance> getHistoricProcessInstance(HistoricProcessInstanceRequest historicProcessInstanceRequest);

    /**
     * 查询流程历史行为
     * @param historyRequest
     * @return
     */
    List<HistoricActivityInstance> getHistoricActivityInstance(HistoryRequest historyRequest);
}
