/**
 *
 * 有限公司
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jojo.util.common.CookieUtil;
import com.jojo.util.pojo.BasePOJO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;
import com.jojo.web.common.authenticate.AuthenticationUtil;
import com.jojo.web.common.context.AppContextHolder;
import com.jojo.webapp.common.filter.AuthenticationAndAuthorizationFilter.JOJOCid;

/**
 *
 * @author JOJO
 */
public class BaseController
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getLoginUsrId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        String loginUserId = null;
        if (AppContextHolder.get() == null)
        {
            // 获取登录用户名
            CookieUtil cookieUtil = new CookieUtil(httpServletRequest, httpServletResponse);
            final JOJOCid appCid = new JOJOCid(
                    StringUtils.trimToEmpty(cookieUtil.getCookie(AuthenticationUtil.APP_CID)));

            if (appCid.isValid())
            {
                loginUserId = (appCid.getUserId());
            }

        }
        else
        {
            loginUserId = AppContextHolder.get().getUserId();
        }
        return loginUserId;
    }

    public void redirectGolbalErr(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Exception e)
    {
        // 转给全局异常
        try
        {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/tip/exception?tip="
                    + URLEncoder.encode(e.getMessage(), "UTF-8") + "&tipDesc="
                    + URLEncoder.encode(ExceptionUtils.getStackTrace(e), "UTF-8"));
        }
        catch (IOException e1)
        {
            logger.error("处理异常时发生另一个异常", e1);
        }
    }

    public void redirectGolbalErr(DataResponse<WorkFlowTaskDTO> dataResponse, Exception e)
    {
        try
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            dataResponse.setTip(URLEncoder.encode(e.getMessage() == null ? "" : e.getMessage(), "UTF-8"));
//            dataResponse.setTipDesc(URLEncoder.encode(ExceptionUtil.getSimpleExceptionStackTrace(e), "UTF-8"));
            dataResponse.setTip( e.getMessage() );
            dataResponse.setTipDesc( ExceptionUtils.getStackTrace(e));
        }
        catch (Exception e1)
        {
            logger.error("处理异常时发生另一个异常", e1);
        }

    }

    // public String sendErr(String appConetxtPath,String tip ,String tipDesc){
    // try
    // {
    // return appConetxtPath
    // + "/tip/exception?tip="
    // + URLEncoder.encode(tip, "UTF-8")
    // + "&tipDesc="
    // + URLEncoder.encode(tipDesc,"UTF-8");
    // }
    // catch (IOException e)
    // {
    // logger.error("处理异常时发生另一个异常",e);
    // }
    // return "";
    // }

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
    @SuppressWarnings("unchecked")
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
        Class<? extends Object> clazz = qryService.getClass();
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
        for (int i=0;i<list.size();i++)
        {
            BasePOJO pojo = (BasePOJO)(list.get(i));
            pojo.setNumber(i);
        }


        response.setRecords(count);
        response.setTotal(totalPages);
        response.setPage(currPage);
        response.setRows(list);

        return response;
    }
}
