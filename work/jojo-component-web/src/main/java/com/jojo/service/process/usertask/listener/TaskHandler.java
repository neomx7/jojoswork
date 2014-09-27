/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.usertask.listener;

import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

/**
 * <summary>
 * [分派下一个节点的任务负责人]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public abstract class TaskHandler implements TaskListener
{

    /**   */
    private static final long serialVersionUID = 5603774273648788216L;

    private FixedValue approvedRequired;

    public FixedValue getApprovedRequired()
    {
        return approvedRequired;
    }

    public void setApprovedRequired(FixedValue approvedRequired)
    {
        this.approvedRequired = approvedRequired;
    }
}
