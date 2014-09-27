/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.task.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class DefaultTask4Log implements JavaDelegate
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(DelegateExecution execution) throws Exception {
        logger.info("task variables : {}",execution.getVariables());
      }

    }
