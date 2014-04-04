/**
 * JOJO
 * 
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.test.demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
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

    private static String deploymentId = null;

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
        deploymentId = repositoryService.createDeployment().addClasspathResource("processfile/demo.bpmn20.xml").deploy()
                .getId();
        logger.info("deploymentId [{}]", deploymentId);

    }

    @Test
    @Deployment
    public void simpleProcessTest()
    {
        preWork();
        runtimeService.startProcessInstanceByKey("helloProcess");
        Task task = taskService.createTaskQuery().singleResult();
        Assert.assertEquals("Demo Task", task.getName());

        taskService.complete(task.getId());
        Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());

    }
}
