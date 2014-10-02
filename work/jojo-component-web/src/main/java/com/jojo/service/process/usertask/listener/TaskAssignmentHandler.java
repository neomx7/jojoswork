/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.usertask.listener;

import org.activiti.engine.delegate.DelegateTask;

import com.jojo.util.constants.JOJOConstants;

/**
 * <summary>
 * [维护全局使用的task变量]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class TaskAssignmentHandler extends TaskHandler
{
    /**   */
    private static final long serialVersionUID = 6504653808165347949L;




    @Override
    public void notify(DelegateTask delegateTask)
    {
        // Execute custom identity lookups here
        delegateTask.setVariableLocal(JOJOConstants.WORKFLOW_TASK_VARIABLES_KYE_APPROVEDREQUIRED, getApprovedRequired());
    }

}
