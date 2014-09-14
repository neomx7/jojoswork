/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.jojo.integration.workflow.WorkFlowExecutor;
import com.jojo.process.dal.postgre.AttachMgrMapper;
import com.jojo.process.dal.postgre.ProcessMgrMapper;
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.common.DateUtils;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessInstanceTask;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowDefineGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;

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

    // private String deploymentId = null;
    // 改成jmx配置形式
    private String serviceURL;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private AttachMgrMapper attachMgrMapper;

    @Autowired
    private ProcessMgrMapper processMgrMapper;

    public void initialize() throws Exception
    {
        // setDeploymentId(repositoryService.createDeployment().addClasspathResource("classpath*:/processfile/*.bpmn20.xml").deploy().getId());
        // setServiceURL(serviceURL);
    }

    @Override
    public void genProcessGraph()
    {
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().latestVersion().active()
                .list();
        for (ProcessDefinition processDefinition : definitions)
        {
            if (!processDefinition.getId().contains("lishengProcess1"))
            {
                continue;
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("attachId", processDefinition.getId().replaceAll(":", "_"));
            long count = attachMgrMapper.count(params);
            if (count <= 0)
            {
                try
                {
                    // 生成流程图片
                    generateProcessGraph(processDefinition.getId());
                }
                catch (IOException e)
                {
                    logger.error("genProcessGraph failed.I/O exception info : [{}]", e);
                }
                catch (Exception e)
                {
                    logger.error("genProcessGraph failed. exception info : [{}]", e);
                }
            }
        }
    }


    /**
     * 读取资源
     *
     * @return
     */
    public void loadResource(String processInstanceId,String proDefId) {
        try {
            InputStream resourceAsStream = null;
            if (StringUtils.isNotBlank(processInstanceId)) {
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
                ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
                        .processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
                String resourceName = "";
                resourceName = singleResult.getDiagramResourceName();
//                if (resourceType.equals("image")) {
//                    resourceName = singleResult.getDiagramResourceName();
//                } else if (resourceType.equals("xml")) {
//                    resourceName = singleResult.getResourceName();
//                }
                resourceAsStream = repositoryService.getResourceAsStream(singleResult.getDeploymentId(), resourceName);
            }
//            else {
//                resourceAsStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
//            }
            StringBuilder graphFilePath = new StringBuilder();
            graphFilePath.append(proDefId.replaceAll(":", "_"));
            byte[] b = null;
                b = FileCopyUtils.copyToByteArray(resourceAsStream);
                if (b == null || b.length == 0)
                {
                    return;
                }
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("attachContent", b);// 此处所用的参数类型为 byte[]
                params.put("attachId", graphFilePath.toString());
                attachMgrMapper.upload(params);
        } catch (Exception e) {
            logger.error("读取资源出错：[{}]", e);
        }
    }

    /**
     * 流程跟踪图
     * @return
     */
    @Override
    public Map<String, Object> traceProcess(String processInstanceId) {
        try {
            Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
            Object property = PropertyUtils.getProperty(execution, "activityId");
            String activityId = "";
            if (property != null) {
                activityId = property.toString();
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
            List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点
            ActivityImpl activity = null;
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (id.equals(activityId)) {//获得执行到那个节点
                    activity = activityImpl;
                    break;
                }
            }
            Map<String, Object> activityImageInfo = new HashMap<String, Object>();
            activityImageInfo.put("x", activity.getX());
            activityImageInfo.put("y", activity.getY());
            activityImageInfo.put("width", activity.getWidth());
            activityImageInfo.put("height", activity.getHeight());
            return activityImageInfo;
//            Struts2Utils.renderJson(activityImageInfo);
        } catch (Exception e) {
            logger.error("查看流程跟踪图出错：[{}]",e);
        }
        return null;
    }


    /**
     * 得到当前激活的task节点
     * @return
     */
    @Override
    public WorkFlowTaskDTO getProcessActivity(String processInstanceId) {
        WorkFlowTaskDTO workFlowTaskDTO = new WorkFlowTaskDTO();
        try {
            Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
            Object property = PropertyUtils.getProperty(execution, "activityId");
            String activityId = "";
            if (property != null) {
                activityId = property.toString();
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
            List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (id.equals(activityId)) {//获得执行到那个节点

                    workFlowTaskDTO.setTheId(id);
                    workFlowTaskDTO.setTheName(String.valueOf(activityImpl.getProperty("name")));
                    return workFlowTaskDTO;
                }
            }
//            Struts2Utils.renderJson(activityImageInfo);
        } catch (Exception e) {
            logger.error("查看流程跟踪图出错：[{}]",e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jojo.facade.workflow.WorkFlowExecutor#queryList(int,
     * java.lang.String)
     */
    @Override
    public List<WorkFlowTaskDTO> queryList(int status, String operId)
    {

        if (status == 1)
        {
            // 待办

        }
        else
        {

        }

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jojo.facade.workflow.WorkFlowExecutor#startProcessInstanceByKey(java
     * .lang.String, java.lang.String)
     */
    @Override
    public String startProcessInstanceByKey(String processKey, String operId, String businessKey,
            Map<String, Object> variables)
    {
        logger.info("startProcessInstanceByKey [{}] and operId [{}]", new Object[] { processKey, operId });
        ProcessInstance processInstance = null;
        try
        {
            // 调用该方法，自动把当前任务给oeprId用户
            identityService.setAuthenticatedUserId(operId);
            processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey,
                    variables);

        }
        finally
        {
            identityService.setAuthenticatedUserId(null);
        }
        // 将创建者分配给任务
        // Map<String, Object> variables = new HashMap<String, Object>();
        // variables.put("hrUserId", hrUserId);
        // taskService.complete(taskId, variables);
        if (processInstance != null)
        {
            return processInstance.getId();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jojo.facade.workflow.WorkFlowExecutor#claimTask(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void claimTask(String taskId, String operId)
    {
        // taskService.getVariables(taskId)
        taskService.claim(taskId, operId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jojo.facade.workflow.WorkFlowExecutor#completeTask(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void completeTask(ProcessInstanceTask processTask)
    {



     // 设置下个节点审批人
        Map<String, Object> variables = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(processTask.getNextAssignee()))
        {
            variables.put(JOJOConstants.WORKFLOW_PROCESSINST_NEXT_ASSIGNEE, processTask.getNextAssignee());
        }
//        runtimeService.setVariable(processTask.getExecutionId(),
//                JOJOConstants.NEXT_ASSIGNEE, processTask.getNextAssignee());

        taskService.complete(processTask.getTaskId(),variables);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jojo.facade.workflow.WorkFlowExecutor#pendProcessByKey(java.lang.
     * String, java.lang.String)
     */
    @Override
    public void pendProcessByKey(String processKey, String operId)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jojo.facade.workflow.WorkFlowExecutor#getTaskForm(java.lang.String)
     */
    @Override
    public ProcessInstanceTask getProcessInstanceTask(String taskId)
    {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstanceTask processInstanceTask = convert2WFTask(task,0);

        return processInstanceTask;
    }

    /**
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param task
     * @return
     */
    private ProcessInstanceTask convert2WFTask(Task task,int listIndex)
    {
        ProcessInstanceTask processInstanceTask = new ProcessInstanceTask();
        processInstanceTask.setTaskId(task.getId());
        processInstanceTask.setTaskName(task.getName());
        processInstanceTask.setAssignee(task.getAssignee());
        processInstanceTask.setExecutionId(task.getExecutionId());
        processInstanceTask.setOwner(task.getOwner());
        processInstanceTask.setParentTaskId(task.getParentTaskId());
//        processInstanceTask.setInitialAssignee(task.get);
        processInstanceTask.setTaskDefinitionKey(task.getTaskDefinitionKey());
        processInstanceTask.setProcessDefinitionId(task.getProcessDefinitionId());
        processInstanceTask.setProcessInstanceId(task.getProcessInstanceId());
        processInstanceTask.setPriority(task.getPriority());
        processInstanceTask.setNumber(listIndex);
//        processInstanceTask.set
        processInstanceTask.setCrtTime(DateUtils.parseDateTime2Ms(task.getCreateTime()));

        //任务到期时间
        processInstanceTask.setDueTimeStr(DateUtils.parseDateTime2Ms(task.getDueDate()));

        //待处理状态 TODO 此处状态还可细分为pending和 resolved，以后再说。
        processInstanceTask.setStatus(JOJOConstants.WORKFLOW_TASKMODE_DOING);

        Map<String, Object> localVariables = task.getTaskLocalVariables();
        processInstanceTask.setLocalVariables(localVariables);
        return processInstanceTask;
    }


    private ProcessInstanceTask convert2WFHistoricTask(HistoricTaskInstance historicTask,int listIndex )
    {
        ProcessInstanceTask processInstanceTask = new ProcessInstanceTask();
        processInstanceTask.setTaskId(historicTask.getId());
        processInstanceTask.setTaskName(historicTask.getName());
        processInstanceTask.setAssignee(historicTask.getAssignee());
        processInstanceTask.setExecutionId(historicTask.getExecutionId());
        processInstanceTask.setOwner(historicTask.getOwner());
        processInstanceTask.setParentTaskId(historicTask.getParentTaskId());
//        processInstanceTask.setInitialAssignee(task.get);
        processInstanceTask.setTaskDefinitionKey(historicTask.getTaskDefinitionKey());
        processInstanceTask.setProcessDefinitionId(historicTask.getProcessDefinitionId());
        processInstanceTask.setProcessInstanceId(historicTask.getProcessInstanceId());
        processInstanceTask.setPriority(historicTask.getPriority());

//        processInstanceTask.set
        processInstanceTask.setCrtTime(DateUtils.parseDateTime2Ms(historicTask.getStartTime()));
        processInstanceTask.setUpdTime(DateUtils.parseDateTime2Ms(historicTask.getEndTime()));
        //任务到期时间
        processInstanceTask.setDueTimeStr(DateUtils.parseDateTime2Ms(historicTask.getDueDate()));
        processInstanceTask.setNumber(listIndex);

            //已处理
        processInstanceTask.setStatus(JOJOConstants.WORKFLOW_TASKMODE_DONE);

        Map<String, Object> localVariables = historicTask.getTaskLocalVariables();
        processInstanceTask.setLocalVariables(localVariables);
        return processInstanceTask;
    }

    @Override
    public List<WorkFlowDefine> queryFlowDefines(DataRequest request)
    {
        // // Simple Service
        // TempConverter converter = ContextHolder.getBean("simpleGateway",
        // TempConverter.class);
        // System.out.println(converter.fahrenheitToCelcius(68.0f));
        // System.out.println("come here ..........");
        List<WorkFlowDefine> list = null;

        /**
         * orderByDeploymentId :按照id_排序
         * asc：升序
         * listPage：分页查询 0:表示起始位置，4：表示查询长度 .listPage(0, 4)
         */
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().latestVersion()
        // .active().orderByDeploymentId()
        // .desc()
                .list();
        list = new ArrayList<WorkFlowDefine>(definitions.size());
        for (ProcessDefinition definition : definitions)
        {
            // System.out.println("id:"+definition.getId()+",name:"+definition.getName()+",time:"+definition.getDeploymentId());
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

    /**
     * 首先从数据库中获取图片，如果没有，再根据流程生成。
     * TODO 增加事务控制
     */
    @Override
    public WorkFlowDefineGraph getProcessGraph(String proDefId)
    {
        WorkFlowDefineGraph workFlowDefineGraph = new WorkFlowDefineGraph();

        StringBuilder graphFilePath = new StringBuilder();
        graphFilePath.append(proDefId.replaceAll(":", "_"));
        workFlowDefineGraph.setGraphKey(graphFilePath.toString());

        return workFlowDefineGraph;
    }

    private void generateProcessGraph(String proDefId) throws IOException
    {
        if (StringUtils.isNotEmpty(proDefId))
        {
            // BpmnModel bpmnModel = repositoryService.getBpmnModel(proDefId);
            // InputStream imageStream =
            // ProcessDiagramGenerator.generatePngDiagram(bpmnModel);
            // ReadOnlyProcessDefinition define =
            // ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(proDefId);
            // ProcessDefinition processDefinition =
            // repositoryService.createProcessDefinitionQuery()
            // .processDefinitionId(proDefId).singleResult();
            // String resourceName = processDefinition.getResourceName();
            // InputStream imageStream =
            // repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
            // resourceName);

            // InputStream imageStream =
            // repositoryService.getProcessDiagram(proDefId);

            // InputStream imageStream =
            // ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", null);

            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(proDefId).singleResult();
            String diagramResourceName = processDefinition.getDiagramResourceName();
            if (diagramResourceName == null)
            {
                logger.warn("no valid diagramResource set for this process definition Id.[{}]", proDefId);
                return;
            }
            InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                    diagramResourceName);

            StringBuilder graphFilePath = new StringBuilder();
            graphFilePath.append(proDefId.replaceAll(":", "_"));
            byte[] b = null;
            try
            {
                b = FileCopyUtils.copyToByteArray(imageStream);
                if (b == null || b.length == 0)
                {
                    return;
                }
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("attachContent", b);// 此处所用的参数类型为 byte[]
                params.put("attachId", graphFilePath.toString());
                attachMgrMapper.upload(params);
            }
            catch (IOException e)
            {
                logger.error("Save ProcessGraph failed. exception info : [{}]", e);
            }
            finally
            {
                if (imageStream != null)
                {
                    imageStream.close();
                }
            }
        }
    }

    @Override
    public Point locationWorkFlowGraph(String proDefId)
    {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(proDefId);
        if (bpmnModel == null)
        {
            return null;
        }
        return getPoint(bpmnModel);
    }

    /**
     * 获取流程节点元素中，最大的x,y坐标信息
     *
     * @param bpmnModel
     *            BpmnModel对象
     * @return Point
     */
    public Point getPoint(BpmnModel bpmnModel)
    {
        Point point = null;
        if (bpmnModel != null && bpmnModel.getFlowLocationMap().size() > 0)
        {
            int maxX = 0;
            int maxY = 0;
            for (String key : bpmnModel.getLocationMap().keySet())
            {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(key);
                double elementX = graphicInfo.getX() + graphicInfo.getWidth();
                if (maxX < elementX)
                {
                    maxX = (int) elementX;
                }
                double elementY = graphicInfo.getY() + graphicInfo.getHeight();
                if (maxY < elementY)
                {
                    maxY = (int) elementY;
                }
            }
            point = new Point(maxX, maxY);
        }
        else
        {
            point = new Point(100, 100);
        }
        return point;
    }

    /**
     * 获取未签收的任务查询对象
     *
     * @param userId
     *            用户ID
     */
    @Transactional(readOnly = true)
    public List<ProcessInstanceTask> createUnsignedTaskQuery(String userId, String processDefKey)
    {
        TaskQuery taskCandidateUserQuery = taskService.createTaskQuery().processDefinitionKey(processDefKey)
                .taskCandidateUser(userId);
        if (taskCandidateUserQuery != null)
        {
            List<Task> tasks = taskCandidateUserQuery.list();
            return getProcessTaskList(tasks);
        }
        return null;
    }

    /**
     * 获取正在处理的任务查询对象
     *
     * @param userId
     *            用户ID
     */
    @Transactional(readOnly = true)
    public List<ProcessInstanceTask> createTodoTaskQuery(String userId, String processDefKey)
    {
        TaskQuery taskAssigneeQuery = taskService.createTaskQuery().processDefinitionKey(processDefKey)
                .taskAssignee(userId);
        if (taskAssigneeQuery != null)
        {
            List<Task> tasks = taskAssigneeQuery.list();
            return getProcessTaskList(tasks);
        }
        return null;
    }

    /**
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param tasks
     * @return
     */
    private List<ProcessInstanceTask> getProcessTaskList(List<Task> tasks)
    {
        List<ProcessInstanceTask> processTasks = null;
        if (tasks != null && !tasks.isEmpty())
        {
            processTasks = new ArrayList<ProcessInstanceTask>(tasks.size());
            for (Task task : tasks)
            {
                ProcessInstanceTask processTask = tranferTask(task);
                processTasks.add(processTask);
            }

        }
        return processTasks;
    }

    /**
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param task
     */
    private ProcessInstanceTask tranferTask(Task task)
    {
        ProcessInstanceTask processTask = new ProcessInstanceTask();
        processTask.setCategory(task.getCategory());
        processTask.setCreateTime(task.getCreateTime());
        processTask.setDescription(task.getDescription());
        processTask.setDueDate(task.getDueDate());
        processTask.setExecutionId(task.getExecutionId());
        processTask.setTheName(task.getName());
        processTask.setOwner(task.getOwner());
        processTask.setParentTaskId(task.getParentTaskId());
        processTask.setPriority(task.getPriority());
        processTask.setProcessInstanceId(task.getProcessDefinitionId());
        processTask.setProcessInstanceId(task.getProcessInstanceId());
        processTask.setTenantId(task.getTenantId());

        return processTask;
    }

    // /**
    // * 获取未经完成的流程实例查询对象
    // * @param userId 用户ID
    // */
    // @Transactional(readOnly = true)
    // public List<ProcessTask> createUnFinishedProcessInstanceQuery(String
    // userId, String processDefKey) {
    // ProcessInstanceQuery unfinishedQuery =
    // runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefKey)
    // .active();
    // if (unfinishedQuery != null)
    // {
    // List<ProcessInstance> tasks = unfinishedQuery.list();
    // return getProcessTaskList(tasks);
    // }
    // return null;
    // }

    // /**
    // * 获取已经完成的流程实例查询对象
    // * @param userId 用户ID
    // */
    // @Transactional(readOnly = true)
    // public List<ProcessTask> createFinishedProcessInstanceQuery(String
    // userId, String processDefKey) {
    // HistoricProcessInstanceQuery finishedQuery =
    // historyService.createHistoricProcessInstanceQuery()
    // .processDefinitionKey(processDefKey).finished();
    // return finishedQuery.;
    // }

    public String getServiceURL()
    {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL)
    {
        this.serviceURL = serviceURL;
    }

    @Override
    public PageResultBO<WorkFlowTaskDTO> queryWorkFlowTODOTask(WorkFlowQuery query)
    {
        PageResultBO<WorkFlowTaskDTO> resultBO = new PageResultBO<WorkFlowTaskDTO>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operId", query.getOperId());

        int limit = query.getPageLimit();
        Long count = processMgrMapper.getTODOCount(params);

        if (count == null || count ==0)
        {
            return resultBO;
        }
        resultBO.setTotalCount(count.intValue());
        int totalPages = (int) Math.ceil((double) count / limit);
        int currPage = Math.min(totalPages, query.getCurPage());
//        query.setCurPage(currPage);

        int start = currPage * limit - limit;
        start = start < 0 ? 0 : start;

        params.put("limit", limit);
        params.put("start", start);
        //翻页查找
        List<WorkFlowTaskDTO> dtos = processMgrMapper.qryTODOList(params);
        resultBO.setResults(dtos);
        resultBO.setCurPage(currPage);

        return resultBO;
    }

    @Override
    public com.jojo.util.pojo.ProcessInstance getProcessInstance(String processInstanceId)
    {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        com.jojo.util.pojo.ProcessInstance result = new com.jojo.util.pojo.ProcessInstance();
        result.setBusinessKey(processInstance.getBusinessKey());
        result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
        result.setProcessInstanceId(processInstance.getProcessInstanceId());
        result.setTenantId(processInstance.getTenantId());
        result.setProcessVariables(runtimeService.getVariables(processInstanceId));

        return result;
    }

    @Override
    public List<ProcessInstanceTask> getInstTasks(String instanceId)
    {
        List<ProcessInstanceTask> list = new ArrayList<ProcessInstanceTask>();
        // 设置当前任务信息
        List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(instanceId).orderByHistoricTaskInstanceEndTime().asc().list();
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(instanceId).orderByTaskCreateTime().asc().list();
        int historicSize = 0;
        if (historicTasks != null && !historicTasks.isEmpty())
        {
            for (int i =0;i<historicTasks.size();i++)
            {
                ProcessInstanceTask processInstanceTask = convert2WFHistoricTask(historicTasks.get(i),i);
                list.add(processInstanceTask);
            }
            historicSize = historicTasks.size();
        }

        if (tasks != null && !tasks.isEmpty())
        {
            for (int i =0;i<tasks.size();i++)
            {
                ProcessInstanceTask processInstanceTask = convert2WFTask(tasks.get(i),i+historicSize);
                list.add(processInstanceTask);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> traceProcessDetails(String processInstanceId) throws Exception {
        Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
        Object property = PropertyUtils.getProperty(execution, "activityId");
        String activityId = "";
        if (property != null) {
            activityId = property.toString();
        }
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点

        List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
        for (ActivityImpl activity : activitiList) {

            boolean currentActiviti = false;
            String id = activity.getId();

            // 当前节点
            if (id.equals(activityId)) {
                currentActiviti = true;
            }

            Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);

            activityInfos.add(activityImageInfo);
        }

        return activityInfos;
    }

    /**
     * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
     *
     * @param activity
     * @param processInstance
     * @param currentActiviti
     * @return
     */
    private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance,
                                                          boolean currentActiviti) throws Exception {
        Map<String, Object> vars = new HashMap<String, Object>();
        Map<String, Object> activityInfo = new HashMap<String, Object>();
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_TASK_ACTIVITI, currentActiviti);
        setPosition(activity, activityInfo);
        setWidthAndHeight(activity, activityInfo);

        Map<String, Object> properties = activity.getProperties();
        vars.put(JOJOConstants.WORKFLOW_PROCESSINST_VALS_TASK_TYPE, WorkFlowUtils.parseToZhType(properties.get("type").toString()));

        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        logger.debug("activityBehavior={}", activityBehavior);
        if (activityBehavior instanceof UserTaskActivityBehavior) {
            /*
             * 当前任务的分配角色
             */
            UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
            TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
            Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
            if (!candidateGroupIdExpressions.isEmpty()) {
                Task currentTask = null;
                /*
                 * 当前节点的task
                 */
                if (currentActiviti) {
                    currentTask = getCurrentTaskInfo(processInstance);
                }
                // 任务的处理角色,后续再加
//                setTaskGroup(vars, candidateGroupIdExpressions);

                // 当前处理人
                if (currentTask != null) {
                    ProcessInstanceTask instanceTask = convert2WFTask(currentTask, 0);
                    setCurrentTaskAssignee(vars, instanceTask);
                }
            }
        }

        vars.put(JOJOConstants.WORKFLOW_PROCESSINST_VALS_TASK_TIP, properties.get("documentation"));

        String description = activity.getProcessDefinition().getDescription();
        vars.put(JOJOConstants.WORKFLOW_PROCESSINST_VALS_TASK_DESC, description);

        logger.debug("trace variables: {}", vars);
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_VARS_KEY, vars);
        return activityInfo;
    }

    /**
     * 设置当前处理人信息
     *
     * @param vars
     * @param currentTask
     */
    private void setCurrentTaskAssignee(Map<String, Object> vars, ProcessInstanceTask currentTask) {
        String assignee = currentTask.getAssignee();
        if (StringUtils.isNotBlank(assignee)) {
//            User assigneeUser = identityService.createUserQuery().userId(assignee).singleResult();
//            String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
            vars.put(JOJOConstants.WORKFLOW_PROCESSINST_VALS_TASK_ASSIGNEE, assignee.trim());
        }
    }


//    /**
//     * 获取当前节点信息
//     *
//     * @param processInstance
//     * @return
//     */
    private Task getCurrentTaskInfo(ProcessInstance processInstance) {
        Task currentTask = null;
        try {
            String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
            logger.debug("current activity id: {}", activitiId);

            currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId)
                    .singleResult();
            logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));

        } catch (Exception e) {
            logger.error("can not get property activityId from processInstance: {}", processInstance);
        }
        return currentTask;
    }

    /**
     * 设置宽度、高度属性
     *
     * @param activity
     * @param activityInfo
     */
    private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_TASK_GRH_WIDTH, activity.getWidth());
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_TASK_GRH_HEIGHT, activity.getHeight());
    }

    /**
     * 设置坐标位置
     *
     * @param activity
     * @param activityInfo
     */
    private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_TASK_GRH_X, activity.getX());
        activityInfo.put(JOJOConstants.WORKFLOW_PROCESSINST_TASK_GRH_Y, activity.getY());
    }

}
