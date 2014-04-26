/**
 * 
 * JOJO
 * 
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.common.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <summary>
 * 
 * </summary>
 * 
 * @author jojo
 * 
 */
public class ByPassOtherFilter extends OncePerRequestFilter
{

    private Set<String> byPassStaticsSet;

    private Set<String> byPassControllerSet;

    /**
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String servletPath = request.getServletPath();
        boolean isStatic = false;
        if (byPassStaticsSet != null)
        {
            for (String a : byPassStaticsSet)
            {
                if (servletPath.startsWith("/" + a))
                {
                    isStatic = true;
                    // added by jojo.
                    break;
                }
            }
            if (isStatic)
            {
                request.getRequestDispatcher(servletPath).forward(request, response);
                return;
            }
        }

        boolean isController = false;
        if (byPassControllerSet != null)
        {
            for (String b : byPassControllerSet)
            {
                if (servletPath.startsWith("/" + b))
                {
                    isController = true;
                    // added by jojo.
                    break;
                }
            }
            if (isController)
            {
                request.getRequestDispatcher("/app" + servletPath).forward(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public Set<String> getByPassStaticsSet()
    {
        return byPassStaticsSet;
    }

    public void setByPassStaticsSet(Set<String> byPassStaticsSet)
    {
        this.byPassStaticsSet = byPassStaticsSet;
    }

    public Set<String> getByPassControllerSet()
    {
        return byPassControllerSet;
    }

    public void setByPassControllerSet(Set<String> byPassControllerSet)
    {
        this.byPassControllerSet = byPassControllerSet;
    }

}
