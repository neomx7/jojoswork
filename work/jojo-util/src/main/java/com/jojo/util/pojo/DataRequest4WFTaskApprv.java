/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class DataRequest4WFTaskApprv extends DataRequest {

    /**   */
    private static final long serialVersionUID = -6627992392376251212L;

    /**
     * 流程实例id
     */
    private String instanceId;

    /**
     *
     */
    private String instTaskId;

    /**
     *
     */
    private String businessKey;

    private String assigner;

    private int apprvFlg;

    public String getInstanceId()
    {
        return instanceId;
    }
    public void setInstanceId(String instanceId)
    {
        this.instanceId = instanceId;
    }
    public String getInstTaskId()
    {
        return instTaskId;
    }
    public void setInstTaskId(String instTaskId)
    {
        this.instTaskId = instTaskId;
    }
    public String getBusinessKey()
    {
        return businessKey;
    }
    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey;
    }
    public String getAssigner()
    {
        return assigner;
    }
    public void setAssigner(String assigner)
    {
        this.assigner = assigner;
    }
    public int getApprvFlg()
    {
        return apprvFlg;
    }
    public void setApprvFlg(int apprvFlg)
    {
        this.apprvFlg = apprvFlg;
    }

}
