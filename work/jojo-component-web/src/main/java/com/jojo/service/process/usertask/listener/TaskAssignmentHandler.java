/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.usertask.listener;

import org.activiti.engine.delegate.DelegateTask;

/**
 * <summary>
 * [分派下一个节点的任务负责人]<br>
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
        // and then for example call following methods:
        //在这里设置之前form中设定好的分派人

        delegateTask.setVariable("formDivId", getFormDivId());
        delegateTask.setVariable("qryDataBeanId", getQryDataBeanId());
        delegateTask.setVariable("qryDataBeanMethod", getQryDataBeanMethod());
    }

}
