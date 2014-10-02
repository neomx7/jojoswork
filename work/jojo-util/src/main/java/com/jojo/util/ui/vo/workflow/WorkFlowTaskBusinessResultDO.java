package com.jojo.util.ui.vo.workflow;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [工作流业务对象的处理结果，一般在流程结束时更新其状态]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowTaskBusinessResultDO extends BasePOJO
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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkFlowTaskBusinessResultDO [instanceId=");
        builder.append(instanceId);
        builder.append(", instTaskId=");
        builder.append(instTaskId);
        builder.append(", businessKey=");
        builder.append(businessKey);
        builder.append("]");
        return builder.toString();
    }


}
