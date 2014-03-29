/**
 * 
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;

import com.jojo.web.common.context.ContextHolder;

/**
 * 该类主要是为了保存应用上下文以及Spring容器上下文对象，方便后续使用
 * 
 */
public class InitApplicationListener extends ContextLoader implements ServletContextListener {

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
        
        ContextHolder.setServletContext(sce.getServletContext()) ;
        ContextHolder.setApplicationContext(initWebApplicationContext(ContextHolder.servletContext));
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        closeWebApplicationContext(sce.getServletContext());

    }

}
