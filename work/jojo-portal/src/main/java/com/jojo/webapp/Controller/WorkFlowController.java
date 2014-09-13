/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.awt.Point;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.CommonBiz;
import com.jojo.biz.WorkFlowBiz;
import com.jojo.dal.common.postgre.domain.AttachDO;
import com.jojo.dal.common.postgre.domain.WorkFlowTaskApprovalDO;
import com.jojo.integration.workflow.WorkFlowExecutor;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.ProcessInstance;
import com.jojo.util.pojo.ProcessInstanceTask;
import com.jojo.util.pojo.ResultInfo;
import com.jojo.util.ui.vo.workflow.LocationGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.web.common.context.ContextHolder;
import com.jojo.webapp.form.ProcessInstanceTaskForm;

/**
 *
 * <summary>
 * [工作流处理]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class WorkFlowController extends BaseController
{

    @Autowired
    private CommonBiz commonBiz;

    @Autowired
    private WorkFlowBiz workFlowBiz;

    /**
     *
     * <summary>
     * <p>
     * 点击左侧边栏的3级菜单,显示已经部署的流程定义list页面
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    @RequestMapping(value = "/workflow/toList")
    public String toList()
    {
        // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/workflow/workflow-list";
    }

    /**
     *
     * <summary>
     * [使用json字符串传递查询参数+返回结果]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/workflow/queryDefines")
    @ResponseBody
    public DataResponse<WorkFlowDefine> queryDefines(@RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            HttpServletRequest httpServletRequest)
    {
        // 调用工作流服务获取列表，workFlowExecutor是实现在工作流服务上的remoting 服务
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
        try
        {
            DataRequest request = new DataRequest();
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
            request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
            request.setSearchField(searchField);
            request.setSearchOper(searchOper);
            request.setSearchString(searchString);
            // List<WorkFlowDefine> result =
            // workFlowExecutor.queryFlowDefines();
            return findResult(request, WorkFlowDefine.class, workFlowExecutor, "queryFlowDefines");
        }
        catch (Exception e)
        {
            logger.error("queryDefines 4 workflow failed. exception info: [{}]", e);

        }

        return null;
    }

    /**
     *
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param proDefId
     * @throws IOException
     */
    @RequestMapping(value = "/workflow/getWorkFlowGraph")
    public void getWorkFlowGraph(
    // @RequestParam(required = true, value = "proDefId")
            String proDefId, HttpServletResponse httpServletResponse
    // ,@RequestBody WorkFlowDefineGraph graph
    ) throws IOException
    {
        // 得到流程定义图片
        if (StringUtils.isBlank(proDefId))
        {
            logger.error("no proDefId set 4 getWorkFlowGraph.");
            return;
        }
        // InputStream is = null;
        AttachDO attachDO = null;
        try
        {
            attachDO = commonBiz.download(proDefId.replaceAll(":", "_"));
        }
        catch (Exception e)
        {
            logger.error("getWorkFlowGraph failed, excepton info : [{}]", e);
        }
        if (attachDO != null)
        {
            // graph.setInputStream(is);
            // graph.setPoint(workFlowDefineGraph.getPoint());
            httpServletResponse.setContentType("image/png");
            OutputStream out = null;
            try
            {
                out = httpServletResponse.getOutputStream();
                // byte[] bs = new byte[1024];
                // int n = 0;
                // while ((n = is.read(bs)) != -1)
                // {
                // out.write(bs, 0, n);
                // }

                out.write(attachDO.getAttachContent());
                out.flush();
            }
            catch (Exception ex)
            {
                logger.error("write into httpServletResponse failed, excepton info : [{}]", ex);
            }
            finally
            {
                // if (is != null)
                // {
                // is.close();
                // }
                if (out != null)
                {
                    out.close();
                }
            }
        }
    }

    @RequestMapping(value = "/workflow/locationWorkFlowGraph")
    @ResponseBody
    public LocationGraph locationWorkFlowGraph(
    // @RequestParam(required = true, value = "proDefId")
            @RequestBody WorkFlowQuery query)
    {
        LocationGraph location = new LocationGraph();
        if (StringUtils.isBlank(query.getProDefId()))
        {
            logger.error("no proDefId set 4 getWorkFlowGraph.");
            return location;
        }
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
        Point pointNow = workFlowExecutor.locationWorkFlowGraph(query.getProDefId());
        location.setX(pointNow.x);
        location.setY(pointNow.y);
        return location;
    }

    @RequestMapping(value = "/workflow/startProcessInstance")
    @ResponseBody
    public ResultInfo startProcessInstance(
    // @RequestParam(required = true, value = "proDefId")
    // String proDefKey, HttpServletResponse httpServletResponse
            @RequestBody WorkFlowQuery query)
    {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isBlank(query.getProDefKey()))
        {
            logger.error("can not start process instance cause proDefKey is null");
            resultInfo.setResultCode(-1);
            return resultInfo;
        }
        // 生成业务对象的唯一索引，作为businessKey,每个业务对象都放置一个businesKey
        String businessKey = UUID.randomUUID().toString();
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
        // 从session中获取operId
        String operId = "jojo";
        Map<String, Object> variables = new HashMap<String, Object>(0);
        @SuppressWarnings("unused")
        String processInstanceId = workFlowExecutor.startProcessInstanceByKey(query.getProDefKey(), operId,
                businessKey, variables);

        // TODO 把流程实例id保存到业务对象

        resultInfo.setResultCode(0);
        return resultInfo;
    }

    /**
     *
     * <summary>
     * [获取流程跟踪图]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/workflow/traceProcess")
    @ResponseBody
    public LocationGraph traceProcess(@RequestBody WorkFlowQuery query)
    {
        LocationGraph location = new LocationGraph();

        if (StringUtils.isBlank(query.getProDefId()))
        {
            logger.error("can not start process instance cause proDefId is null");
            return location;
        }
        if (StringUtils.isBlank(query.getProInsId()))
        {
            logger.error("can not start process instance cause proInsId is null");
            return location;
        }

        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));

        Point pointNow = workFlowExecutor.locationWorkFlowGraph(query.getProDefId());
        Map<String, Object> result = workFlowExecutor.traceProcess(query.getProInsId());

        if (result != null && result.get("height") != null)
        {
            location.setHeight(Integer.valueOf(result.get("height").toString()));
            location.setWidth(Integer.valueOf(result.get("width").toString()));
            location.setX(Integer.valueOf(result.get("x").toString()));
            location.setY(Integer.valueOf(result.get("y").toString()));
            location.setDefX(pointNow.getX());
            location.setDefY(pointNow.getY());
        }

        return location;
    }

    /**
     *
     * <summary>
     * [结束当前task]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param query
     * @return
     */

    @RequestMapping(value = "/workflow/processTODOTask")
    @ResponseBody
    public ResultInfo processTODOTask(@RequestBody ProcessInstanceTaskForm form)
    {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isBlank(form.getProcessInstanceTask().getTaskId()))
        {
            logger.error("can not processTODOTask cause taskId is null");
            return resultInfo;
        }
        if (StringUtils.isBlank(form.getProcessInstanceTask().getNextAssignee()))
        {
            logger.error("can not processTODOTask cause nextAssignee is null");
            return resultInfo;
        }

        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));

        ProcessInstanceTask processTask = new ProcessInstanceTask();
        processTask.setNextAssignee(form.getProcessInstanceTask().getNextAssignee());
        processTask.setTaskId(form.getProcessInstanceTask().getTaskId());
        workFlowExecutor.completeTask(processTask);

        resultInfo.setResultCode(0);
        return resultInfo;
    }

    /**
     *
     * <summary>
     * [进入我的待办列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    @RequestMapping(value = "/process/toTODOTaskList")
    public String toMyTaskList()
    {
        logger.info("match url 4 '/process/toTODOTaskList'");
        // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/workflow/todoTaskList-list";
    }

    /**
     *
     * <summary>
     * [进入流程任务节点编辑页面]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = "/process/toProcessTask")
    public String toProcessTaskList(@RequestBody ProcessInstanceTaskForm form,
            @ModelAttribute("form") ProcessInstanceTaskForm model)
    {
        logger.info("match url 4 '/process/toProcessTask'");
        model.getProcessInstanceTask().setTaskId(form.getProcessInstanceTask().getTaskId());
        // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/workflow/processTask";
    }

    /**
     *
     * <summary>
     * [展示task节点信息]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param form
     * @return
     */
    @RequestMapping(value = "/workflow/showTask")
    public String showTask(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            @ModelAttribute("form") ProcessInstanceTaskForm form)
    {
        String processInstanceId = form.getTheInstId();
        String instTaskId = form.getTheTaskId();

        if (StringUtils.isBlank(processInstanceId) || StringUtils.isBlank(instTaskId))
        {
            form.setErrorMsg("未获取到有效的流程taskid");
            return "view/common/common-error";
        }
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
        ProcessInstance processInstance = workFlowExecutor.getProcessInstance(processInstanceId);
        ProcessInstanceTask instanceTask = workFlowExecutor.getProcessInstanceTask(instTaskId);
        if (instanceTask != null && processInstance != null)
        {
            form.setBusinessKey(processInstance.getBusinessKey());
            form.setBusinessKeyURL(String.valueOf(processInstance.getProcessVariables().get(
                    JOJOConstants.WORKFLOW_PROCESSINST_BIZ_KEY_URL)));
            form.getProcessInstanceTask().setProcessDefinitionId(processInstance.getProcessDefinitionId());
            form.getProcessInstanceTask().setProcessInstanceId(processInstanceId);
            form.getProcessInstanceTask().setTenantId(processInstance.getTenantId());
            form.getProcessInstanceTask().setTaskId(instanceTask.getTaskId());
            form.getProcessInstanceTask().setTaskName(instanceTask.getTaskName());
        }

        return "view/workflow/edit-workflow";
    }

    /**
     *
     * <summary>
     * [展示任务节点记录列表，只读]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param form
     * @return
     */
    @RequestMapping(value = "/workflow/showAllTasks")
    @ResponseBody
    public DataResponse<ProcessInstanceTask> showAllTasks(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestParam(required = false, defaultValue = "1", value = "page") String page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") String rows,
            @RequestParam(required = false, value = "sidx") String sidx,
            @RequestParam(required = false, value = "sord") String sord,

            // @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters,
            @RequestParam(required = true, value = "instanceId") String instanceId )
    {
        logger.info("match url 4 '/workflow/showAllTasks'");
        DataResponse<ProcessInstanceTask> dataResponse = new DataResponse<ProcessInstanceTask>();

        String processInstanceId = instanceId;

        if (StringUtils.isBlank(processInstanceId))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到有效的流程实例id");
            dataResponse.setTipDesc("无法保存申请：未获取到有效的流程实例id");
            return dataResponse;
        }
        // 得到流程实例对应的全部流程task节点
        try
        {
            WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
            List<ProcessInstanceTask> list = workFlowExecutor.getInstTasks(processInstanceId);
            dataResponse.setRows(list);
        }
        catch (Exception e)
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip(e.getMessage());
            dataResponse.setTipDesc(ExceptionUtils.getStackTrace(e));
            return dataResponse;
        }
        return dataResponse;
    }

    /**
     *
     * <summary>
     * [结束当前流程]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/workflow/completeTask")
    @ResponseBody
    public DataResponse<ProcessInstanceTaskForm> completeTask(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @RequestBody ProcessInstanceTaskForm form)
    {
        logger.info("match url 4 '/workflow/completeTask'");
        DataResponse<ProcessInstanceTaskForm> dataResponse = new DataResponse<ProcessInstanceTaskForm>();

        // 启动流程并且同时也设定下一个流程处理人，同时下方代码的variables也设置
        if (StringUtils.isBlank(form.getTheTaskId()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到当前流程task的id");
            dataResponse.setTipDesc("无法继续流程：当前流程task的id未设置。");
            return dataResponse;
        }
        if (StringUtils.isBlank(form.getNextAssignee()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到有效的下一个流程处理人的id");
            dataResponse.setTipDesc("无法继续流程：下一个流程节点的申请人id未设置。");
            return dataResponse;
        }
        if (StringUtils.isBlank(form.getBusinessKey()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到有效的业务键值businessKey");
            dataResponse.setTipDesc("无法继续流程：未获取到有效的业务键值businessKey。");
            return dataResponse;
        }
        String operId = getLoginUsrId(httpServletRequest, httpServletResponse);
        // TODO 保存任务审批记录
        String instanceId = form.getTheInstId();
        String taskId = form.getTheTaskId();
        WorkFlowTaskApprovalDO approvalDO = new WorkFlowTaskApprovalDO();
        approvalDO.setApprvContent(form.getApprvContent());
        approvalDO.setApprvFlg(form.getApprvFlg());
        approvalDO.setBusinessKey(form.getBusinessKey());
        approvalDO.setInstanceId(instanceId);
        approvalDO.setInstTaskId(taskId);
        approvalDO.setTheId(instanceId + JOJOConstants.UNDERLINE + taskId);
        approvalDO.setAssigner(operId);
        // approvalDO.setCrtTime(DateUtils.getCurrentDateTimeMs());
        approvalDO.setCrtTimestamp(new Timestamp(new Date().getTime()));
        approvalDO.setCrtUserId(operId);
        try
        {
            workFlowBiz.addTaskApproval(approvalDO);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("系统内部错误");
            dataResponse.setTipDesc("无法继续流程：系统内部错误:" + ExceptionUtils.getStackTrace(e));
            return dataResponse;
        }
        try
        {
            // 结束流程
            WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder
                    .getBean(JOJOConstants.WORKFLOW_SERVICE));
            ProcessInstanceTask task = new ProcessInstanceTask();
            task.setNextAssignee(form.getNextAssignee());
            task.setProcessInstanceId(form.getTheInstId());
            task.setTaskId(form.getTheTaskId());
            workFlowExecutor.completeTask(task);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("系统内部错误");
            dataResponse.setTipDesc("无法继续流程：工作流错误:" + ExceptionUtils.getStackTrace(e));
        }

        return dataResponse;
    }
}
