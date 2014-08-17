/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.test.demo;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <summary>
 * 流程测试类
 * </summary>
 *
 * @author jojo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext-service.xml",
        "file:src/main/resources/applicationContext-tx.xml", "classpath*:test-applicationContext-demo.xml" })
public class DemoBusinessProcessTest
{
    private static Logger logger = LoggerFactory.getLogger(DemoBusinessProcessTest.class);

//    private static String deploymentId = null;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    // @Autowired
    // @Rule
    // public ActivitiRule activitiSpringRule;

    @BeforeClass
    public static void preClass()
    {
        // RepositoryService repositoryService = (RepositoryService)
        // applicationContext.getBean("repositoryService");
    }

    @Before
    public void setUP()
    {

        // if (!StringUtils.isNotEmpty(deploymentId))
        // {
        // return;
        // }

    }

    public void preWork()
    {
//        deploymentId = repositoryService.createDeployment().addClasspathResource("processfile/demo.bpmn20.xml").deploy()
//                .getId();
//        logger.info("deploymentId [{}]", deploymentId);

    }

//    @Test
//    @Deployment
    public void simpleProcessTest()
    {
        preWork();
        runtimeService.startProcessInstanceByKey("helloProcess");
        Task task = taskService.createTaskQuery().singleResult();
        Assert.assertEquals("Demo Task", task.getName());

        taskService.complete(task.getId());
        Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());

    }

    /**
     *
     * <summary>
     * <p><b>一个完整的快速上手的流程例子</b></p>
     * </summary>
     *
     * @author jojo
     *
     */
    @Test
    public void quickStartProcessDemo()
    {
        //以下这些都可以通过配置spring的方式来完成
        // Create Activiti process engine
//        ProcessEngine processEngine = ProcessEngineConfiguration
//          .createStandaloneProcessEngineConfiguration()
//          .buildProcessEngine();

        // Get Activiti services
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        RuntimeService runtimeService = processEngine.getRuntimeService();

        // Deploy the process definition
//        repositoryService.createDeployment()
//          .addClasspathResource("FinancialReportProcess.bpmn20.xml")
//          .deploy();

        // Start a process instance
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");

        //迭代下面Task节点
        //认领任务
//        taskService.claim(task.getId(), "fozzie");
        //完成任务
//        taskService.complete(task.getId());
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        for (Task task : tasks) {
          System.out.println("Following task is available for accountancy group: " + task.getName());

          // claim it
          taskService.claim(task.getId(), "fozzie");
        }

        // Verify Fozzie can now retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
        for (Task task : tasks) {
          System.out.println("Task for fozzie: " + task.getName());

          // Complete the task
          taskService.complete(task.getId());
        }

        System.out.println("Number of tasks for fozzie: "
                + taskService.createTaskQuery().taskAssignee("fozzie").count());

        // Retrieve and claim the second task
        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
          System.out.println("Following task is available for accountancy group: " + task.getName());
          taskService.claim(task.getId(), "kermit");
        }

        // Completing the second task ends the process
        for (Task task : tasks) {
          taskService.complete(task.getId());
        }

        // verify that the process is actually finished
        //判断任务是否完成
        String procId = processInstance.getId();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
        historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
        logger.info("=====================================");
        logger.info("Process instance end time: " + historicProcessInstance.getEndTime());
        logger.info("=====================================");
    }
}
