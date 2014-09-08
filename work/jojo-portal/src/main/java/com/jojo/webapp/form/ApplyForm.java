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
}
