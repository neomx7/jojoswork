/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process;

import java.util.List;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.pojo.ProcessTaskForm;

/**
 * <summary>
 * 封装 activiti5 实现工作流操作
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowExecutorImpl implements WorkFlowExecutor
{

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#queryList(int, java.lang.String)
     */
    @Override
    public List<?> queryList(int status, String operId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#startProcessInstanceByKey(java.lang.String, java.lang.String)
     */
    @Override
    public void startProcessInstanceByKey(String processKey, String operId)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#claimTask(java.lang.String, java.lang.String)
     */
    @Override
    public void claimTask(String taskId, String operId)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#completeTask(java.lang.String, java.lang.String)
     */
    @Override
    public void completeTask(String taskId, String operId)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#pendProcessByKey(java.lang.String, java.lang.String)
     */
    @Override
    public void pendProcessByKey(String processKey, String operId)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.jojo.facade.workflow.WorkFlowExecutor#getTaskForm(java.lang.String)
     */
    @Override
    public ProcessTaskForm getTaskForm(String taskId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
