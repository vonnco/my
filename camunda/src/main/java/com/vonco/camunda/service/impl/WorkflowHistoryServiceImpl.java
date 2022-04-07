package com.vonco.camunda.service.impl;

import com.vonco.camunda.model.HistoricProcessInstanceRequest;
import com.vonco.camunda.model.HistoryRequest;
import com.vonco.camunda.service.WorkflowHistoryService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricActivityInstanceQuery;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ke feng
 * @title: WorkflowHistoryServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2022/3/23 14:10
 */
@Service
public class WorkflowHistoryServiceImpl implements WorkflowHistoryService {
    @Autowired
    private HistoryService historyService;

    /**
     * 查询历史流程实例
     * @return
     */
    @Override
    public List<HistoricProcessInstance> getHistoricProcessInstance(HistoricProcessInstanceRequest historicProcessInstanceRequest) {
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        if (historicProcessInstanceRequest.getProcessDefinitionId() != null) {
            query.processDefinitionId(historicProcessInstanceRequest.getProcessDefinitionId());
        }
        if (historicProcessInstanceRequest.getProcessInstanceId() != null) {
            query.processInstanceId(historicProcessInstanceRequest.getProcessInstanceId());
        }
        if (historicProcessInstanceRequest.getBusinessKey() != null) {
            query.processInstanceBusinessKey(historicProcessInstanceRequest.getBusinessKey());
        }
        List<HistoricProcessInstance> historicProcessInstances = query.list();
        return historicProcessInstances;
    }

    /**
     * 查询流程历史行为
     * @param historyRequest
     * @return
     */
    @Override
    public List<HistoricActivityInstance> getHistoricActivityInstance(HistoryRequest historyRequest) {
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();
        if (historyRequest.getProcessInstanceId() != null) {
            query.processInstanceId(historyRequest.getProcessInstanceId());
        }
        if (historyRequest.getProcessDefinitionId() != null) {
            query.processDefinitionId(historyRequest.getProcessDefinitionId());
        }
        //query.activityType("userTask");
        query.canceled();
        query.orderByHistoricActivityInstanceEndTime().asc();
        List<HistoricActivityInstance> historicActivityInstances = query.list();
        return historicActivityInstances;
    }
}
