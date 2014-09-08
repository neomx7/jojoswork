package com.jojo.dal.common.postgre.domain;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [物料申请，原始的申请物料]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class MaterialApplyDO extends BasePOJO
{

    /**   */


    /**   */
    private static final long serialVersionUID = -7537729638496299308L;
    /**
     * 状态信息的描述，针对流程节点，取值为流程的备注信息，如 ‘安技部经理审核’
     */
    private String statusDsc;

    /**
     * 流程实例id
     */
    private String instanceId;

    public String getStatusDsc()
    {
        return statusDsc;
    }

    public void setStatusDsc(String statusDsc)
    {
        this.statusDsc = statusDsc;
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
