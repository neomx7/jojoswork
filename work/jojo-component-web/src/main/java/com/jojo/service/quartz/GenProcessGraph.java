/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.jojo.integration.workflow.WorkFlowExecutor;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class GenProcessGraph extends SchedulingJob
{
    @Autowired
    private WorkFlowExecutor workFlowExecutor;

    /**
     * 检查还有哪些工作流程定义未生成图片，生成到数据库中
     */
    public void work()
    {
        workFlowExecutor.genProcessGraph();
    }
}
