/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.CommonUtils;
import com.jojo.util.biz.bo.ApplyBO;
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.biz.bo.UserBO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.PageQuery;
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
public class ApplyController
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
    @RequestMapping(value = "/demo/createApplyList")
    @ResponseBody
    public DataResponse<ApplyBO> queryApply(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
//            @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters,
            HttpServletRequest httpServletRequest
     )
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


    @RequestMapping(value = "/demo/toMyTaskList")
    @ResponseBody
    public DataResponse<WorkFlowTaskDTO> queryMyTaskList(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
//            @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters,
            HttpServletRequest httpServletRequest
     )
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



    public <T> DataResponse<T> findWorkFlowResult(DataRequest request, String wfQryBean, String wfQryMethod,PageQuery wfQryParamObj)
    {
       Object workFlowExecutor =  (ContextHolder.getBean(wfQryBean));//"workFlowServiceProxy"

        DataResponse<T> response = new DataResponse<T>();
        int count = 0;// 总记录数
        int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
        int totalPages = 0;// 总页数
        int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码
        List<T> list = new ArrayList<T>(20);



        //反射通过查询方法得到对象
        PageResultBO resultBO = null;

        count = resultBO.getTotalCount();
        totalPages =  (int)Math.ceil( (double)count / limit );
//        if (count % limit != 0)
//        {
//            totalPages++;
//        }
        int currPage = Math.min(totalPages, page);

        int start = currPage * limit - limit;
        start = start < 0 ? 0 : start;

//        int curLimit = start+page*limit >= count ? count:start+page*limit;
//        for (int i = start; i < curLimit; i++)
//        {
//            list.add((T) createOne(String.valueOf(i), "applyName" + i, "备注神马的...",i));
//        }
        response.setRecords(count);
        response.setTotal(totalPages);
        response.setPage(currPage);
        response.setRows(resultBO.getResults());

        return response;
    }

    private ApplyBO createOne(String id, String applyName, String remark,int number)
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
