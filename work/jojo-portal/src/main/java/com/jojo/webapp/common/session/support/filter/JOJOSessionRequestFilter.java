/**
 *
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.common.session.support.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.jojo.util.constants.JOJOConstants;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class JOJOSessionRequestFilter extends OncePerRequestFilter
{

    private static final Set<String> notFilters = new HashSet<String>(40);

    private String notFilter;

    public void init()
    {
        String[] strs = notFilter.split(",");
        for (String string : strs)
        {
            notFilters.add(string);
        }
    }

    /**
     * session 有效性拦截
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        if (getNotFilters().isEmpty())
        {
            init();
        }
        // 请求的uri
        String uri = request.getRequestURI();

        // 是否过滤
        boolean doFilter = true;
        for (String noFStr : notFilters)
        {
            if (uri.indexOf(noFStr) >= 0)
            {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter)
        {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute(JOJOConstants.SESSION_KEY_LOGINUSER);
            if (null == obj)
            {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                // 设置request和response的字符集，防止乱码
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                String loginPage = request.getContextPath() + "/login";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("alert('网页过期，请重新登录！');");
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
                return;
            }
        }

        // 如果session中存在登录者实体，则继续
        filterChain.doFilter(request, response);
    }

    public Set<String> getNotFilters()
    {
        return notFilters;
    }


    public String getNotFilter()
    {
        return notFilter;
    }

    public void setNotFilter(String notFilter)
    {
        this.notFilter = notFilter;
    }

}
