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

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.process.dal.postgre.AttachMgrMapper;
import com.jojo.process.dal.postgre.ProcessMgrMapper;
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessTask;
import com.jojo.util.pojo.ProcessTaskForm;
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
    public void completeTask(String taskId, String operId)
    {
        taskService.complete(taskId);
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
    public ProcessTaskForm getTaskForm(String taskId)
    {
        // TODO Auto-generated method stub
        return null;
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
    public List<ProcessTask> createUnsignedTaskQuery(String userId, String processDefKey)
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
    public List<ProcessTask> createTodoTaskQuery(String userId, String processDefKey)
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
    private List<ProcessTask> getProcessTaskList(List<Task> tasks)
    {
        List<ProcessTask> processTasks = null;
        if (tasks != null && !tasks.isEmpty())
        {
            processTasks = new ArrayList<ProcessTask>(tasks.size());
            for (Task task : tasks)
            {
                ProcessTask processTask = tranferTask(task);
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
    private ProcessTask tranferTask(Task task)
    {
        ProcessTask processTask = new ProcessTask();
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
    public PageResultBO queryWorkFlowTask(WorkFlowQuery query)
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

}
