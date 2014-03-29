/**
 * 
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.web.common.context;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @author finley.yao
 * @version $Id: ContextHolder.java, v 0.1 2013-7-31 下午3:06:20 finley.yao Exp $
 */
public class ContextHolder {

    public static ApplicationContext applicationContext;

    public static ServletContext     servletContext;

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext)
	{
		ContextHolder.applicationContext = applicationContext;
	}

	public static void setServletContext(ServletContext servletContext)
	{
		ContextHolder.servletContext = servletContext;
	}

}
