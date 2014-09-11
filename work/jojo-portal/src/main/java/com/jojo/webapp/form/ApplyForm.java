/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;


/**
 * <summary>
 * 物料申请
 * </summary>
 *
 * @author jojo
 *
 */
public class ApplyForm extends BaseForm
{

    /**   */
    private static final long serialVersionUID = 2874084372707416681L;

    private String nextUsrId;

    private String instanceId;

    private String instanceTaskId;

    public String getNextUsrId()
    {
        return nextUsrId;
    }

    public void setNextUsrId(String nextUsrId)
    {
        this.nextUsrId = nextUsrId;
    }

    public String getInstanceId()
    {
        return instanceId;
    }

    public void setInstanceId(String instanceId)
    {
        this.instanceId = instanceId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ApplyForm [nextUsrId=");
        builder.append(nextUsrId);
        builder.append(", instanceId=");
        builder.append(instanceId);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

    public String getInstanceTaskId()
    {
        return instanceTaskId;
    }

    public void setInstanceTaskId(String instanceTaskId)
    {
        this.instanceTaskId = instanceTaskId;
    }
}
