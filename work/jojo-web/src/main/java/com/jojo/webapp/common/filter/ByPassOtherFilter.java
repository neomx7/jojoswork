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
 * @author zhangyijia
 *
 */
public class ByPassOtherFilter extends OncePerRequestFilter {

    private Set<String> byPassStaticsSet;

    private Set<String> byPassControllerSet;

    /** 
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        boolean isStatic = false;
        if (byPassStaticsSet != null) {
            for (String a : byPassStaticsSet) {
                if (servletPath.startsWith("/" + a)) {
                    isStatic = true;
                }
            }
            if (isStatic) {
                request.getRequestDispatcher(servletPath).forward(request, response);
                return;
            }
        }

        boolean isController = false;
        if (byPassControllerSet != null) {
            for (String b : byPassControllerSet) {
                if (servletPath.startsWith("/" + b)) {
                    isController = true;
                }
            }
            if (isController) {
                request.getRequestDispatcher("/app" + servletPath).forward(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Getter method for property <tt>byPassStaticsSet</tt>.
     * 
     * @return property value of byPassStaticsSet
     */
    public Set<String> getByPassStaticsSet() {
        return byPassStaticsSet;
    }

    /**
     * Setter method for property <tt>byPassStaticsSet</tt>.
     * 
     * @param byPassStaticsSet value to be assigned to property byPassStaticsSet
     */
    public void setByPassStaticsSet(Set<String> byPassStaticsSet) {
        this.byPassStaticsSet = byPassStaticsSet;
    }

    /**
     * Getter method for property <tt>byPassControllerSet</tt>.
     * 
     * @return property value of byPassControllerSet
     */
    public Set<String> getByPassControllerSet() {
        return byPassControllerSet;
    }

    /**
     * Setter method for property <tt>byPassControllerSet</tt>.
     * 
     * @param byPassControllerSet value to be assigned to property byPassControllerSet
     */
    public void setByPassControllerSet(Set<String> byPassControllerSet) {
        this.byPassControllerSet = byPassControllerSet;
    }

}
