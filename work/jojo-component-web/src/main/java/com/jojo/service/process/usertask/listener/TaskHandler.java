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

    /**
     * 需要审批意见
     */
    private FixedValue approvedRequired;
    /**
     * 流程状态自定义，9为已经完成。
     */
    private FixedValue status;
    /**
     * 为最后一个节点 ，'yes' | 'true' | '1' 为是，其他都不是。
     */
    private FixedValue isLastNode;

    public FixedValue getApprovedRequired()
    {
        return approvedRequired;
    }

    public void setApprovedRequired(FixedValue approvedRequired)
    {
        this.approvedRequired = approvedRequired;
    }

    public FixedValue getStatus()
    {
        return status;
    }

    public void setStatus(FixedValue status)
    {
        this.status = status;
    }

    public FixedValue getIsLastNode()
    {
        return isLastNode;
    }

    public void setIsLastNode(FixedValue isLastNode)
    {
        this.isLastNode = isLastNode;
    }
}
