/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller.workflow;

import java.awt.Point;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.CommonBiz;
import com.jojo.dal.common.postgre.domain.AttachDO;
import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.ProcessTask;
import com.jojo.util.pojo.ProcessTaskForm;
import com.jojo.util.pojo.ResultInfo;
import com.jojo.util.ui.vo.workflow.LocationGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.web.common.context.ContextHolder;

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
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean("workFlowServiceProxy"));
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
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean("workFlowServiceProxy"));
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
        // TODO 生成业务对象的唯一索引，作为businessKey,每个业务对象都放置一个businesKey
        String businessKey = UUID.randomUUID().toString();
        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean("workFlowServiceProxy"));
        // 从session中获取operId
        String operId = "jojo";
        Map<String, Object> variables = new HashMap<String, Object>(0);
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

        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean("workFlowServiceProxy"));

        Point pointNow = workFlowExecutor.locationWorkFlowGraph(query.getProDefId());
        Map<String, Object> result = workFlowExecutor.traceProcess(query.getProInsId());

        if (result != null && result.get("height") != null)
        {
            location.setHeight(Integer.valueOf(result.get("height").toString()) );
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
    public ResultInfo processTODOTask(@RequestBody ProcessTaskForm form)
    {
        ResultInfo  resultInfo = new ResultInfo();
        if (StringUtils.isBlank(form.getTaskId()))
        {
            logger.error("can not processTODOTask cause taskId is null");
            return resultInfo;
        }
        if (StringUtils.isBlank(form.getNextAssignee()))
        {
            logger.error("can not processTODOTask cause nextAssignee is null");
            return resultInfo;
        }

        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean("workFlowServiceProxy"));

        ProcessTask processTask = new ProcessTask();
        processTask.setNextAssignee(form.getNextAssignee());
        processTask.setTaskId(form.getTaskId());
        workFlowExecutor.completeTask(processTask);

        resultInfo.setResultCode(0);
        return resultInfo;
    }
}
