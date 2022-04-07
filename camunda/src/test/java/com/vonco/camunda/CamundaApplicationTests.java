package com.vonco.camunda;

import com.vonco.camunda.model.TaskRequest;
import com.vonco.camunda.service.DeploymentService;
import com.vonco.camunda.service.ProcessInstanceService;
import com.vonco.camunda.service.WorkflowHistoryService;
import com.vonco.camunda.service.WorkflowTaskService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamundaApplicationTests {
    @Autowired
    private DeploymentService deploymentService;
    @Autowired
    private ProcessInstanceService processInstanceService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private WorkflowTaskService workflowTaskService;
    @Autowired
    private WorkflowHistoryService workflowHistoryService;
    @Autowired
    private HistoryService historyService;

    @Test
    void contextLoads() {
        /*DeployRequest deployRequest = new DeployRequest();
        deployRequest.setName("holidayProcess");
        deployRequest.setPath("BPMN/booth.bpmn");
        deploymentService.deployProcess(deployRequest);*/
        //deploymentService.deleteDeployment("c9fae8f5-a9a3-11ec-b7e1-7c10c9d08684");
        //Boolean aBoolean = deploymentService.activateProcessDefinition("Process_00i1q73:1:9ad95db0-a9a4-11ec-8332-7c10c9d08684");
       /* ProcessInstanceRequest processInstanceRequest = new ProcessInstanceRequest();
        processInstanceRequest.setProcessDefinitionId("Process_1rkawqf:1:28b13693-ab18-11ec-a8e7-7c10c9d08684");
        processInstanceRequest.setBusinessKey("3");
        Map<String,Object> map = new HashMap<>();
        map.put("user1","王五");
        map.put("user2","部门经理");
        map.put("user3","人事");
        map.put("user4","总经理");
        map.put("days",5);
        processInstanceRequest.setVariables(map);
        ProcessInstance processInstance = processInstanceService.startProcessInstance(processInstanceRequest);*/
        //List<ProcessInstance> processInstance = processInstanceService.getProcessInstance(processInstanceRequest);
        //processInstanceRequest.setProcessInstanceId("6eca9886-a9ad-11ec-9765-7c10c9d08684");
        //processInstanceRequest.setDeleteReason("测试删除");
        //processInstanceService.deleteProcessInstance(processInstanceRequest);
        //runtimeService.setVariable("4db633da-ab12-11ec-977b-7c10c9d08684","days",2);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskId("dfb54846-b63e-11ec-9cd8-7c10c9d08684");
        /*Map<String,Object> map = new HashMap<>();
        map.put("agree",true);
        taskRequest.setVariables(map);*/
        //taskRequest.setTaskAssignee("部门经理");
        //List<Task> task = workflowTaskService.getTask(taskRequest);
        workflowTaskService.complete(taskRequest);
        //processInstanceService.suspendProcessInstance("8a8109c0-ab18-11ec-97c8-7c10c9d08684");
       /* HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setProcessInstanceId("6a704523-ab18-11ec-855a-7c10c9d08684");
        List<HistoricActivityInstance> historicActivityInstances = workflowHistoryService.getHistoricActivityInstance(historyRequest);*/
        /*List<HistoricActivityInstance> unfinishs = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("6a704523-ab18-11ec-855a-7c10c9d08684")
                .unfinished()
                .list();*/
        /*ProcessInstanceModificationBuilder modificationBuilder = runtimeService.createProcessInstanceModification("6a704523-ab18-11ec-855a-7c10c9d08684");
        modificationBuilder.cancelAllForActivity("Activity_1ju20s1:f0d25bb4-affc-11ec-931d-7c10c9d08684");
        ProcessInstanceModificationInstantiationBuilder builder = modificationBuilder.startBeforeActivity("Activity_1v1ui7k");
        builder.execute();*/
        /*TaskRequest taskRequest = new TaskRequest();
        taskRequest.setProcessInstanceId("6a704523-ab18-11ec-855a-7c10c9d08684");
        List<Task> task = workflowTaskService.getTask(taskRequest);*/
        // 退回至起点
        /*runtimeService.createProcessInstanceModification("6a704523-ab18-11ec-855a-7c10c9d08684")
                .cancelAllForActivity("Activity_1v1ui7k")
                .startBeforeActivity("Activity_11m8gru")
                .execute();*/
        //String s = deploymentService.downloadProcessResource("50");
    }

}
