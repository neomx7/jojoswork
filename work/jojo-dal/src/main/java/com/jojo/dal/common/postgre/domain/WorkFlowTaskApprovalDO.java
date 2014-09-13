package com.jojo.dal.common.postgre.domain;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [工作流审批记录]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowTaskApprovalDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -2490478264563043844L;

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

    private String apprvContent;

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

    public String getApprvContent()
    {
        return apprvContent;
    }

    public void setApprvContent(String apprvContent)
    {
        this.apprvContent = apprvContent;
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
