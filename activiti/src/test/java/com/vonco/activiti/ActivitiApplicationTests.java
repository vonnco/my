package com.vonco.activiti;

import com.vonco.activiti.service.ActivitiService;
import com.vonco.activiti.util.SecurityUtil;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.model.Task;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;

@SpringBootTest
class ActivitiApplicationTests {
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private SecurityUtil securityUtil;
    @Test
    void contextLoads() throws IOException {
        securityUtil.logInAs("lishi");
    }

}
