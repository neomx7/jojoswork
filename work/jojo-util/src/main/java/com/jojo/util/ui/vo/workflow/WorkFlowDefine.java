package com.jojo.util.ui.vo.workflow;

import com.jojo.util.pojo.BasePOJO;

public class WorkFlowDefine extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -858320274900041485L;

    private String deploymentId;
    private String resourceName;
    private String diagramResourceName;
    private boolean hasStartFormKey;
    private boolean suspended;
    private String tenantId;

    public String getDeploymentId()
    {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId)
    {
        this.deploymentId = deploymentId;
    }
    public String getResourceName()
    {
        return resourceName;
    }
    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }
    public String getDiagramResourceName()
    {
        return diagramResourceName;
    }
    public void setDiagramResourceName(String diagramResourceName)
    {
        this.diagramResourceName = diagramResourceName;
    }
    public boolean isHasStartFormKey()
    {
        return hasStartFormKey;
    }
    public void setHasStartFormKey(boolean hasStartFormKey)
    {
        this.hasStartFormKey = hasStartFormKey;
    }
    public boolean isSuspended()
    {
        return suspended;
    }
    public void setSuspended(boolean suspended)
    {
        this.suspended = suspended;
    }
    public String getTenantId()
    {
        return tenantId;
    }
    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

}
