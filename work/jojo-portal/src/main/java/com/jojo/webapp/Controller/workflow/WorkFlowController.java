/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller.workflow;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
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

}
