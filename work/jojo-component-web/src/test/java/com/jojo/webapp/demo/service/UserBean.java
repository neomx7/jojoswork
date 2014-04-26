/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.demo.service;

import org.activiti.engine.RuntimeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <summary>
 * 
 * </summary>
 * 
 * @author jojo
 * 
 */
public class UserBean
{
    /** injected by Spring */
    private RuntimeService runtimeService;

    @Transactional
    public void hello() {
      // here you can do transactional stuff in your domain model
      // and it will be combined in the same transaction as 
      // the startProcessInstanceByKey to the Activiti RuntimeService
      runtimeService.startProcessInstanceByKey("helloProcess");
    }
    
    public void setRuntimeService(RuntimeService runtimeService) {
      this.runtimeService = runtimeService;
    }
}
