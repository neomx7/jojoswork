/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessTaskForm;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;

/**
 * <summary>
 * 封装 activiti5 实现工作流操作
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class WorkFlowExecutorImpl implements WorkFlowExecutor
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private String deploymentId = null;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;


    public void initialize() throws Exception {
//        setDeploymentId(repositoryService.createDeployment().addClasspathResource("classpath*:/processfile/*.bpmn20.xml").deploy().getId());
    }


    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#queryList(int, java.lang.String)
     */
    @Override
    public List<?> queryList(int status, String operId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#startProcessInstanceByKey(java.lang.String, java.lang.String)
     */
    @Override
    public void startProcessInstanceByKey(String processKey, String operId)
    {
        logger.info("startProcessInstanceByKey [{}] and operId [{}]", new Object[]{processKey,operId} );
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#claimTask(java.lang.String, java.lang.String)
     */
    @Override
    public void claimTask(String taskId, String operId)
    {
        taskService.claim(taskId, operId);
    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#completeTask(java.lang.String, java.lang.String)
     */
    @Override
    public void completeTask(String taskId, String operId)
    {
        taskService.complete(taskId);
    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#pendProcessByKey(java.lang.String, java.lang.String)
     */
    @Override
    public void pendProcessByKey(String processKey, String operId)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#getTaskForm(java.lang.String)
     */
    @Override
    public ProcessTaskForm getTaskForm(String taskId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WorkFlowDefine> queryFlowDefines(DataRequest request)
    {
//      // Simple Service
//      TempConverter converter = ContextHolder.getBean("simpleGateway", TempConverter.class);
//      System.out.println(converter.fahrenheitToCelcius(68.0f));
//        System.out.println("come here ..........");
        List<WorkFlowDefine> list = null;

        /**
         * orderByDeploymentId :按照id_排序
         * asc：升序
         * listPage：分页查询 0:表示起始位置，4：表示查询长度 .listPage(0, 4)
         */
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc().list();
        list = new ArrayList<WorkFlowDefine>(definitions.size());
        for(ProcessDefinition definition:definitions){
//            System.out.println("id:"+definition.getId()+",name:"+definition.getName()+",time:"+definition.getDeploymentId());
            WorkFlowDefine workFlowDefine = new WorkFlowDefine();
            workFlowDefine.setCategory(definition.getCategory());
            workFlowDefine.setDeploymentId(definition.getDeploymentId());
            workFlowDefine.setDiagramResourceName(definition.getDiagramResourceName());
            workFlowDefine.setHasStartFormKey(definition.hasStartFormKey());
            workFlowDefine.setKey(definition.getKey());
            workFlowDefine.setResourceName(definition.getResourceName());
            workFlowDefine.setSuspended(definition.isSuspended());
            workFlowDefine.setTenantId(definition.getTenantId());
            workFlowDefine.setTheId(definition.getId());
            workFlowDefine.setTheName(definition.getName());
            workFlowDefine.setTheRemark(definition.getDescription());
            workFlowDefine.setVersion(definition.getVersion());
            list.add(workFlowDefine);
        }


        return list;
    }


}
