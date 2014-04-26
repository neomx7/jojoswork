/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <summary>
 * 
 * </summary>
 * 
 * @author jojo
 * 
 */
public class SchedulingJob
{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public void work()
    {
        logger.info("scheduling job work...");
    }
}
