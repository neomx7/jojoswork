/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.util.CommonUtils;
import com.jojo.util.biz.bo.ApplyBO;
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.PageQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;
import com.jojo.web.common.context.ContextHolder;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class ApplyController extends BaseController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * <summary>
     * [查询已保存的申请]<br>
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
    @RequestMapping(value = "/process/createApplyList")
    @ResponseBody
    public DataResponse<ApplyBO> queryApply(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
            // @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest)
    {
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
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
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
            @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
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
            //TODO 从session中得到当前用户
            query.setOperId("jojo");


            return findWorkFlowResult(request, JOJOConstants.WORKFLOW_SERVICE, "queryWorkFlowTODOTask",
                    query,WorkFlowQuery.class, dataResponse);

        }
        catch (Exception e)
        {
          logger.error("调用工作流服务出错",e);
          redirectGolbalErr(dataResponse, e);
        }
        return dataResponse;
    }

    @SuppressWarnings("unchecked")
    public <T> DataResponse<T> findWorkFlowResult(DataRequest request, String qryBean, String qryMethodName,
            PageQuery qryParamObj
//            ,Class<T> cls
            ,Class<?> cls
            ,DataResponse<T> response
//            ,HttpServletRequest httpServletRequest
//            ,HttpServletResponse httpServletResponse
            ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
            Object wfService = (ContextHolder.getBean(qryBean));// "workFlowServiceProxy"

            int count = 0;// 总记录数
            int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
            int totalPages = 0;// 总页数
            int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码

            qryParamObj.setPageLimit(limit);
            qryParamObj.setCurPage(page);
            //从session中得到公用户
            qryParamObj.setOperId("jojo");

            // 反射通过查询方法得到对象
            PageResultBO<T> resultBO = null;

            Class<? extends Object> clazz = wfService.getClass();
            Method qryMethod = null;
            qryMethod = clazz.getDeclaredMethod(qryMethodName, cls);
            resultBO =  (PageResultBO<T>)(qryMethod.invoke(wfService, qryParamObj));

            int currPage = resultBO.getCurPage();
//            count = resultBO.getTotalCount();
//            totalPages = (int) Math.ceil((double) count / limit);
//            int currPage = Math.min(totalPages, page);
//            qryParamObj.setCurPage(currPage);
//
//            int start = currPage * limit - limit;
//            start = start < 0 ? 0 : start;

            response.setRecords(count);
            response.setTotal(totalPages);
            response.setPage(currPage);
            response.setRows(resultBO.getResults());

        return response;
    }

    @SuppressWarnings("unused")
    private ApplyBO createOne(String id, String applyName, String remark, int number)
    {
        ApplyBO bo = new ApplyBO();
        bo.setId(id);
        bo.setTheName(applyName);
        bo.setTheRemark(remark);
        bo.setCrtTime(CommonUtils.getDateFormat2Ms(new Date()));
        bo.setNumber(number);
        return bo;
    }
}
