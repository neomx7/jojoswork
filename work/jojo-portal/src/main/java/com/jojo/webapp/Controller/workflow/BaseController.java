/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller.workflow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;

/**
 * <summary>
 * [controller父类，提供通用的一些方法]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * <summary>
     * [按照json格式组装查询结果]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param request
     * @param cls
     * @return
     */
    public <T> DataResponse<T> findResult(DataRequest request, Class<T> cls, Object qryService, String qryMethod)
    {
        if (qryService == null)
        {
            logger.error("null query service found.");
            return null;
        }
        DataResponse<T> response = new DataResponse<T>();
        int count = 0;// 总记录数
        int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
        int totalPages = 0;// 总页数
        int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码
        List<T> list = new ArrayList<T>();
        totalPages = (int) Math.ceil((double) count / limit);
        int currPage = Math.min(totalPages, page);

        int start = currPage * limit - limit;
        start = start < 0 ? 0 : start;
        Class clazz = qryService.getClass();
        Method query = null;
        try
        {
            query = clazz.getDeclaredMethod(qryMethod, DataRequest.class);
        }
        catch (NoSuchMethodException e)
        {
            logger.error("findResult failed. exception info: [{}]", e);
        }
        catch (SecurityException e)
        {
            logger.error("findResult failed. exception info: [{}]", e);
        }
        Object resultList = null;
        try
        {
            resultList = query.invoke(qryService, request);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            logger.error("findResult failed. exception info: [{}]", e);
        }
        // 转换成list对象
        list = (List<T>) resultList;

        response.setRecords(count);
        response.setTotal(totalPages);
        response.setPage(currPage);
        response.setRows(list);

        return response;
    }
}
