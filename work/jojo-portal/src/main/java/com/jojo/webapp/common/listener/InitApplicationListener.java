/**
 * 
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.common.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.jojo.web.common.context.ContextHolder;

/**
 * 该类主要是为了保存应用上下文以及Spring容器上下文对象，方便后续使用
 * 
 */
public class InitApplicationListener extends ContextLoaderListener {

    public InitApplicationListener() {
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

//        ContextHolder.setServletContext(sce.getServletContext()) ;
//
//        // 初始化Spring容器
//        ContextHolder.setApplicationContext(initWebApplicationContext(ContextHolder.servletContext));
        super.contextInitialized(sce);
        
        ContextHolder.setServletContext(sce.getServletContext()) ;
//        ContextHolder.setApplicationContext(initWebApplicationContext(ContextHolder.servletContext));
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        super.contextDestroyed(sce);
//        closeWebApplicationContext(sce.getServletContext());

    }

}
