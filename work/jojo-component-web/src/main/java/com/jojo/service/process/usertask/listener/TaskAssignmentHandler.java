/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.usertask.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * <summary>
 * [分派下一个节点的任务负责人]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class TaskAssignmentHandler implements TaskListener
{

    /**   */
    private static final long serialVersionUID = -2436247344095303654L;


    @Override
    public void notify(DelegateTask arg0)
    {
        // Execute custom identity lookups here
        // and then for example call following methods:
        //在这里设置之前form中设定好的分派人


    }

}
