/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

import java.util.Map;


/**
 * <summary>
 * 流程实例
 * </summary>
 *
 * @author jojo
 *
 */
public class ProcessInstance extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 3403839722668738733L;

    private String processDefinitionId;
    private String processInstanceId;

    /**业务key */
    private String businessKey;

    /**
     * 委托人id
     */
    private String tenantId;


    /**
     * 流程额外的参数集
     */
    private Map<String, Object> processVariables;

    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey()
    {
        return businessKey;
    }

    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

    public Map<String, Object> getProcessVariables()
    {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables)
    {
        this.processVariables = processVariables;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ProcessInstance [processDefinitionId=");
        builder.append(processDefinitionId);
        builder.append(", processInstanceId=");
        builder.append(processInstanceId);
        builder.append(", businessKey=");
        builder.append(businessKey);
        builder.append(", tenantId=");
        builder.append(tenantId);
        builder.append(", processVariables=");
        builder.append(processVariables);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
