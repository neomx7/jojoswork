/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.awt.Point;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.el.FixedValue;
import org.apache.commons.collections.CollectionUtils;
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
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.BasePOJO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.PageQuery;
import com.jojo.util.pojo.ProcessInstance;
import com.jojo.util.pojo.ProcessInstanceTask;
import com.jojo.util.pojo.ResultInfo;
import com.jojo.util.ui.vo.workflow.LocationGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;
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
    * [进入我的已完成列表]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @return
    */
   @RequestMapping(value = "/process/toDoneTaskList")
   public String toDoneTaskList()
   {
       logger.info("match url 4 '/process/toDoneTaskList'");
       return "view/workflow/doneTask-list";
   }

   @RequestMapping(value = "/process/qryDoneTaskList")
   @ResponseBody
   public DataResponse<WorkFlowTaskDTO> qryDoneTaskList(
           @RequestParam(defaultValue = "1", value = "page") String page,
           @RequestParam(defaultValue = "20", value = "rows") String rows,
           @RequestParam("sidx") String sidx,
           @RequestParam("sord") String sord,
           // @RequestParam("_search") boolean search,
           @RequestParam(required = false, value = "searchField") String searchField,
           @RequestParam(required = false, value = "searchOper") String searchOper,
           @RequestParam(required = false, value = "searchString") String searchString,
           @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse)
   {
       DataResponse<WorkFlowTaskDTO> dataResponse = new DataResponse<WorkFlowTaskDTO>();
       try
       {
           DataRequest request = new DataRequest();
           request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
           request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
           request.setSidx(sidx);
           request.setSord(sord);
           request.setSearchField(searchField);
           request.setSearchOper(searchOper);
           request.setSearchString(searchString);

           WorkFlowQuery query = new WorkFlowQuery();
           query.setTaskMode(JOJOConstants.WORKFLOW_TASKMODE_DOING);
           // 从session中得到当前用户
           query.setOperId(getLoginUsrId(httpServletRequest, httpServletResponse));

            findWorkFlowResult(request, JOJOConstants.WORKFLOW_SERVICE, "queryWorkFlowDoneTask", query,
                   WorkFlowQuery.class, dataResponse);
           // ,httpServletRequest,httpServletResponse
       }
       catch (Exception e)
       {
           logger.error("调用工作流服务出错", e);
           redirectGolbalErr(dataResponse, e);
       }
       return dataResponse;
   }


    /**
    *
    * <summary>
    * [进入我的在办列表]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @return
    */
   @RequestMapping(value = "/process/toDoingTaskList")
   public String toDoingTaskList()
   {
       logger.info("match url 4 '/process/toDOingTaskList'");
       return "view/workflow/doingTask-list";
   }

    @RequestMapping(value = "/process/qryDOingTaskList")
    @ResponseBody
    public DataResponse<WorkFlowTaskDTO> queryDOingTaskList(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
            // @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
    {
        DataResponse<WorkFlowTaskDTO> dataResponse = new DataResponse<WorkFlowTaskDTO>();
        try
        {
            DataRequest request = new DataRequest();
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
            request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
            request.setSidx(sidx);
            request.setSord(sord);
            request.setSearchField(searchField);
            request.setSearchOper(searchOper);
            request.setSearchString(searchString);

            WorkFlowQuery query = new WorkFlowQuery();
            query.setTaskMode(JOJOConstants.WORKFLOW_TASKMODE_DOING);
            // 从session中得到当前用户
            query.setOperId(getLoginUsrId(httpServletRequest, httpServletResponse));

             findWorkFlowResult(request, JOJOConstants.WORKFLOW_SERVICE, "queryWorkFlowDOingTask", query,
                    WorkFlowQuery.class, dataResponse);
            // ,httpServletRequest,httpServletResponse
        }
        catch (Exception e)
        {
            logger.error("调用工作流服务出错", e);
            redirectGolbalErr(dataResponse, e);
        }
        return dataResponse;
    }

    /**
    *
    * <summary>
    * [访问待办列表]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param page
    * @param rows
    * @param sidx
    * @param sord
    * @param searchField
    * @param searchOper
    * @param searchString
    * @param filters
    * @param httpServletRequest
    * @return
    */
   @RequestMapping(value = "/process/qryMyTaskList")
   @ResponseBody
   public DataResponse<WorkFlowTaskDTO> queryMyTaskList(
           @RequestParam(defaultValue = "1", value = "page") String page,
           @RequestParam(defaultValue = "20", value = "rows") String rows,
           @RequestParam("sidx") String sidx,
           @RequestParam("sord") String sord,
           // @RequestParam("_search") boolean search,
           @RequestParam(required = false, value = "searchField") String searchField,
           @RequestParam(required = false, value = "searchOper") String searchOper,
           @RequestParam(required = false, value = "searchString") String searchString,
           @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse)
   {
       DataResponse<WorkFlowTaskDTO> dataResponse = new DataResponse<WorkFlowTaskDTO>();
       try
       {
           DataRequest request = new DataRequest();
           request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
           request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
           request.setSidx(sidx);
           request.setSord(sord);
           request.setSearchField(searchField);
           request.setSearchOper(searchOper);
           request.setSearchString(searchString);

           WorkFlowQuery query = new WorkFlowQuery();
           query.setTaskMode(JOJOConstants.WORKFLOW_TASKMODE_TODO);
           // 从session中得到当前用户
           query.setOperId(getLoginUsrId(httpServletRequest, httpServletResponse));

           return findWorkFlowResult(request, JOJOConstants.WORKFLOW_SERVICE, "queryWorkFlowTODOTask", query,
                   WorkFlowQuery.class, dataResponse
           // ,httpServletRequest,httpServletResponse
           );

       }
       catch (Exception e)
       {
           logger.error("调用工作流服务出错", e);
           redirectGolbalErr(dataResponse, e);
       }
       return dataResponse;
   }

   @SuppressWarnings("unchecked")
   public <T> DataResponse<T> findWorkFlowResult(DataRequest request, String qryBean, String qryMethodName,
           PageQuery qryParamObj
           // ,Class<T> cls
           , Class<?> cls, DataResponse<T> response
   // ,HttpServletRequest httpServletRequest
   // ,HttpServletResponse httpServletResponse
   ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
           InvocationTargetException
   {
       Object wfService = (ContextHolder.getBean(qryBean));// JOJOConstants.WORKFLOW_SERVICE

       int count = 0;// 总记录数
       int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
       int totalPages = 0;// 总页数
       int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码

       qryParamObj.setPageLimit(limit);
       qryParamObj.setCurPage(page);

       // 反射通过查询方法得到对象
       PageResultBO<T> resultBO = null;

       Class<? extends Object> clazz = wfService.getClass();
       Method qryMethod = null;
       qryMethod = clazz.getDeclaredMethod(qryMethodName, cls);
       resultBO = (PageResultBO<T>) (qryMethod.invoke(wfService, qryParamObj));

       int currPage = resultBO.getCurPage();
       // count = resultBO.getTotalCount();
       // totalPages = (int) Math.ceil((double) count / limit);
       // int currPage = Math.min(totalPages, page);
       // qryParamObj.setCurPage(currPage);
       //
       // int start = currPage * limit - limit;
       // start = start < 0 ? 0 : start;
       List<T> list = resultBO.getResults();

       if (CollectionUtils.isNotEmpty(list))
       {
           for (int i=0;i<list.size();i++)
           {
               BasePOJO pojo = (BasePOJO)(list.get(i));
               pojo.setNumber((i+1));
           }
       }


       response.setRecords(count);
       response.setTotal(totalPages);
       response.setPage(currPage);
       response.setRows(list);

       return response;
   }

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
            @RequestBody WorkFlowQuery query, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
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
        String operId = getLoginUsrId(httpServletRequest, httpServletResponse);
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
     * [获取流程跟踪详情]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/workflow/traceProcessDetails")
    @ResponseBody
    public List<Map<String, Object>> traceProcessDetails(@RequestBody WorkFlowQuery query)
    {
        List<Map<String, Object>> list = null;
        if (StringUtils.isBlank(query.getProInsId()))
        {
            logger.error("can not start process instance cause proInsId is null");
            return list;
        }
        try
        {
            WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder
                    .getBean(JOJOConstants.WORKFLOW_SERVICE));
            list = workFlowExecutor.traceProcessDetails(query.getProInsId());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     *
     * <summary>
     * [完成当前task]<br>
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
            form.getProcessInstanceTask().setVariables(instanceTask.getVariables());
            Object approvedRequired = instanceTask.getVariables().get(
                    JOJOConstants.WORKFLOW_TASK_VARIABLES_KYE_APPROVEDREQUIRED);
            if (approvedRequired != null)
            {
                form.setApprovedRequired(convert2Boolean(((FixedValue) approvedRequired).getExpressionText()));
            }
        }

        return "view/workflow/edit-workflow";
    }

    /**
     *
     * <summary>
     * [把字符串表达式转成boolean值，如 'yes' ，'true'，'1'等字符串都按ture返回，否则按false返回]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param orgiStr
     * @return
     */
    private boolean convert2Boolean(String orgiStr)
    {
        if (StringUtils.isBlank(orgiStr))
        {
            return false;
        }
        orgiStr = orgiStr.trim();
        if (orgiStr.equalsIgnoreCase("yes") || orgiStr.equalsIgnoreCase("true") || orgiStr.equalsIgnoreCase("1"))
        {
            return true;
        }
        return false;
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
            @RequestParam(required = true, value = "instanceId") String instanceId)
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
            WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder
                    .getBean(JOJOConstants.WORKFLOW_SERVICE));
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
            HttpServletResponse httpServletResponse, @ModelAttribute("form") ProcessInstanceTaskForm form)
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

        // 只有需要审批的节点才需要保存审批记录
        if (form.isApprovedRequired())
        {
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
            approvalDO.setCrtUserName(form.getAppContext().getUserName());
            approvalDO.setUpdUserId(operId);
            approvalDO.setUpdUserName(form.getAppContext().getUserName());

            try
            {
                //如果已经存在，则update，否则insert
                Map<String, Object> params = new HashMap<String, Object>(8);
                params.put("theId", approvalDO.getTheId());
                WorkFlowTaskApprovalDO taskApprovalDO = workFlowBiz.findTaskApproval(params);
                if (taskApprovalDO == null)
                {
                    workFlowBiz.addTaskApproval(approvalDO);
                }else {
                    workFlowBiz.updateTaskApproval(taskApprovalDO);
                }
            }
            catch (Exception e)
            {
                logger.error(e.getMessage(), e);
                dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                dataResponse.setTip("系统内部错误");
                dataResponse.setTipDesc("无法继续流程：系统内部错误:" + ExceptionUtils.getStackTrace(e));
                return dataResponse;
            }
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
            if (form.isApprovedRequired())
            {
                Map<String, Object> variables4Task = new HashMap<String, Object>(10);
                variables4Task.put(JOJOConstants.WORKFLOW_TASK_VARIABLES_KYE_APPROVEDBYMANAGER,
                        form.getApprvFlg());
                task.setVariablesLocal(variables4Task);
            }
            workFlowExecutor.completeTask(task);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("系统内部错误");
            dataResponse.setTipDesc("无法继续流程：工作流错误:" + ExceptionUtils.getStackTrace(e));
            return dataResponse;
        }

        return dataResponse;
    }
}
